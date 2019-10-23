package me.santipingui58.celestialmc.game.rank;


public class RankManager {

	
	private static RankManager manager;	
	 public static RankManager getManager() {
	        if (manager == null)
	        	manager = new RankManager();
	        return manager;
	    }
	 
	 
	 
}
