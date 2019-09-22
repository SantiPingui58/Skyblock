package me.santipingui58.celestialmc.task;

import org.bukkit.Bukkit;
import org.bukkit.Sound;

import me.santipingui58.celestialmc.Main;
import me.santipingui58.celestialmc.game.CelestialPlayer;
import me.santipingui58.celestialmc.game.DataManager;
import me.santipingui58.celestialmc.game.PlayerLocation;
import me.santipingui58.celestialmc.game.skyblock.SkyblockIsland;
import me.santipingui58.celestialmc.game.skyblock.SkyblockManager;



public class PlayerMoveTask {

	public PlayerMoveTask() {
		task();
	}
	
	private void task() {
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Main.get(), new Runnable() {
			
		    public void run() {
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
		    	
		  
		    }, 20, 8L);
	}
}
