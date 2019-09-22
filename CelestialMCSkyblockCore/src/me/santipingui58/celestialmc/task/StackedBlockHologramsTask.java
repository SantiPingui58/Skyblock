package me.santipingui58.celestialmc.task;

import org.bukkit.Bukkit;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;

import me.santipingui58.celestialmc.Main;
import me.santipingui58.celestialmc.game.stacking.StackeableManager;
import me.santipingui58.celestialmc.game.stacking.StackedBlock;



public class StackedBlockHologramsTask {

	

	public StackedBlockHologramsTask() {
		task();

	}
	
	
	private void task() {
		
		 Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Main.get(), new Runnable() {
		          
               @Override
               public void run() {          
            	   for (Hologram hologram : HologramsAPI.getHolograms(Main.get())) {
            		   boolean delete = true;
            		   for (StackedBlock sblock : StackeableManager.getManager().getStackedBlocks()) {
            			   if (sblock.getHologram().equals(hologram)) {
            				   sblock.updateHologram();
            				   delete = false;
            				   break;
            			   }
            		   }
            		   
            		   
            		   if (delete) {
            		   hologram.delete();
            		   }
            	   }
               }
		 }, 10, 20L);
	
			
	
	}
	
	

	
	
	
	
}
