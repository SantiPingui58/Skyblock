package me.santipingui58.celestialmc.game.skyblock;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;


import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.WorldEditException;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormats;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardReader;
import com.sk89q.worldedit.function.operation.Operation;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.session.ClipboardHolder;

import me.santipingui58.celestialmc.Main;
import me.santipingui58.celestialmc.game.CelestialPlayer;
import me.santipingui58.celestialmc.game.DataManager;
import me.santipingui58.celestialmc.game.Result;
import me.santipingui58.celestialmc.utils.RandomCollection;
import me.santipingui58.celestialmc.utils.Utils;


public class SkyblockManager {

	private static SkyblockManager manager;	
	 public static SkyblockManager getManager() {
	        if (manager == null)
	        	manager = new SkyblockManager();
	        return manager;
	    }
	
	
	 private List<Integer> nextLocations = new ArrayList<Integer>();

	public List<Integer> getNextLocations() {
		return nextLocations;
	}
	 public CelestialPlayer getCelestialPlayer(Player p) {
		 for (CelestialPlayer cplayer : DataManager.getManager().getPlayers()) {
			 if (cplayer.getUUID().equals(p.getUniqueId())) {
				 return cplayer;
			 }
		 }

		 return null;
	 }
	 
	 
	 public CelestialPlayer getCelestialPlayer(UUID uuid) {
	
		 for (CelestialPlayer cplayer : DataManager.getManager().getPlayers()) {
			 if (cplayer.getUUID().equals(uuid)) {
				 return cplayer;
			 }
		 }
		return null;
	 }

	 
		public void generateNewIsland(CelestialPlayer cplayer) {
			List<Integer> list = nextLocation();
			int x = list.get(0);
			int y = 100;
			int z = list.get(1);
			Location mainlocation = new Location(Bukkit.getWorld("skyblock"),x,y,z);
			 HashMap<CelestialPlayer,List<PlayerPermissions>> permissions = new HashMap<CelestialPlayer,List<PlayerPermissions>>();
			 SkyblockIsland main = new SkyblockIsland(null,0,new ArrayList<String>(), cplayer, mainlocation,1,null,false,null, permissions,1,1,1,1,1);		 			 
			 DataManager.getManager().getIslands().add(main);			 
			 File file = new File( Main.get().getDataFolder(),"schematics/skyblock_island.schem");
			 Clipboard clipboard = null;
			 ClipboardFormat format = ClipboardFormats.findByFile(file);
			 try (ClipboardReader reader = format.getReader(new FileInputStream(file))) {
			     clipboard = reader.read();
			 } catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			 
			 try (EditSession editSession = WorldEdit.getInstance().getEditSessionFactory().getEditSession(new BukkitWorld(mainlocation.getWorld()), -1)) {
				    Operation operation = new ClipboardHolder(clipboard)
				            .createPaste(editSession)
				            .to(BlockVector3.at(x, y, z))
				            .ignoreAirBlocks(false)
				            .build();
				    try {
						Operations.complete(operation);
					} catch (WorldEditException e) {
						e.printStackTrace();
					}
				}
			 			 
			 if (cplayer.isOnline()) {
			 cplayer.getPlayer().teleport(main.getSpawnPoint());
			 }
		 }
		 

	public List<Integer> nextLocation() {
		int value = 1500;
		if (this.nextLocations.isEmpty()) {
			this.nextLocations.add(0);
			this.nextLocations.add(0);
		}
		int x = this.nextLocations.get(0);
		int z = this.nextLocations.get(1);
		
		if (x<value*100) {
			x = x + value;
		} else {
			z = z + value;
			x = 0;
		}
		this.nextLocations.clear();
		this.nextLocations.add(x);
		this.nextLocations.add(z);
		List<Integer> nueva = new ArrayList<Integer>();
		nueva.add(x);
		nueva.add(z);
		return nueva;
	}
	 
	public SkyblockIsland getIslandByLocation(Location l) {
		if (l.getWorld().getName().equalsIgnoreCase("skyblock")) {
		 for (SkyblockIsland i : DataManager.getManager().getIslands()) {
			 if (i.getSpawnPoint().distance(l)<550) {
				return i;
			 }
		 }
		}
		 return null;
	}
	
	public void homeTeleport(CelestialPlayer cplayer,SkyblockIsland island) {
		Player p = cplayer.getPlayer();
		if (island==null) {
			island = cplayer.getIsland();
		}
		
		Location home = island.getHome();
		Location spawnpoint = island.getSpawnPoint();
		if (Utils.getUtils().isSafeLocation(home)) {
			p.teleport(home);
		} else {
			if (Utils.getUtils().isSafeLocation(home.getWorld().getHighestBlockAt(home).getLocation())) {
				Location t = home.getWorld().getHighestBlockAt(home).getLocation();
				t.setDirection(home.getDirection());
				p.teleport(t);
			} else {
				p.sendMessage(Main.skyblock_prefix+ChatColor.RED+" Your Skyblock Island home was blocked, so you were teleported to your Skyblock Island Spawnpoint.");
				if (Utils.getUtils().isSafeLocation(spawnpoint)) {
					p.teleport(spawnpoint);
				} else {
					if (Utils.getUtils().isSafeLocation(spawnpoint.getWorld().getHighestBlockAt(spawnpoint).getLocation())) {
						Location t = spawnpoint.getWorld().getHighestBlockAt(spawnpoint).getLocation();
						t.setDirection(spawnpoint.getDirection());
						p.teleport(t);
					} else {
						spawnpoint.getBlock().setType(Material.AIR);
						Location l = spawnpoint.add(0,1,0);
						l.getBlock().setType(Material.AIR);
						p.teleport(spawnpoint);
					}
				}
			}
		}
	}
	
	
	
	public void warpTeleport(CelestialPlayer cplayer,CelestialPlayer teleporter) {
		Player p = teleporter.getPlayer();
		Location warp = cplayer.getIsland().getWarp();
		
		if (Utils.getUtils().isSafeLocation(warp)) {
			p.teleport(warp);
		} else {
			if (Utils.getUtils().isSafeLocation(warp.getWorld().getHighestBlockAt(warp).getLocation())) {
				Location t = warp.getWorld().getHighestBlockAt(warp).getLocation();
				t.setDirection(warp.getDirection());
				p.teleport(t);
			} else {
				p.sendMessage(Main.skyblock_prefix+ChatColor.RED+" Could not teleport you to the warp since it is blocked, contact the owner of the Skyblock Island.");
			}
		}
	}
	
	
	
	
	
	
	public void removeFromIsland(CelestialPlayer member, SkyblockIsland island,boolean kicked) {
		island.getMembers().remove(member);
		for (PlayerPermissions perms : island.getAllPermissions(member)) {
			island.removePermission(member, perms);
		}
		
		if (member.isOnline()) {
			Location spawn = Utils.getUtils().getLoc(Main.config.getConfig().getString("spawn"), true,false);
			member.getPlayer().teleport(spawn);
			if (kicked) {
				member.getPlayer().sendMessage(Main.skyblock_prefix+"§c You have been kicked from the Skyblock Island of §b" + island.getOwner().getOfflinePlayer().getName());
			} else {
				member.getPlayer().sendMessage(Main.skyblock_prefix + "§c You have left the Skyblock Island of §b" + island.getOwner().getOfflinePlayer().getName());
			}
 		}
		
		if (island.getOwner().isOnline()) {
			if (kicked) {
				island.getOwner().getPlayer().sendMessage(Main.skyblock_prefix + " §cYou have succesfully kicked §b" + member.getOfflinePlayer().getName() + "§c from your Skyblock Island.");
			} else {
				island.getOwner().getPlayer().sendMessage(Main.skyblock_prefix + "§c The player §b" + member.getOfflinePlayer().getName() + " §chas left your Skyblock Island!");
			}
		}
	}

	
	
	public void giveCrateKey(CelestialPlayer cplayer) {
		 RandomCollection<String> random = new RandomCollection<String>();
		 random.add(940, "None");
		 random.add(10, "Vote");
		 random.add(10,"Spawner");
		 random.add(10,"Rare");
		 random.add(10, "Epic");
		 random.add(10, "Celestial");
		 random.add(10, "Monthly");
		 
		 String result = random.next();
		 if (result.equalsIgnoreCase("Vote")) {
			 Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "crates key Vote " + cplayer.getOfflinePlayer().getName());
			 cplayer.sendMessage("&e&lYou have found a &a&lVote Crate Key&e&l!", Result.ALLOW);
		 } else if (result.equalsIgnoreCase("Spawner")) {
			 Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "crates key Spawner " + cplayer.getOfflinePlayer().getName());
			 cplayer.sendMessage("&e&lYou have found a &a&lSpawner Crate Key&e&l!", Result.ALLOW);
		 } else if (result.equalsIgnoreCase("Rare")) {
			 Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "crates key Rare " + cplayer.getOfflinePlayer().getName());
			 cplayer.sendMessage("&e&lYou have found a &a&lRare Crate Key&e&l!", Result.ALLOW);
		 } else if (result.equalsIgnoreCase("Epic")) {
			 Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "crates key Epic " + cplayer.getOfflinePlayer().getName());
			 cplayer.sendMessage("&e&lYou have found a &a&lEpic Crate Key&e&l!", Result.ALLOW);
		 } else if (result.equalsIgnoreCase("Celestial")) {
			 Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "crates key Celestial " + cplayer.getOfflinePlayer().getName());
			 cplayer.sendMessage("&e&lYou have found a &a&lCelestial Crate Key&e&l!", Result.ALLOW);
		 } else if (result.equalsIgnoreCase("Monthly")) {
			 Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "crates key Monthly " + cplayer.getOfflinePlayer().getName());
			 cplayer.sendMessage("&e&lYou have found a &a&lMonthly Crate Key&e&l!", Result.ALLOW);
		 }
	}
}
