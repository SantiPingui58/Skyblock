package me.santipingui58.celestialmc;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import me.santipingui58.celestialmc.game.CelestialPlayer;
import me.santipingui58.celestialmc.game.skyblock.SkyblockIsland;
import me.santipingui58.celestialmc.game.skyblock.SkyblockManager;

public class CelestialAPI {

	private static CelestialAPI api;	
	 public static CelestialAPI getAPI() {
	        if (api == null)
	        	api = new CelestialAPI();
	        return api;
	    }
	
	public CelestialPlayer getCelestialPlayer(Player p) {
		return SkyblockManager.getManager().getCelestialPlayer(p);
	}
	
	public SkyblockIsland getIslandAt(Location l) {
		return SkyblockManager.getManager().getIslandByLocation(l);
	}
	
	
}
