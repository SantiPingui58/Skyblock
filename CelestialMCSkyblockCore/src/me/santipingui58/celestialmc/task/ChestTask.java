package me.santipingui58.celestialmc.task;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.inventory.ItemStack;

import me.santipingui58.celestialmc.Main;
import me.santipingui58.celestialmc.game.chest.AutoBlockChest;
import me.santipingui58.celestialmc.game.chest.AutoBlockMaterial;
import me.santipingui58.celestialmc.game.chest.AutoSellChest;
import me.santipingui58.celestialmc.game.chest.ChestManager;
import me.santipingui58.celestialmc.game.chest.MaterialSellValue;



public class ChestTask {

	

	public ChestTask() {
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
            	  
            	  
            	  for (AutoSellChest aschest : ChestManager.getManager().getAutoSellChests()) {
            		  if (aschest.isPlaced()) {
            			  if (aschest.getInventory().getContents().length>0) {
            				  for (ItemStack i : aschest.getInventory().getContents()) {
     					  
            					  if (i!=null) {
            						  if (!i.getType().equals(Material.AIR)) {
            							  
            						  int v = MaterialSellValue.getValue(i.getType());
            						  int money = aschest.getMoney();
            						  aschest.setMoney(money+v);
            						  i.setAmount(i.getAmount()-1);
            						  aschest.setTimesUsed(aschest.getTimesUsed()+1);
            					  }
            				  }
            				  }
            				  
            				  Chest chestState = (Chest) aschest.getInventory().getLocation().getBlock().getState();
            				  chestState.setCustomName("§a§lAutoSell Chest - §6§l$"+aschest.getMoney());
            				  chestState.update();
		 }
            		  }
            	  }
            	   
               }
		 }, 10, 10L);
		
	}
}
