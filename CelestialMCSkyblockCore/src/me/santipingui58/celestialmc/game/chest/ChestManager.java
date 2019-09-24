package me.santipingui58.celestialmc.game.chest;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import me.santipingui58.celestialmc.Main;
import me.santipingui58.celestialmc.game.CelestialPlayer;
import me.santipingui58.celestialmc.game.skyblock.SkyblockIsland;
import me.santipingui58.celestialmc.game.skyblock.SkyblockManager;

public class ChestManager implements Listener {
	
	private static ChestManager manager;	
	 public static ChestManager getManager() {
	        if (manager == null)
	        	manager = new ChestManager();
	        return manager;
	    }
	 
	 private List<AutoBlockChest> autoblockchests = new ArrayList<AutoBlockChest>();
	 private List<AutoSellChest> autosellchests = new ArrayList<AutoSellChest>();
	 
	 public List<AutoBlockChest> getAutoBlockChests() {
		 return this.autoblockchests;
	 }
	 
	 public List<AutoSellChest> getAutoSellChests() {
		 return this.autosellchests;
	 }
	 
	 public void createAutoBlockChest(CelestialPlayer owner) {
		 AutoBlockChest abchest = new AutoBlockChest(UUID.randomUUID(),owner,null,0);
		 autoblockchests.add(abchest);	
		 owner.getPlayer().getInventory().addItem(abchest.getItem());
		 		 
	 }
	 
	 public void createAutosSellChest(CelestialPlayer owner) {
		 AutoSellChest aschest = new AutoSellChest(UUID.randomUUID(),owner,null,0,0);
		 autosellchests.add(aschest);	
		 owner.getPlayer().getInventory().addItem(aschest.getItem());
		 		 
	 }
	 
	 @EventHandler
	 public void onPlace(BlockPlaceEvent e) {
		 if (e.getItemInHand().getType().equals(Material.CHEST)) {
			 ItemStack chest = e.getItemInHand();
			 if (chest.getItemMeta().getLore()!=null) {
			 if (chest.getItemMeta().getLore().size()>0) {
				 String string = ChatColor.stripColor(chest.getItemMeta().getLore().get(7));	
				 if (string!=null) {
					 try {
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
					 } catch (Exception ex) {}
	 }			 
				 String str = ChatColor.stripColor(chest.getItemMeta().getLore().get(10));
				 if (str!=null) {
					 try {
					 UUID uuid = UUID.fromString(str);
					 if (uuid!=null) {
						 for (AutoSellChest aschest : ChestManager.getManager().getAutoSellChests()) {
							 if (aschest.getUUID().toString().equalsIgnoreCase(uuid.toString())) {
								 aschest.place(e.getBlock().getLocation());
								 e.getPlayer().sendMessage(Main.skyblock_prefix+ " §aYou have placed an Auto Sell Chest!");
								 if (e.getPlayer().getInventory().contains(aschest.getItem())) {
									 e.getPlayer().getInventory().remove(aschest.getItem());
								 }
								 return;
							 } 
						 }
					 }
					 } catch (Exception ex) {}
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
			for (AutoSellChest aschest : ChestManager.getManager().getAutoSellChests()) {
				if (aschest.isPlaced()) {
					if (aschest.getLocation().equals(e.getBlock().getLocation())) {
						 e.getPlayer().sendMessage(Main.skyblock_prefix+ " §aYou have picked up an Auto Sell Chest!");
						 e.setCancelled(true);
						 e.getBlock().setType(Material.AIR);
						 e.getBlock().getWorld().dropItem(e.getBlock().getLocation(), aschest.getItem());
						 aschest.pickUp();			
						 return;
					}
				}
			} 
			
	 }
	 }
	 
	 @EventHandler
	 public void onInteract(PlayerInteractEvent e) {
		 if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			 if (e.getPlayer().isSneaking()) {
				 if (e.getClickedBlock().getType().equals(Material.CHEST)) {
					 for (AutoSellChest aschest : ChestManager.getManager().getAutoSellChests()) {
						 if (aschest.isPlaced()) {
							 if (aschest.getLocation().equals(e.getClickedBlock().getLocation())) {
								 SkyblockIsland island = SkyblockManager.getManager().getIslandByLocation(e.getClickedBlock().getLocation());
								 if (island!=null) {
									 for (CelestialPlayer members : island.getMembersAndOwner()) {
										 if (members.getOfflinePlayer().getUniqueId().toString().equalsIgnoreCase(e.getPlayer().getUniqueId().toString())) {
											 if (aschest.getMoney()>0) {									
												 e.getPlayer().sendMessage(Main.skyblock_prefix+" §aYou have succesfully withdrawn §6$" + aschest.getMoney() + " §afrom the Auto Sell Chest!");
												 members.addMoney(aschest.getMoney());
												 aschest.setMoney(0);											
												 e.setCancelled(true);
												 return;
											 } else {
												 e.getPlayer().sendMessage(Main.skyblock_prefix+" §cThe Auto Sell Chest does not have money to withdraw.");											
												 e.setCancelled(true);
												 return;
											 }
											 
											 
											 
										 }
									 }
									 e.getPlayer().sendMessage(Main.skyblock_prefix+" §cOnly members of this Skyblock Island can do this.");
									 e.setCancelled(true);
								 }
								 
							 }
						 }
					 }
				 }
			 }
		 }
	 }

	 
}
