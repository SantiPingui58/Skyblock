package me.santipingui58.celestialmc.game.spawner;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.SpawnerSpawnEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import me.santipingui58.celestialmc.Main;
import me.santipingui58.celestialmc.game.CelestialPlayer;
import me.santipingui58.celestialmc.gui.spawner.SpawnerMenu;
public class SpawnerManager implements Listener {

	
	private static SpawnerManager manager;	
	 public static SpawnerManager getManager() {
	        if (manager == null)
	        	manager = new SpawnerManager();
	        return manager;
	    }
	
	private List<CelestialSpawner> spawners = new ArrayList<CelestialSpawner>();
	
	public List<CelestialSpawner> getSpawners() {
	return this.spawners;
	}
	
	 public void createSpawner(CelestialPlayer owner,SpawnerType type) {
		 CelestialSpawner spawner = new CelestialSpawner(UUID.randomUUID(), owner, null, type,1);
		 spawners.add(spawner);	
		 owner.getPlayer().getInventory().addItem(spawner.getItem());
		 		 
	 }
	 
	 
	 public CelestialSpawner getSpawner(UUID uuid) {
		 for (CelestialSpawner spawner : SpawnerManager.getManager().getSpawners()) {			
			 if (spawner.getUUID().toString().equalsIgnoreCase(uuid.toString())) {
				 return spawner;
			 }
		 }
		 return null;
	 }
	 
	 public CelestialSpawner getSpawnerByItem(ItemStack item) {
		 for (CelestialSpawner spawner : SpawnerManager.getManager().getSpawners()) {
			 if (spawner.getItem().isSimilar(item)) {
				 return spawner;
			 }
		 }
		 return null;
	 }
	 
	 public CelestialSpawner getSpawnerAt(Location l) {
		 for (CelestialSpawner spawner : SpawnerManager.getManager().getSpawners()) {
			 if (spawner.isPlaced()) {
			 if (spawner.getLocation().equals(l)) {
				 return spawner;
			 }
			 }
		 }
		 return null;
	 }
	
	 @EventHandler
	 public void onInteract(PlayerInteractEvent e) {
		 Player p = e.getPlayer();
		 if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
		 if (e.getClickedBlock().getType().equals(Material.SPAWNER)) {
			 if (e.getItem()!=null) {
			 if (e.getItem().getType().equals(Material.SPAWNER)) {
				 ItemStack item = e.getItem();
				 if (item.getItemMeta().hasLore() && item.getItemMeta().getLore().size()>=9) {
					 String string = ChatColor.stripColor(item.getItemMeta().getLore().get(8));
					 UUID uuid = null;
					 try {
						  uuid = UUID.fromString(string);
					 } catch(Exception ex) {
						 return;
					 }
						 for (CelestialSpawner spawner : SpawnerManager.getManager().getSpawners()) {
							 if (spawner.getUUID().toString().equalsIgnoreCase(uuid.toString())) {
								 CelestialSpawner clickedspawner = SpawnerManager.getManager().getSpawnerAt(e.getClickedBlock().getLocation());
								 if (clickedspawner.getType().equals(spawner.getType()) && clickedspawner.getLevel()==spawner.getLevel()) {
									 clickedspawner.getStackedSpawners().add(spawner);
									 item.setAmount(item.getAmount()-1);
									 e.setCancelled(true);
									 return;
									
								 } else {
									 p.sendMessage(Main.skyblock_prefix +" §cYou can stack only spawners from same level and same mob!");
									 e.setCancelled(true);
									 return;
								 }
								 
							 }
						 }
				 } 
			 } 
			 }
			 
			 for (CelestialSpawner spawner : SpawnerManager.getManager().getSpawners()) {
				 if (spawner.isPlaced()) {
				 if (spawner.getLocation().equals(e.getClickedBlock().getLocation())) {
					 new SpawnerMenu(p,spawner).o(p);
					 return;
				 }
				 } 
			 }
		 }
		 }
	 }
	 
	 @EventHandler
	 public void onPlace(BlockPlaceEvent e) {
		 if (e.getItemInHand().getType().equals(Material.SPAWNER)) {
			 if (!e.getBlockAgainst().getType().equals(Material.SPAWNER)) {
			 ItemStack item = e.getItemInHand();
			 if (item.hasItemMeta()) {
				 if (item.getItemMeta().hasLore()) {
			 if (item.getItemMeta().getLore().size()>=9) {
				 String string = ChatColor.stripColor(item.getItemMeta().getLore().get(8));			
				 UUID uuid = null;
				 try {
				  uuid = UUID.fromString(string);
				 } catch (Exception ex) {}
				 
		 for (CelestialSpawner spawner: SpawnerManager.getManager().getSpawners()) {
			 if (spawner.getUUID().toString().equalsIgnoreCase(uuid.toString())) {
				 spawner.place(e.getBlock().getLocation());					 
				 spawner.updateSpawnerData();	
				 e.getPlayer().sendMessage(Main.skyblock_prefix+ " §aYou have placed an Spawner!");
				 if (e.getPlayer().getInventory().contains(spawner.getItem())) {
					 e.getPlayer().getInventory().remove(spawner.getItem());
				 }			
				 return;
			 } 
		 }
		
			 } 
	 }
			 }
	 }
	 }
	 }
	 @EventHandler
	 public void onBreak(BlockBreakEvent e) {
		 if (e.getBlock().getType().equals(Material.SPAWNER)) {
			 for (CelestialSpawner spawner: SpawnerManager.getManager().getSpawners()) {
				 if (spawner.isPlaced()) {
				 if (spawner.getLocation().equals(e.getBlock().getLocation())) {
					 e.getPlayer().sendMessage(Main.skyblock_prefix+ " §aYou have picked up an Spawner!");
					 e.setCancelled(true);
					 e.getBlock().setType(Material.AIR);
					 e.getBlock().getWorld().dropItem(e.getBlock().getLocation(), spawner.getItem());
					 spawner.pickUp();			
					 return;
				 }
				 } 
			 }
	 }
	 }
	 
	 
	 @EventHandler
	 public void onSpawn(SpawnerSpawnEvent e) {
		 for (CelestialSpawner spawner : SpawnerManager.getManager().getSpawners()) {
				if (spawner.isPlaced()) {
					if (spawner.getLocation().equals(e.getSpawner().getBlock().getLocation())) {
						if (!spawner.isActivated()) {
							e.setCancelled(true);
							return;
						} 
					}
				}
			}
		 }
}
