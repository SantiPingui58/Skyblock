package me.santipingui58.celestialmc.game;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import me.santipingui58.celestialmc.Main;
import me.santipingui58.celestialmc.game.hopper.CelestialHopper;
import me.santipingui58.celestialmc.game.rank.Rank;
import me.santipingui58.celestialmc.game.skyblock.SkyblockIsland;
import me.santipingui58.celestialmc.game.spawner.CelestialSpawner;
import me.santipingui58.celestialmc.utils.Utils;


public class CelestialPlayer {
 
	private UUID uuid;
	private PlayerLocation location;
	private Rank rank;
	private Location prevLocation;
	
	
	private boolean fly;
	private boolean expboost;
	private boolean seeds;
	private boolean cropskey;
	private boolean miningkey;
	private boolean haste;
	private boolean tree;
	private CelestialSpawner openspawnergui;
	private CelestialHopper openhoppergui;
	private int worthtoppage;
		
	public CelestialPlayer(UUID uuid, boolean fly) {
		this.rank = Rank.SKYGOD;
		this.uuid = uuid;		
		this.fly = fly;
		this.location = PlayerLocation.SPAWN;
	}
	
	public Rank getRank() {
		return this.rank;
	}
	
	public void rankUp() {
		if (this.rank.equals(Rank.DEFAULT)) {
			this.rank = Rank.SKYBORN;
			Utils.getUtils().addPermission(this, "essentials.kit.skyborn");
		} else if (this.rank.equals(Rank.SKYBORN)) {
			this.rank = Rank.RECRUIT;
		} else if (this.rank.equals(Rank.RECRUIT)) {
			this.rank = Rank.TIMEKEEPER;
			this.expBoost(true);
		} else if (this.rank.equals(Rank.TIMEKEEPER)) {
			this.rank = Rank.BALLER;
		} else if (this.rank.equals(Rank.BALLER)) {
			this.rank = Rank.ACTIVIST;
		} else if (this.rank.equals(Rank.ACTIVIST)) {
		this.autoSeed(true);
			this.rank = Rank.CRAFTSMAN;
			Utils.getUtils().addPermission(this, "essentials.workbench");
		} else if (this.rank.equals(Rank.CRAFTSMAN)) {
			this.rank = Rank.BLACKSMITH;
			Utils.getUtils().addPermission(this, "pci.anvil");
		} else if (this.rank.equals(Rank.BLACKSMITH)) {
			this.rank = Rank.LUMBERJACK;
		} else if (this.rank.equals(Rank.LUMBERJACK)) {
			this.rank = Rank.TROOPER;	
			Utils.getUtils().addPermission(this, "essentials.hat");
		} else if (this.rank.equals(Rank.TROOPER)) {		
			this.rank = Rank.DRIFTER;
			Utils.getUtils().addPermission(this, "essentials.kit.skyhero");
			Utils.getUtils().addPermission(this, "essentials.enderchest");
		} else if (this.rank.equals(Rank.SKYHERO)) {
			Utils.getUtils().addPermission(this, "essentials.ext");
			Utils.getUtils().addPermission(this, "essentials.kit.skylord");
			this.rank = Rank.SKYLORD;
		} else if (this.rank.equals(Rank.SKYLORD)) {	
			this.rank = Rank.SKYLEGEND;
			Utils.getUtils().addPermission(this, "essentials.kit.skylegend");
		} else if (this.rank.equals(Rank.SKYLEGEND)) {			
			Utils.getUtils().addPermission(this, "essentials.top");
			this.rank = Rank.AIRBORN;		
		} else if (this.rank.equals(Rank.AIRBORN)) {
			this.rank = Rank.FARMER;
		} else if (this.rank.equals(Rank.FARMER)) {
			this.rank = Rank.INFINITY;
			Utils.getUtils().addPermission(this, "celestialmc.fly");
			Utils.getUtils().addPermission(this, "celestialmc.chatcolor");			
		} else if (this.rank.equals(Rank.INFINITY)) {
			this.rank = Rank.HUSTLER;
		} else if (this.rank.equals(Rank.HUSTLER)) {
			this.rank = Rank.SKYGOD;
			Utils.getUtils().addPermission(this, "essentials.kit.skygod");
			Utils.getUtils().addPermission(this, "essentials.feed");
			Utils.getUtils().addPermission(this, "essentials.heal");
			Utils.getUtils().addPermission(this, "essentials.fix.all");
		}
	}
	public int getWorthTopPage() {
		return this.worthtoppage;
	}
	
	public void setWorthTopPage(int i) {
		this.worthtoppage = i;
	}
	public CelestialSpawner getOpenSpawnerGUI() {
		return this.openspawnergui;
	}
	
	public void setOpenSpawnerGUI(CelestialSpawner spawner) {
		this.openspawnergui = spawner;
	}
	
	public CelestialHopper getOpenHopperGUI() {
		return this.openhoppergui;
	}
	
	public void setOpenHopperGUI(CelestialHopper hopper) {
		this.openhoppergui = hopper;
	}
	
	public boolean isFlying() {
		return this.fly;
	}
	
	public void fly() {
		this.fly = true;
		this.getPlayer().setAllowFlight(true);
	}
	
	public void stopFly() {
		this.fly = false;
		this.getPlayer().setAllowFlight(false);
	}
	
	public boolean isHasteBoost() {
		return this.haste;
	}
	
	public void hasteBoost(boolean b) {
		this.haste = b;
	}
	
	public boolean isAutoChop() {
		return this.tree;
	}
	
	public void autoChop(boolean b) {
		this.tree = b;
		if (b) {
			Utils.getUtils().addPermission(this,"tg.user");
		} else {
			Utils.getUtils().removePermission(this,"tg.user");
		}
	}
	
	public boolean isFarmerKey() {
		return this.cropskey;
	}
	public void farmerKey(boolean b) {
		this.cropskey = b;
	}
	
	public boolean isMiningKey() {
		return this.miningkey;
	}
	
	public void miningKey(boolean b) {
		this.miningkey = b;
	}
	public boolean isExpBoosted() {
		return this.expboost;
	}
	
	public void expBoost(boolean b) {
		this.expboost = b;
	}
	
	public boolean isAutoSeedsEnabled() {
		return this.seeds;
	}
	
	public void autoSeed(boolean b) {
		this.seeds = b;
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
	
	
	// !$ &b
	// !% &e
	
	public void sendMessage(String msg, Result result) {
		if (isOnline()) {
			msg = msg.replace("!$", "§b");
			msg = msg.replace("!%", "§e");
			if (result.equals(Result.ALLOW)) {			
				this.getPlayer().sendMessage(Main.skyblock_prefix + " §a" +ChatColor.translateAlternateColorCodes('&', msg));
			} else {
				this.getPlayer().sendMessage(Main.skyblock_prefix + " §c" +ChatColor.translateAlternateColorCodes('&', msg));
			}
		}
	}
}
