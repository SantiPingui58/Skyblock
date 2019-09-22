package me.santipingui58.celestialmc.listener;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.santipingui58.celestialmc.Main;
import me.santipingui58.celestialmc.game.CelestialPlayer;
import me.santipingui58.celestialmc.game.DataManager;
import me.santipingui58.celestialmc.game.PlayerLocation;
import me.santipingui58.celestialmc.game.skyblock.SkyblockManager;
import me.santipingui58.celestialmc.scoreboard.PinguiScoreboard;
import me.santipingui58.celestialmc.utils.FastBoard;
import me.santipingui58.celestialmc.utils.Utils;

public class PlayerConnectEvent implements Listener {

	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		 e.setJoinMessage(null);
		 Player p = e.getPlayer();
		 CelestialPlayer cplayer = null;
		if (Main.data.getConfig().contains("players."+p.getUniqueId().toString())) {
			cplayer = SkyblockManager.getManager().getCelestialPlayer(p);
		} else {
			cplayer = DataManager.getManager().createPlayerData(p.getUniqueId());
			
		}
		
		FastBoard board = new FastBoard(p);
		board.updateTitle("§a§lCelestialMC");		 
		PinguiScoreboard.getScoreboard().getPlayerScoreboards().put(cplayer, board);
		 if (cplayer.getLocation().equals(PlayerLocation.SPAWN)) {
			 Location spawn = Utils.getLoc(Main.config.getConfig().getString("spawn"), true);
			 p.teleport(spawn);
		 } else if (cplayer.getLocation().equals(PlayerLocation.OWN_ISLAND)) {
			 p.teleport(cplayer.getIsland().getHome());
		 }
	}
	
	
	@EventHandler
	public void onLeave(PlayerQuitEvent e) {
		e.setQuitMessage(null);
		CelestialPlayer cplayer = SkyblockManager.getManager().getCelestialPlayer(e.getPlayer());
		FastBoard board = PinguiScoreboard.getScoreboard().getPlayerScoreboards().remove(cplayer);
		if (board !=null) {
			board.delete();
		}
		
	}
}
