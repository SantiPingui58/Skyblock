package me.santipingui58.celestialmc.task;

import java.util.Date;

import org.bukkit.Bukkit;

import me.santipingui58.celestialmc.Main;
import me.santipingui58.celestialmc.game.DataManager;
import me.santipingui58.celestialmc.game.skyblock.SkyblockIsland;


public class MinuteTask {

	

	public MinuteTask() {
		task();

	}
	
	
	private void task() {
		
		 Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Main.get(), new Runnable() {
		          
               @SuppressWarnings("deprecation")
			@Override
               public void run() {          
            	   Date date = new Date();
                   if (date.getHours() == 0 && date.getMinutes() == 0) {
                	   for (SkyblockIsland island : DataManager.getManager().getIslands()) {
                		   island.interest();
                	   }
                   }
               }
		 }, 10, 20*60L);
	
			
	
	}
	
	

	
	
	
	
	
	
}
