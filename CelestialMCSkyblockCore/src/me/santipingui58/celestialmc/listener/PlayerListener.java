package me.santipingui58.celestialmc.listener;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.scheduler.BukkitRunnable;

import me.santipingui58.celestialmc.Main;
import me.santipingui58.celestialmc.game.CelestialPlayer;
import me.santipingui58.celestialmc.game.PlayerLocation;
import me.santipingui58.celestialmc.game.skyblock.PlayerPermissions;
import me.santipingui58.celestialmc.game.skyblock.SkyblockIsland;
import me.santipingui58.celestialmc.game.skyblock.SkyblockManager;
import me.santipingui58.celestialmc.utils.Utils;

public class PlayerListener implements Listener {
 
	
	@EventHandler
	public void onDeath(PlayerDeathEvent e) {
		Player p = e.getEntity();
		CelestialPlayer cplayer = SkyblockManager.getManager().getCelestialPlayer(p);
		e.setDeathMessage(null);

	      new BukkitRunnable()
	      {
	        public void run() {
	          p.spigot().respawn();
	        }
	      }
	      .runTaskLater(Main.get(), 1L);
	      

	      new BukkitRunnable()
	      {
	        public void run() {
	        	if (cplayer.getLocation().equals(PlayerLocation.OWN_ISLAND)) {
	        		SkyblockManager.getManager().homeTeleport(cplayer,null);
	        	}  else {
	        	Location spawn = Utils.getLoc(Main.config.getConfig().getString("spawn"), true,true);
	   		 cplayer.setLocation(PlayerLocation.SPAWN);
	   		 p.teleport(spawn);
	        	}
	        }
	      }
	      .runTaskLater(Main.get(), 2L);
		 
		 
	}
	
	@EventHandler
	public void onTeleport(PlayerTeleportEvent e) {
		Player p = e.getPlayer();
		CelestialPlayer cplayer = SkyblockManager.getManager().getCelestialPlayer(p);
		if (e.getTo().getWorld().getName().equalsIgnoreCase("world")) {
			cplayer.setLocation(PlayerLocation.SPAWN);
		} else if (e.getTo().getWorld().getName().equalsIgnoreCase("skyblock")) {
			if (cplayer.hasIsland()) {
				if (cplayer.getIsland().getSpawnPoint().distance(e.getTo()) < spaceToBlocks(cplayer.getIsland().getSpace())) {
					cplayer.setLocation(PlayerLocation.OWN_ISLAND);
				} else {
					cplayer.setLocation(PlayerLocation.OTHER_PLAYER_ISLAND);
				}
			} else {
				cplayer.setLocation(PlayerLocation.OTHER_PLAYER_ISLAND);
			}
		}
	}
	
	@EventHandler
	public void onPlace(BlockPlaceEvent e) {
		if (e.getBlockAgainst().getType().equals(Material.SPAWNER)) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onPvP(EntityDamageByEntityEvent e) {
		if (e.getEntity().getWorld().getName().equalsIgnoreCase("skyblock")) {		
			if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
			e.setCancelled(true);
			} else {
				if (e.getDamager() instanceof Player) {
				SkyblockIsland island = SkyblockManager.getManager().getIslandByLocation(e.getEntity().getLocation());
				if (island!=null) {
					Player damager = (Player) e.getDamager();
					CelestialPlayer cdamager = SkyblockManager.getManager().getCelestialPlayer(damager);
					if (!island.hasPermission(cdamager, PlayerPermissions.CAN_KILL_ENTITIES)) {
						e.setCancelled(true);
					}
				}
				}
				}
			}
		
	}
	private int spaceToBlocks(int i) {
		if (i==1) {
			return 100;
		} 
		return 100;
	}
}
