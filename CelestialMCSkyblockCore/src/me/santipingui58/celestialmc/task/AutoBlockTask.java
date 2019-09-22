package me.santipingui58.celestialmc.task;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import me.santipingui58.celestialmc.Main;
import me.santipingui58.celestialmc.game.chest.AutoBlockChest;
import me.santipingui58.celestialmc.game.chest.AutoBlockMaterial;
import me.santipingui58.celestialmc.game.chest.ChestManager;



public class AutoBlockTask {

	

	public AutoBlockTask() {
		task();

	}
	
	
	private void task() {
		
		 Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Main.get(), new Runnable() {
		          
               @Override
               public void run() {    
            	  for (AutoBlockChest abchest : ChestManager.getManager().getAutoBlockChests()) {
            		  if (abchest.isPlaced()) {
            			  ItemStack[] inv = abchest.getInventory().getContents();
            			 abchest.getInventory().clear();
            			 for (ItemStack i : inv) {
            				 if (i!=null) {
            					 abchest.getInventory().addItem(i);
            				 }
            			 }
            			 
            			 for (ItemStack item : abchest.getInventory().getContents()) {
            				 if (item!=null) {				 
            					 if (AutoBlockMaterial.fromMaterial(item.getType())!=null) {
            						AutoBlockMaterial abm = AutoBlockMaterial.fromMaterial(item.getType());
            						 int amount = abm.getAmount();
            						 Material block = abm.getBlock();
            						 if (item.getAmount()>=amount) {
            							 item.setAmount(item.getAmount()-amount);
            							 abchest.getInventory().addItem(new ItemStack(block));
            							 abchest.setTimesUsed(abchest.getTimesUsed()+1);
            						 }
            					 }
            			 }
            		  }
            		  }
            	  }
            	   
               }
		 }, 10, 10L);
		
	}
}
