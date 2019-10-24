package me.santipingui58.celestialmc.scoreboard;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.scoreboard.Scoreboard;

import me.lucko.luckperms.api.Contexts;
import me.lucko.luckperms.api.LuckPermsApi;
import me.lucko.luckperms.api.User;
import me.lucko.luckperms.api.caching.MetaData;
import me.santipingui58.celestialmc.Main;
import me.santipingui58.celestialmc.game.CelestialPlayer;
import me.santipingui58.celestialmc.game.DataManager;
import me.santipingui58.celestialmc.game.PlayerLocation;
import me.santipingui58.celestialmc.game.skyblock.SkyblockIsland;
import me.santipingui58.celestialmc.game.skyblock.SkyblockManager;
import me.santipingui58.celestialmc.utils.FastBoard;



public class PinguiScoreboard {
	
	private static PinguiScoreboard scoreboard;	
	  private final Map<CelestialPlayer, FastBoard> boards = new HashMap<>();
	  
	 public static PinguiScoreboard getScoreboard() {
	        if (scoreboard == null)
	        	scoreboard = new PinguiScoreboard();
	        return scoreboard;
	    }
	
	 public Map<CelestialPlayer,FastBoard> getPlayerScoreboards() {
		 return boards;
	 }
	public void scoreboard() {
		
		Iterator<Entry<CelestialPlayer, FastBoard>> it = boards.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry<CelestialPlayer,FastBoard> pair = (Map.Entry<CelestialPlayer,FastBoard>)it.next();
	       CelestialPlayer cp = pair.getKey();
	       FastBoard board = pair.getValue();
	    
	       updateBoard(board,cp);
	    }	   
	}

	@SuppressWarnings("deprecation")
	public void setTags() {
		for (CelestialPlayer cplayer : DataManager.getManager().getOnlinePlayers()) {
			if (cplayer.isOnline()) {
				Player p = cplayer.getPlayer();
				Scoreboard scoreboard = Main.getScoreboard();
				LuckPermsApi api = null;
				 RegisteredServiceProvider<LuckPermsApi> provider = Bukkit.getServicesManager().getRegistration(LuckPermsApi.class);
				 if (provider != null) {
				      api = provider.getProvider();				     
				 }			 
				 User user = api.getUser(p.getUniqueId());
				 MetaData metaData = user.getCachedData().getMetaData(Contexts.allowAll());
				 String prefix = metaData.getPrefix();
				 if (prefix!=null) {
				 if (prefix.contains("DEV")) {
					 scoreboard.getTeam("dev").addPlayer(p);
				 } else if (prefix.contains("OWNER")) {
					 scoreboard.getTeam("owner").addPlayer(p);
				 } else if (prefix.contains("Celestial")) {
					 scoreboard.getTeam("celestial").addPlayer(p);
				 } else if (prefix.contains("Saint")) {
					 scoreboard.getTeam("saint").addPlayer(p);
				 }else if (prefix.contains("Venerate")) {
					 scoreboard.getTeam("venerate").addPlayer(p);
				 }else if (prefix.contains("Ancestor")) {
					 scoreboard.getTeam("ancestor").addPlayer(p);
				 }else if (prefix.contains("Emperor")) {
					 scoreboard.getTeam("emperor").addPlayer(p);
				 }else if (prefix.contains("GrandMaster")) {
					 scoreboard.getTeam("grandmaster").addPlayer(p);
				 }else if (prefix.contains("Master")) {
					 scoreboard.getTeam("master").addPlayer(p);
				 }else if (prefix.contains("Disciple")) {
					 scoreboard.getTeam("disciple").addPlayer(p);
				 } else {
					 scoreboard.getTeam("def").addPlayer(p);
				 }
				
			} else {
				 scoreboard.getTeam("def").addPlayer(p);
			}
				 p.setScoreboard(scoreboard);
			} 
		}
		
			
		
	}
	

	 private void updateBoard(FastBoard board,CelestialPlayer cplayer) {
		 if (cplayer.getOptions().hasScoreboardEnabled()) {
		 String location = PlayerLocation.SPAWN.asString();
		 if (cplayer.getLocation().equals(PlayerLocation.OTHER_PLAYER_ISLAND)) {
				location = PlayerLocation.OTHER_PLAYER_ISLAND.asString();
			 SkyblockIsland island = SkyblockManager.getManager().getIslandByLocation(cplayer.getPlayer().getLocation());
			if (island!=null) {
				location = location.replace("%player%", island.getOwner().getOfflinePlayer().getName());
			} else {
				location = location.replace("%player%", "Unknown");
			}
		 } else if (cplayer.getLocation().equals(PlayerLocation.OWN_ISLAND)) {
			 location = PlayerLocation.OWN_ISLAND.asString();
		 }
		 
		 
		 board.updateLines(
					null,
					"§fName: §3" + cplayer.getOfflinePlayer().getName(),
					"§fRank: " + getPrefix(cplayer),
					"§fMoney: §6$" + cplayer.getStringMoney(), 
					null,					
					"§b§l* §a" + location,
					null,
					"§fOnline players: §a" + Bukkit.getOnlinePlayers().size(),
					"§fMobCoins: §60",
					null,
					"   §7play.celestialmc.com");
	    } else {
	    	cplayer.getPlayer().setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
	    }
	 }
	
	private String getPrefix(CelestialPlayer cplayer) {
		LuckPermsApi api = null;
		 RegisteredServiceProvider<LuckPermsApi> provider = Bukkit.getServicesManager().getRegistration(LuckPermsApi.class);
		 if (provider != null) {
		      api = provider.getProvider();
		     
		 }
		 
		 User user = api.getUser(cplayer.getUUID());
		 MetaData metaData = user.getCachedData().getMetaData(Contexts.allowAll());
		 String prefix = metaData.getPrefix();
		 
		 if (prefix!=null) {
			 return ChatColor.translateAlternateColorCodes('&', prefix);
		 }
		 return "§7Default";
	}

	
}
	

