package me.santipingui58.celestialmc.game.hopper;

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
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import me.santipingui58.celestialmc.Main;
import me.santipingui58.celestialmc.game.CelestialPlayer;
import me.santipingui58.celestialmc.game.Result;
import me.santipingui58.celestialmc.game.skyblock.SkyblockIsland;
import me.santipingui58.celestialmc.game.skyblock.SkyblockManager;
import me.santipingui58.celestialmc.gui.hopper.HopperMenu;
import me.santipingui58.celestialmc.utils.Utils;

public class HopperManager implements Listener {

	private static HopperManager manager;	
	 public static HopperManager getManager() {
	        if (manager == null)
	        	manager = new HopperManager();
	        return manager;
	    }
	
	private List<CelestialHopper> hoppers = new ArrayList<CelestialHopper>();
	public List<CelestialHopper> getHoppers() {
	return this.hoppers;
	}
	
	 public void createHopper(CelestialPlayer owner) {
		 CelestialHopper hopper = new CelestialHopper(UUID.randomUUID(), owner, null, 1);
		 hoppers.add(hopper);	
		 owner.getPlayer().getInventory().addItem(hopper.getItem());
		 		 
	 }
	 
	 public void removeHopper(CelestialHopper hopper) {
		 SkyblockIsland island = SkyblockManager.getManager().getIslandByLocation(hopper.getLocation());
		 if (island!=null) {
			 if (island.getHoppers().contains(hopper)) {
				 island.getHoppers().remove(hopper);
			 }
		 }
	 }
	 
	 public CelestialHopper getHopper(UUID uuid) {
		for (CelestialHopper hopper : HopperManager.getManager().getHoppers()) {
			if (hopper.getUUID().toString().equalsIgnoreCase(uuid.toString())) {
				return hopper;
			}
		}
		return null;
	 }
	
	 public CelestialHopper getHopperAt(Location location) {
		 for (CelestialHopper hopper : HopperManager.getManager().getHoppers()) {
			 if (hopper.isPlaced()) {
				 if (hopper.getLocation().equals(location)) {
					 return hopper;
				 }
			 }
		 }
		 return null;
	 }
	
	 @EventHandler
	 public void onInteract(PlayerInteractEvent e) {
		 Player p = e.getPlayer();
		 if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
		 if (e.getClickedBlock().getType().equals(Material.HOPPER)) {		
			 if (p.isSneaking()) {
			 for (CelestialHopper hopper : HopperManager.getManager().getHoppers()) {
				 if (hopper.isPlaced()) {
				 if (hopper.getLocation().equals(e.getClickedBlock().getLocation())) {
					 new BukkitRunnable() {
						 public void run() {
					 new HopperMenu(p,hopper).o(p);
					 return;
						 }
					 }.runTaskLater(Main.get(), 1L);
				 }
				 } 
			 }
		 }
			 }
		 }
	 }
	 
	 @EventHandler
	 public void onPlace(BlockPlaceEvent e) {
		 if (e.getItemInHand().getType().equals(Material.HOPPER)) {
			 SkyblockIsland island = SkyblockManager.getManager().getIslandByLocation(e.getBlock().getLocation());
				if (island!=null) {
					if (island.getHoppers().size()>=island.getMaxHoppersLevel()) {
						 SkyblockManager.getManager().getCelestialPlayer(e.getPlayer()).sendMessage("You have reached the limit of Hoppers of this Skyblock Island!", Result.DENY);
						 e.setCancelled(true);
						 return;
					}
				}
			 
			 ItemStack item = e.getItemInHand();
			 if (item.hasItemMeta()) {
				 if (item.getItemMeta().hasLore()) {
			 if (item.getItemMeta().getLore().size()>=8) {
				 String string = ChatColor.stripColor(item.getItemMeta().getLore().get(7));			
				 UUID uuid = null;
				 try {
				  uuid = UUID.fromString(string);
				 } catch (Exception ex) {}
				 
		 for (CelestialHopper hopper : HopperManager.getManager().getHoppers()) {
			 if (hopper.getUUID().toString().equalsIgnoreCase(uuid.toString())) {
				 hopper.place(e.getBlock().getLocation());					 	
				 e.getPlayer().sendMessage(Main.skyblock_prefix+ " §aYou have placed an Hopper!");
				 if (e.getPlayer().getInventory().contains(hopper.getItem())) {
					 e.getPlayer().getInventory().remove(hopper.getItem());
				 }			
				 return;
			 } 
		 }
		
			 } 
	 }
			 }
	 
	 }
	 }
	 @EventHandler
	 public void onBreak(BlockBreakEvent e) {

		 if (e.getBlock().getType().equals(Material.HOPPER)) {
			 for (CelestialHopper hopper :  HopperManager.getManager().getHoppers()) {
				 if (hopper.isPlaced()) {
				 if (hopper.getLocation().equals(e.getBlock().getLocation())) {
					 e.getPlayer().sendMessage(Main.skyblock_prefix+ " §aYou have picked up an Hopper!");
					 e.setCancelled(true);
					 removeHopper(hopper);
					 e.getBlock().setType(Material.AIR);
					 e.getBlock().getWorld().dropItem(e.getBlock().getLocation(), hopper.getItem());
					 hopper.pickUp();			
					 return;
				 }
				 } 
			 }
	 }
	 }
	 
	 
	@EventHandler
	public void onHopper(InventoryMoveItemEvent  e) {
		if (e.getInitiator().getType().equals(InventoryType.HOPPER)) {
			CelestialHopper hopper = getHopperAt(e.getInitiator().getLocation());
			if (hopper!=null) {
					e.setCancelled(true);
					if (!hopper.isTransfering() && Utils.getUtils().hasEmptySpace(e.getDestination(), e.getItem())) {
					hopper.transfer(e.getDestination(),e.getSource());			
					}
			
					}
		}
	}
	

}
