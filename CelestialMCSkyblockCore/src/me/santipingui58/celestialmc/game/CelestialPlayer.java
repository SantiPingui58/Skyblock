package me.santipingui58.celestialmc.game;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import me.santipingui58.celestialmc.Main;
import me.santipingui58.celestialmc.game.skyblock.SkyblockIsland;


public class CelestialPlayer {
 
	private UUID uuid;
	private PlayerLocation location;
	private Location prevLocation;
	public CelestialPlayer(UUID uuid) {
		this.uuid = uuid;		
		this.location = PlayerLocation.SPAWN;
	}
	
	public double getMoney() {
	return	Main.econ.getBalance(Bukkit.getOfflinePlayer(uuid));
	}
	public boolean hasMoney(double d) {
		if (Main.econ.getBalance(Bukkit.getOfflinePlayer(uuid))>=d) {
			return true;
		}
		return false;
	}
	public void addMoney(double d) {
		Main.econ.depositPlayer(Bukkit.getOfflinePlayer(uuid), d);
	}
	public void takeMoney(double d) {
		Main.econ.withdrawPlayer(Bukkit.getOfflinePlayer(uuid), d);
	}
	
	public String getStringMoney() {
		
		if (this.getMoney()>= 1000000000000L) {
			double i = this.getMoney()/1000000000000L;
			String d =  new DecimalFormat("#.##").format(i);
			return d+"tril";
		} else if (this.getMoney()>=1000000000) {
			double i = this.getMoney()/1000000000;
			String d =  new DecimalFormat("#.##").format(i);
			return d+"bil";
		} else if (this.getMoney()>=10000000) {
			double i = this.getMoney()/1000000;
			String d =  new DecimalFormat("#.##").format(i);
			return d+"mil";
		} else {
			return String.valueOf(getMoney());
		}
	}
	
	public PlayerLocation getLocation() {
		return this.location;
	}
	
	public Location getPrevLocation() {
		if (this.prevLocation!=null) {
		return this.prevLocation;
		} else {
			return getIsland().getHome();
		}
	}
	
	public void setPrevLocation() {
		if (isOnline()) {
			Player p = getPlayer();
			this.prevLocation = p.getLocation();
		}
	}
	public void setLocation(PlayerLocation loc) {
		this.location = loc;
	}
	
	public UUID getUUID() {
		return this.uuid;
	}
	
	public OfflinePlayer getOfflinePlayer() {
		return Bukkit.getOfflinePlayer(this.uuid);
	}
	
	public Player getPlayer() {
		Player player = Bukkit.getPlayer(uuid);
		if (Bukkit.getOnlinePlayers().contains(player)) {
			return player;
		}
		return null;
	}
	
	public boolean isOnline() {
		Player player = Bukkit.getPlayer(uuid);
		if (Bukkit.getOnlinePlayers().contains(player)) {
			return true;
		} else {
			return false;
		}
	}
	
	public SkyblockIsland getIsland() {
		for (SkyblockIsland i : DataManager.getManager().getIslands()) {
			if (i.getOwner().equals(this)) {
				return i;
			}
		}
		return null;
			
	}
	
	public boolean hasIsland() {
		for (SkyblockIsland i : DataManager.getManager().getIslands()) {
			if (i.getOwner().equals(this)) {
				return true;
			}
		}		
		return false;		
	}
	
	public boolean isMember() {
		for (SkyblockIsland i : DataManager.getManager().getIslands()) {
			if (i.getMembers().contains(this)) {
				return true;
			}
		}
		return false;
	}
	public List<SkyblockIsland> getIslandsWhereIsMember() {
		List<SkyblockIsland> list = new ArrayList<SkyblockIsland>();
		for (SkyblockIsland i : DataManager.getManager().getIslands()) {
			if (i.getMembers().contains(this)) {
				list.add(i);
			}
		}
		return list;
	}
	
}
