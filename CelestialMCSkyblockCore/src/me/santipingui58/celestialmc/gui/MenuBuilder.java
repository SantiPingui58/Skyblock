package me.santipingui58.celestialmc.gui;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.santipingui58.celestialmc.Main;





public abstract class MenuBuilder implements Listener {
    Main plugin;
    
    public MenuBuilder(Main plugin) {
    this.plugin = plugin;
  }
    
	Inventory _inv;

	public MenuBuilder(String name, int rows){
		_inv = Bukkit.createInventory(null, 9 * rows, ChatColor.translateAlternateColorCodes('&', name));
		Main.get().getServer().getPluginManager().registerEvents(this, Main.get());
	}
	
	public  void a(ItemStack stack){
		_inv.addItem(stack);
	}
	public void s(int i , ItemStack stack){
		_inv.setItem(i, stack);
	}
	public Inventory i(){
		return _inv;
	}
	
	public void o(Player p){
		p.openInventory(_inv);
	}

	  @EventHandler
	    public void onInventoryClick(InventoryClickEvent event) {
	        if (event.getInventory().equals(this.i())) {
	            if (event.getCurrentItem() != null && this.i().contains(event.getCurrentItem())  && event.getWhoClicked() instanceof Player) {
	                this.onClick((Player) event.getWhoClicked(), event.getCurrentItem(), event.getSlot(),event);
	                event.setCancelled(true);
	            } 
	        }
	    }
	  @EventHandler
	    public void onInventoryClose(InventoryCloseEvent event) {
	        if (event.getInventory().equals(this.i()) && event.getPlayer() instanceof Player) {
	            this.onClose((Player) event.getPlayer());
	        }
	    }
	  public void onClose(Player player) {}
	  public abstract void onClick(Player p, ItemStack stack, int slot, InventoryClickEvent event);
}