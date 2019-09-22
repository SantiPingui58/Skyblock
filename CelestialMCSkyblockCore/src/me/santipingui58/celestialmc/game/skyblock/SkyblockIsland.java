package me.santipingui58.celestialmc.game.skyblock;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.Map.Entry;

import org.bukkit.ChatColor;
import org.bukkit.Location;

import me.santipingui58.celestialmc.game.CelestialPlayer;
import me.santipingui58.celestialmc.game.stacking.SimpleBlock;
import me.santipingui58.celestialmc.game.stacking.StackedBlock;

public class SkyblockIsland {

	private UUID uuid;
	private Location spawnpoint;	
	private Location home;
	
	private int money;
	private List<String> transactions_history;
	
	private CelestialPlayer owner;
	private Location warp;
	private HashMap<CelestialPlayer, List<PlayerPermissions>> permissions = new HashMap<CelestialPlayer,List<PlayerPermissions>>();
	private List<CelestialPlayer> members = new ArrayList<CelestialPlayer>();
	private List<CelestialPlayer> invitations = new ArrayList<CelestialPlayer>();
	private boolean warpenabled;
	
	
	private List<StackedBlock> stackedblocks = new ArrayList<StackedBlock>();
	private List<SimpleBlock> blocks = new ArrayList<SimpleBlock>();
	
	private int generation;
	private int space;
	private int maxspawners;
	private int maxhoppers;
	private int maxminions;
	private int maxplayers;
	public SkyblockIsland(UUID uuid,int money,List<String> transactions,CelestialPlayer owner,Location spawnpoint,int space,Location warp,
			boolean warpenabled,List<CelestialPlayer> members, HashMap<CelestialPlayer,List<PlayerPermissions>> permissions,
			int generation, int maxspawners, int maxhoppers,int maxminions,int maxplayers) {
		
		if (generation==0) {
			this.generation = 1;
		} else {
			this.generation = generation;
		}

		if (maxspawners==0) {
			this.maxspawners = 1;
		} else {
			this.maxspawners = maxspawners;
		}
		
		if (maxhoppers==0) {
			this.maxhoppers = 1;
		} else {
			this.maxhoppers = maxhoppers;
		}
		
		if (maxplayers==0) {
			this.maxplayers = 1;
		} else {
			this.maxplayers = maxplayers;
		}
		if (maxminions==0) {
			this.maxminions = 1;
		} else {
			this.maxminions = maxminions;
		}
		
		if (space==0) {
			this.space = 100;
		} else {
			this.space = space;
		}
		if (uuid==null) {
			this.uuid = UUID.randomUUID();
		} else {
			this.uuid = uuid;
		}
		if (members==null) {
		this.members = new ArrayList<CelestialPlayer>();
		} else {
		this.members = members;
		}
		this.money = money;
		this.transactions_history = transactions;
		this.owner = owner;
		this.spawnpoint = spawnpoint;
		if (warp==null) {
			this.warp = this.spawnpoint;
		} else {
			this.warp = warp;
		}
		
		this.warpenabled = warpenabled;
		this.permissions = permissions;
	}
	
	public List<StackedBlock> getStackedBlocks() {
		return this.stackedblocks;
	}
	
	public List<SimpleBlock> getBlocks() {
		return this.blocks;
	}
	
	public List<CelestialPlayer> getInvitations() {
		return this.invitations;
	}
	
	public List<String> getTransactions() {
		List<String> list = new ArrayList<String>();
		for (String s : this.transactions_history) {
			s = ChatColor.translateAlternateColorCodes('&', s);
			list.add(s);
		}
		Collections.reverse(list);
		return list;
	}
	
	public List<CelestialPlayer> getMembers() {
		return this.members;
	}
	
	public int getMaxPlayersLevel() {
		return this.maxplayers;
	}
	
	public int getMoney() {
		return this.money;
	}
	
	public void setMoneys(int i) {
		this.money = i;
	}
	
	public void depositMoney(int amount,String name, String date) {
		this.money = this.money+ amount;
		this.transactions_history.add("§a+ §6$"+amount + " §7by §b" + name + " §7("+date+")");
	}
	
	public void withdrawMoney(int amount,String name, String date) {
		this.money = this.money- amount;
		this.transactions_history.add("§c- §6$"+amount + " §7by §b" + name + " §7("+date+")");
	}
	
	public boolean hasMoney(int amount) {
		if (this.money>=amount) {
			return true;
		}
		return false;
	}
	
	public void interest() {
		int interest = this.money/200;
		this.money = this.money + interest;
		Date now = new Date();
		SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy");	
		this.transactions_history.add("§a+ §6$"+interest + " §7by §bBank interest §7("+format.format(now)+")");
	}
	
	public int getMaxPlayers() {
		if (this.maxplayers==1) {
			return 1;
		} else if (this.maxplayers==2) {
			return 2;
		} else if (this.maxplayers==3) {
			return 3;
		} else if (this.maxplayers==4) {
			return 4;
		} else if (this.maxplayers==5) {
			return 5;
		} else if (this.maxplayers==6) {
			return 7;
		} else if (this.maxplayers==7) {
			return 10;
		} else if (this.maxplayers==8) {
			return 12;
		} else if (this.maxplayers==9) {
			return 15;
		} else if (this.maxplayers==10) {
			return 20;
		}
		return 1;
	}
	public void levelUpMaxPlayersLevel() {
		this.maxplayers++;
	}
	public int getGenerationLevel() {
		return this.generation;
	}
	public void levelUpGenerationLevel() {
		this.generation++;
	}
	public int getMaxSpawnersLevel() {
		return this.maxspawners;
	}
	public void levelUpMaxSpawnersLevel() {
		this.maxspawners++;
	}
	public int getMaxHoppersLevel() {
		return this.maxhoppers;
	}
	public void levelUpMaxHoppersLevel() {
		this.maxhoppers++;
	}
	
	public int getMaxMinionsLevel() {
		return this.maxminions;
	}
	public void levelUpMaxMinionsLevel() {
		this.maxminions++;
	}
	public int getMaxSpaceLevel() {
 		return this.space;
	}
	
	public void levelUpMaxSpaceLevel() {
		this.space++;
	}
	
	public int getSpace() {
		if (this.space==1) {
			return 100;
		} else if (this.space==2) {
			return 125;
		} else if (this.space==3) {
			return 150;
		} else if (this.space==4) {
			return 175;
		} else if (this.space==5) {
			return 250;
		} else if (this.space==6) {
			return 300;
		} else if (this.space==7) {
			return 350;
		} else if (this.space==8) {
			return 400;
		} else if (this.space==9) {
			return 450;
		} else if (this.space==10) {
			return 500;
		}
		return 100;
	}
	public boolean isWarpEnabled() {
		return this.warpenabled;
	}
	
	public void enableWarp() {
		this.warpenabled = true;
	}
	
	public void disableWarp() {
		this.warpenabled = false;
	}
	public HashMap<CelestialPlayer,List<PlayerPermissions>> getPermissionsHashMap() {
		return this.permissions;
	}
	public List<PlayerPermissions> getAllPermissions(CelestialPlayer cplayer) {
		
		Iterator<Entry<CelestialPlayer, List<PlayerPermissions>>> it = permissions.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry<CelestialPlayer,List<PlayerPermissions>> pair = (Map.Entry<CelestialPlayer,List<PlayerPermissions>>)it.next();
	       CelestialPlayer cp = pair.getKey();
	       if (cp.equals(cplayer)) {
	     return pair.getValue();	      
	    }
	    }
	    return null;
	}
	
	public boolean hasPermission(CelestialPlayer cplayer, PlayerPermissions perm) {
		if (cplayer.getUUID().toString().equalsIgnoreCase(this.owner.getUUID().toString())) {
			return true;
		}
		Iterator<Entry<CelestialPlayer, List<PlayerPermissions>>> it = permissions.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry<CelestialPlayer,List<PlayerPermissions>> pair = (Map.Entry<CelestialPlayer,List<PlayerPermissions>>)it.next();
	       CelestialPlayer cp = pair.getKey();
	       if (cp.equals(cplayer)) {
	      if (pair.getValue().contains(perm)) {
	    	  return true;
	      } else {
	    	  return false;
	      }
	    }
	    }
	    return false;
	}
	
	public void addPermission(CelestialPlayer cplayer, PlayerPermissions perm) {
		if (this.members.contains(cplayer)) {
		List<PlayerPermissions> perms = null;
		if (permissions.containsKey(cplayer)) {
			 perms = permissions.get(cplayer);			
		} else {
			perms = new ArrayList<PlayerPermissions>();		
		}
		if (!perms.contains(perm)) {
		perms.add(perm);
		}	
		permissions.put(cplayer, perms);
	}
	}
	
	
	public void removePermission(CelestialPlayer cplayer,PlayerPermissions perm) {
		if (this.members.contains(cplayer)) {
		List<PlayerPermissions> perms = null;
		if (permissions.containsKey(cplayer)) {
			 perms = permissions.get(cplayer);			
		} else {
			perms = new ArrayList<PlayerPermissions>();		
		}
		perms.remove(perm);
		permissions.put(cplayer, perms);
		}
	}
	
	
	public Location getHome() {
		if (this.home==null) {
			return spawnpoint;
		}
		return home;
	}
	
		
	public Location getWarp() {
		return this.warp;
	}
	
	public void setWarp(Location l) {
		this.warp = l;
	}
	
	public void setHome(Location l) {
		this.home = l;
	}
	
	public UUID getUUID() {
		return this.uuid;
	}
	
	public void setSpace(int space) {
		this.space = space;
	}
	public Location getSpawnPoint() {
		return this.spawnpoint;
	}
	
	public CelestialPlayer getOwner() {
		return this.owner;
	}
	
	
}
