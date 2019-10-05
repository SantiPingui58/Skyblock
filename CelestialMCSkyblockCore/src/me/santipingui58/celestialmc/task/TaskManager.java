package me.santipingui58.celestialmc.task;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Chest;
import org.bukkit.inventory.ItemStack;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;

import me.santipingui58.celestialmc.Main;
import me.santipingui58.celestialmc.game.CelestialPlayer;
import me.santipingui58.celestialmc.game.DataManager;
import me.santipingui58.celestialmc.game.PlayerLocation;
import me.santipingui58.celestialmc.game.chest.AutoBlockChest;
import me.santipingui58.celestialmc.game.chest.AutoBlockMaterial;
import me.santipingui58.celestialmc.game.chest.AutoSellChest;
import me.santipingui58.celestialmc.game.chest.ChestManager;
import me.santipingui58.celestialmc.game.chest.MaterialSellValue;
import me.santipingui58.celestialmc.game.skyblock.SkyblockIsland;
import me.santipingui58.celestialmc.game.skyblock.SkyblockManager;
import me.santipingui58.celestialmc.game.spawner.CelestialSpawner;
import me.santipingui58.celestialmc.game.spawner.SpawnerManager;
import me.santipingui58.celestialmc.game.stacking.StackeableManager;
import me.santipingui58.celestialmc.game.stacking.StackedBlock;
import me.santipingui58.celestialmc.scoreboard.PinguiScoreboard;

public class TaskManager {

	private static TaskManager manager;	
	 public static TaskManager getManager() {
	        if (manager == null)
	        	manager = new TaskManager();
	        return manager;
	    }
	 
	 private List<Task> tasks = new ArrayList<Task>();	
	 public void task() {
		 loadTasks();
		 t();
	 }
	 
	 private void loadTasks() {
		 Task chest = new Task(TaskType.CHEST,2);
		 Task hopper = new Task(TaskType.HOPPER,0);
		 Task minute = new Task(TaskType.MINUTE,240);
		 Task move = new Task(TaskType.MOVE,1);
		 Task hologram = new Task(TaskType.HOLOGRAM,4);
		 Task tab = new Task(TaskType.TAB,4);
		this.tasks.add(chest);
		this.tasks.add(hopper);
		this.tasks.add(minute);
		this.tasks.add(move);
		this.tasks.add(hologram);
		this.tasks.add(tab);	
	 }
	 private void t() {
		 Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Main.get(), new Runnable() {
			@Override
			public void run() {
				for (Task task : tasks) {
					if (task.getCurrentTick()>=task.getTick()) {
						task.resetCurrentTick();
						getVoid(task);
					} else {
						task.addCurrentTick();
					}
				}
			}
	          
		 }, 10, 5L);
	
	 }
	 
	 private void getVoid(Task task) {
		 if (task.getType().equals(TaskType.CHEST)) {
			 chestTask();
		 } else if (task.getType().equals(TaskType.HOLOGRAM)) {
			 hologramTask();
		 } else if (task.getType().equals(TaskType.HOPPER)) {
			 hopperTask();
		 } else if (task.getType().equals(TaskType.MINUTE)) {
			 minuteTask();
		 } else if (task.getType().equals(TaskType.MOVE)) {
			 moveTask();
		 } else if (task.getType().equals(TaskType.TAB)) {
			 tabTask();
		 }
	 }
	 
	 private void chestTask() {
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
	 
	 
	 
	 @SuppressWarnings("deprecation")
	private void minuteTask() {
		  Date date = new Date();
          if (date.getHours() == 0 && date.getMinutes() == 0) {
       	   for (SkyblockIsland island : DataManager.getManager().getIslands()) {
       		   island.interest();
       	   }
          }
	 }
	 private void moveTask() {
			for (CelestialPlayer cplayer : DataManager.getManager().getPlayers()) {
	    		if (cplayer.isOnline()) {
	    		
	    			SkyblockIsland island = SkyblockManager.getManager().getIslandByLocation(cplayer.getPlayer().getLocation());
	    			if (island!=null) {
	    				if (island.getOwner().getUUID().toString().equalsIgnoreCase(cplayer.getUUID().toString())) {
	    					cplayer.setLocation(PlayerLocation.OWN_ISLAND);
	    				} else {
	    					cplayer.setLocation(PlayerLocation.OTHER_PLAYER_ISLAND);
	    				}
	    				int x = island.getSpawnPoint().getBlockX();
	    				int z = island.getSpawnPoint().getBlockZ();
	    				int value_x = Math.abs(Math.abs(cplayer.getPlayer().getLocation().getBlockX())-x);
	    				int value_z = Math.abs(Math.abs(cplayer.getPlayer().getLocation().getBlockZ())-z);

	    				if (value_x>island.getSpace()/2 || value_z>island.getSpace()/2) {
	    					cplayer.getPlayer().sendMessage(Main.skyblock_prefix + " You have reached the edge of your Skyblock Island.");
	    					cplayer.getPlayer().teleport(cplayer.getPrevLocation());
	    					cplayer.getPlayer().playSound(cplayer.getPlayer().getLocation(), Sound.ENTITY_BLAZE_SHOOT, 1, 1);
	    				} 
	    	} else {
	    		cplayer.setLocation(PlayerLocation.SPAWN);
	    	}
	    			
	    			cplayer.setPrevLocation();
	    		}
	    	}
	 }
	 
	 
	 private void hologramTask() {
	 	   for (Hologram hologram : HologramsAPI.getHolograms(Main.get())) {
    		   boolean delete = true;
    		   for (StackedBlock sblock : StackeableManager.getManager().getStackedBlocks()) {
    			   if (sblock.getHologram().equals(hologram)) {
    				   sblock.updateHologram();
    				   delete = false;
    				   break;
    			   }
    		   }
    		   
    		   for (CelestialSpawner spawner : SpawnerManager.getManager().getSpawners()) {
    			   if (spawner.isPlaced()) {
    			   if (spawner.getHologram().equals(hologram)) {
    				   spawner.updateHologram();
    				   delete = false;
    				   break;
    			   }
    		   }
    		   }
    		   
    		   if (delete) {
    		   hologram.delete();
    		   }
    	   }
	 }
	 
	 private void hopperTask() {
		 
	 }
	 
	 private void tabTask() {
		  PinguiScoreboard.getScoreboard().setTags();
   	   PinguiScoreboard.getScoreboard().scoreboard();
	 }
}
