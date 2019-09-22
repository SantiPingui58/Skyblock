package me.santipingui58.celestialmc.task;

import org.bukkit.Bukkit;

import me.santipingui58.celestialmc.Main;
import me.santipingui58.celestialmc.scoreboard.PinguiScoreboard;



public class TabTask {

	

	public TabTask() {
		task();

	}
	
	
	private void task() {
		
		 Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Main.get(), new Runnable() {
		          
               @Override
               public void run() {          
            	   PinguiScoreboard.getScoreboard().setTags();
            	   PinguiScoreboard.getScoreboard().scoreboard();
               }
		 }, 10, 20L);
	
			
	
	}
	
	

	
	
	
	
	
	
}
