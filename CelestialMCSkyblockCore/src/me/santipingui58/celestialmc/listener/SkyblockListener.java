package me.santipingui58.celestialmc.listener;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockFormEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.InventoryHolder;

import me.santipingui58.celestialmc.game.CelestialPlayer;
import me.santipingui58.celestialmc.game.skyblock.PlayerPermissions;
import me.santipingui58.celestialmc.game.skyblock.SkyblockIsland;
import me.santipingui58.celestialmc.game.skyblock.SkyblockManager;
import me.santipingui58.celestialmc.utils.RandomCollection;

public class SkyblockListener implements Listener{
	
	 @EventHandler
	 public void onBreak(BlockBreakEvent e) {
		 if (e.getBlock().getLocation().getWorld().getName().equalsIgnoreCase("skyblock")) {
		 Player p = e.getPlayer();		 
		 CelestialPlayer cplayer = SkyblockManager.getManager().getCelestialPlayer(p);
		 SkyblockIsland island = SkyblockManager.getManager().getIslandByLocation(e.getBlock().getLocation());
		 if (island!=null) {	
				if (!island.getOwner().getUUID().toString().equalsIgnoreCase(cplayer.getUUID().toString()) && !island.hasPermission(cplayer, PlayerPermissions.CAN_BREAK)) {
					 e.setCancelled(true);
					 p.sendMessage(ChatColor.RED+"You do not have permission to do this.");		
					 return;
			 } else {
				 int x = island.getSpawnPoint().getBlockX();
					int z = island.getSpawnPoint().getBlockZ();
					int value_x = Math.abs(Math.abs(e.getBlock().getLocation().getBlockX())-x);
					int value_z = Math.abs(Math.abs(e.getBlock().getLocation().getBlockZ())-z);
					if (value_x>island.getSpace()/2 || value_z>island.getSpace()/2) {
						e.setCancelled(true);
						return;
					} 
			 }
				
		 }
		 } else {
			 if (!e.getPlayer().hasPermission("celestialmc.admin")) {
				 e.setCancelled(true);
			 }
		 }
	 }
	 
	 @EventHandler
	 public void onPlace(BlockPlaceEvent e) {
		 if (e.getBlock().getLocation().getWorld().getName().equalsIgnoreCase("skyblock")) {
			 Player p = e.getPlayer();		 
			 CelestialPlayer cplayer = SkyblockManager.getManager().getCelestialPlayer(p);
			 SkyblockIsland island = SkyblockManager.getManager().getIslandByLocation(e.getBlock().getLocation());
			 if (island!=null) {	
					if (!island.getOwner().getUUID().toString().equalsIgnoreCase(cplayer.getUUID().toString()) && !island.hasPermission(cplayer, PlayerPermissions.CAN_PLACE)) {
						 e.setCancelled(true);
						 p.sendMessage(ChatColor.RED+"You do not have permission to do this.");		
						 return;
				 } else {
					 int x = island.getSpawnPoint().getBlockX();
						int z = island.getSpawnPoint().getBlockZ();
						int value_x = Math.abs(Math.abs(e.getBlock().getLocation().getBlockX())-x);
						int value_z = Math.abs(Math.abs(e.getBlock().getLocation().getBlockZ())-z);
						if (value_x>island.getSpace()/2 || value_z>island.getSpace()/2) {
							e.setCancelled(true);
							return;
						} 
				 }
			 }
			 } else {
				 if (!e.getPlayer().hasPermission("celestialmc.admin")) {
					 e.setCancelled(true);
				 }
			 }
	 }
	 
	 @EventHandler
	 public void onInteract(PlayerInteractEvent e) {
		 if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
		 if (e.getPlayer().getLocation().getWorld().getName().equalsIgnoreCase("skyblock")) {
			 Player p = e.getPlayer();		 
			 CelestialPlayer cplayer = SkyblockManager.getManager().getCelestialPlayer(p);
			 SkyblockIsland island = SkyblockManager.getManager().getIslandByLocation(e.getClickedBlock().getLocation());
			 if (island!=null) {	
					if (!island.getOwner().getUUID().toString().equalsIgnoreCase(cplayer.getUUID().toString()) && !island.hasPermission(cplayer, PlayerPermissions.CAN_OPEN_INVENTORIES) && e.getClickedBlock().getState() instanceof InventoryHolder) {
						 e.setCancelled(true);
						 p.sendMessage(ChatColor.RED+"You do not have permission to do this.");		
				 } else {
					 int x = island.getSpawnPoint().getBlockX();
						int z = island.getSpawnPoint().getBlockZ();
						int value_x = Math.abs(Math.abs(e.getClickedBlock().getLocation().getBlockX())-x);
						int value_z = Math.abs(Math.abs(e.getClickedBlock().getLocation().getBlockZ())-z);
						if (value_x>island.getSpace()/2 || value_z>island.getSpace()/2) {
							e.setCancelled(true);
						} 
				 }
			 }
			 } else {
				 if (!e.getPlayer().hasPermission("celestialmc.admin")) {
					 e.setCancelled(true);
				 }
			 }
	 }
	 }
	 
	 
	 @EventHandler
	 public void onGenerator(BlockFormEvent e) {
		 if (e.getBlock().getLocation().getWorld().getName().equalsIgnoreCase("skyblock")) {
		 if (e.getBlock().getType().equals(Material.LAVA)) {
			 SkyblockIsland i = SkyblockManager.getManager().getIslandByLocation(e.getBlock().getLocation());
			 if (i!=null) {
				 RandomCollection<Material> items = new RandomCollection<Material>();
				 double level = i.getGenerationLevel();
				 double diamondper = level*0.25;
				 items.add(diamondper, Material.DIAMOND_ORE);
				 double emeraldper = level*0.5;
				 items.add(emeraldper, Material.EMERALD_ORE);
				 double goldper = level*0.75;
				 items.add(goldper, Material.GOLD_ORE);
				 double lapisper = level*1;
				 items.add(lapisper, Material.LAPIS_ORE);
				 double redstoneper = level*1.25;
				 items.add(redstoneper, Material.REDSTONE_ORE);
				 double ironper = level*1.5;
				 items.add(ironper, Material.IRON_ORE);
				 double coalper = level*1.75;
				 items.add(coalper, Material.COAL_ORE);
				 double suma = diamondper-emeraldper-goldper-lapisper-redstoneper-ironper-coalper;
				 double cobblestoneper = 100 - suma;
				 items.add(cobblestoneper, Material.COBBLESTONE);
				 
				 e.getNewState().setType(items.next());
				 e.getNewState().update();
			 }
		 } 
	 }
	 }
}
