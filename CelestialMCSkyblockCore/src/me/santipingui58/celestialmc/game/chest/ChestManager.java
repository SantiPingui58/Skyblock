package me.santipingui58.celestialmc.game.chest;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import me.santipingui58.celestialmc.Main;
import me.santipingui58.celestialmc.game.CelestialPlayer;

public class ChestManager implements Listener {
	
	private static ChestManager manager;	
	 public static ChestManager getManager() {
	        if (manager == null)
	        	manager = new ChestManager();
	        return manager;
	    }
	 
	 private List<AutoBlockChest> autoblockchests = new ArrayList<AutoBlockChest>();
	 
	 public List<AutoBlockChest> getAutoBlockChests() {
		 return this.autoblockchests;
	 }
	 
	 public void createAutoBlockChest(CelestialPlayer owner) {
		 AutoBlockChest abchest = new AutoBlockChest(UUID.randomUUID(),owner,null,0);
		 autoblockchests.add(abchest);	
		 owner.getPlayer().getInventory().addItem(abchest.getItem());
		 		 
	 }
	 
	 
	 @EventHandler
	 public void onPlace(BlockPlaceEvent e) {
		 if (e.getItemInHand().getType().equals(Material.CHEST)) {
			 ItemStack chest = e.getItemInHand();
			 if (chest.getItemMeta().getLore().size()>0) {
				 String string = ChatColor.stripColor(chest.getItemMeta().getLore().get(7));			
				 UUID uuid = UUID.fromString(string);
		 for (AutoBlockChest abchest : ChestManager.getManager().getAutoBlockChests()) {
			 if (abchest.getUUID().toString().equalsIgnoreCase(uuid.toString())) {
				 abchest.place(e.getBlock().getLocation());
				 e.getPlayer().sendMessage(Main.skyblock_prefix+ " §aYou have placed an Auto Block Chest!");
				 if (e.getPlayer().getInventory().contains(abchest.getItem())) {
					 e.getPlayer().getInventory().remove(abchest.getItem());
				 }
				 return;
			 } 
		 }
			 } 
	 }
	 }
	 
	 @EventHandler
	 public void onBreak(BlockBreakEvent e) {
		 if (e.getBlock().getType().equals(Material.CHEST)) {
			 for (AutoBlockChest abchest : ChestManager.getManager().getAutoBlockChests()) {
				 if (abchest.isPlaced()) {
				 if (abchest.getLocation().equals(e.getBlock().getLocation())) {
					 e.getPlayer().sendMessage(Main.skyblock_prefix+ " §aYou have picked up an Auto Block Chest!");
					 e.setCancelled(true);
					 e.getBlock().setType(Material.AIR);
					 e.getBlock().getWorld().dropItem(e.getBlock().getLocation(), abchest.getItem());
					 abchest.pickUp();			
					 return;
				 }
				 } 
			 }
	 }
	 }

	 
}
