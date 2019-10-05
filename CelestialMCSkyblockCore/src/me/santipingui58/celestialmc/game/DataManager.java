package me.santipingui58.celestialmc.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import me.santipingui58.celestialmc.Main;
import me.santipingui58.celestialmc.game.chest.AutoBlockChest;
import me.santipingui58.celestialmc.game.chest.AutoSellChest;
import me.santipingui58.celestialmc.game.chest.ChestManager;
import me.santipingui58.celestialmc.game.skyblock.PlayerPermissions;
import me.santipingui58.celestialmc.game.skyblock.SkyblockIsland;
import me.santipingui58.celestialmc.game.skyblock.SkyblockManager;
import me.santipingui58.celestialmc.game.spawner.CelestialSpawner;
import me.santipingui58.celestialmc.game.spawner.SpawnerManager;
import me.santipingui58.celestialmc.game.spawner.SpawnerType;
import me.santipingui58.celestialmc.game.stacking.SimpleBlock;
import me.santipingui58.celestialmc.game.stacking.StackeableBlockType;
import me.santipingui58.celestialmc.game.stacking.StackeableManager;
import me.santipingui58.celestialmc.game.stacking.StackedBlock;
import me.santipingui58.celestialmc.utils.Utils;

public class DataManager {
	
	private static DataManager manager;	
	 public static DataManager getManager() {
	        if (manager == null)
	        	manager = new DataManager();
	        return manager;
	    }
	
	 
	 private List<SkyblockIsland> islands = new ArrayList<SkyblockIsland>();
	 private List<CelestialPlayer> players = new ArrayList<CelestialPlayer>();
	 public List<SkyblockIsland> getIslands() {
		 return this.islands;
	 }
	 
	 public List<CelestialPlayer> getPlayers() {
		 return this.players;
	 }
	 
	 public List<CelestialPlayer> getOnlinePlayers() {
		 List<CelestialPlayer> list = new ArrayList<CelestialPlayer>();
		 for (CelestialPlayer cplayer : DataManager.getManager().getPlayers()) {
			 if (cplayer.isOnline()) {
				 list.add(cplayer);
			 }
		 }
		 return list;
	 }
	 
	 public void savePlayerData() {
		 for (CelestialPlayer cplayer : this.players) {
			 Main.data.getConfig().set("players."+cplayer.getUUID().toString()+".loc", cplayer.getLocation().toString());
			 Main.data.getConfig().set("players."+cplayer.getUUID().toString()+".fly", cplayer.isFlying());
		 }
		 Main.data.saveConfig();
	 }
	 
	 public void saveData() {
		 savePlayerData();
		 saveIslands();		 
		 saveBlocks();
		 saveSpawners();
	 }
	 
	 public void loadData() {
		 loadPlayers();
		 loadIslands();
	 }
	 
	 public void saveSpawners() {
		 Main.data.getConfig().set("spawners", null);
		 for (CelestialSpawner spawner : SpawnerManager.getManager().getSpawners()) {
			 String uuid = spawner.getUUID().toString();
			 int level = spawner.getLevel();
			 int count = spawner.getSpawnedMobs();
			 SpawnerType type = spawner.getType();
			 boolean activated = spawner.isActivated();
			 CelestialPlayer owner = spawner.getOwner();
			 List<String> stackedspawners = new ArrayList<String>();
			 for (CelestialSpawner s : spawner.getStackedSpawners()) {
				 stackedspawners.add(s.getUUID().toString());
			 }
			 if (spawner.getLocation()!=null) {
				 Main.data.getConfig().set("spawners."+uuid+".location", Utils.getUtils().setLoc(spawner.getLocation(), false));
			 }
			 
			 Main.data.getConfig().set("spawners."+uuid+".owner", owner.getUUID().toString());
			 Main.data.getConfig().set("spawners."+uuid+".level", level);
			 Main.data.getConfig().set("spawners."+uuid+".count", count);
			 Main.data.getConfig().set("spawners."+uuid+".type", type.toString());
			 Main.data.getConfig().set("spawners."+uuid+".activated", activated);
			 Main.data.getConfig().set("spawners."+uuid+".stackedspawners", stackedspawners);	 
		 }
		 Main.data.saveConfig();
	 }
	 
	 
	 public void saveBlocks() {		 
		 Main.data.getConfig().set("autoblockchests", null);
		 Main.data.getConfig().set("autosellchests", null);
		 Main.data.getConfig().set("stackedblocks", null);
		 Main.data.getConfig().set("simpleblocks", null);
		 
		for (AutoBlockChest chests : ChestManager.getManager().getAutoBlockChests()) {
			UUID uuid = chests.getUUID();
			Location location = chests.getLocation();
			CelestialPlayer owner = chests.getOwner();
			int times = chests.getTimesUsed();
			boolean isplaced =chests.isPlaced();
			Main.data.getConfig().set("autoblockchests."+uuid+".isplaced", isplaced);
			if(isplaced) {
				Main.data.getConfig().set("autoblockchests."+uuid+".location", Utils.getUtils().setLoc(location, false));
			}
			Main.data.getConfig().set("autoblockchests."+uuid+".owner", owner.getUUID().toString());
			Main.data.getConfig().set("autoblockchests."+uuid+".times", times);
			
		} 
		for (AutoSellChest chests : ChestManager.getManager().getAutoSellChests()) {
			UUID uuid = chests.getUUID();
			Location location = chests.getLocation();
			CelestialPlayer owner = chests.getOwner();
			int times = chests.getTimesUsed();
			int money = chests.getMoney();
			boolean isplaced =chests.isPlaced();
			Main.data.getConfig().set("autosellchests."+uuid+".isplaced", isplaced);
			if(isplaced) {
				Main.data.getConfig().set("autosellchests."+uuid+".location", Utils.getUtils().setLoc(location, false));
			}
			Main.data.getConfig().set("autosellchests."+uuid+".owner", owner.getUUID().toString());
			Main.data.getConfig().set("autosellchests."+uuid+".times", times);
			Main.data.getConfig().set("autosellchests."+uuid+".money", money);
			
		} 
		
		for (StackedBlock sblock : StackeableManager.getManager().getStackedBlocks()) {
			String uuid = sblock.getUUID().toString();
			Location location  = sblock.getLocation();
			int amount = sblock.getAmount();
			StackeableBlockType type = sblock.getType();
			Main.data.getConfig().set("stackedblocks."+uuid+".location", Utils.getUtils().setLoc(location, true));
			Main.data.getConfig().set("stackedblocks."+uuid+".amount", amount);
			Main.data.getConfig().set("stackedblocks."+uuid+".type", type.toString());
		}
		
		for (SimpleBlock sblock : StackeableManager.getManager().getSimpleBlocks()) {
			String uuid = sblock.getUUID().toString();
			Location location  = sblock.getLocation();
			StackeableBlockType type = sblock.getType();
			Main.data.getConfig().set("simpleblocks."+uuid+".location", Utils.getUtils().setLoc(location, true));
			Main.data.getConfig().set("simpleblocks."+uuid+".type", type.toString());
		}
		
		
		
		
		Main.data.saveConfig();
	 }
	 
	 
	 public void loadSpawners() {
		 if (Main.data.getConfig().contains("spawners")) {
			 Set<String> spawners = Main.data.getConfig().getConfigurationSection("spawners").getKeys(false);
			 for (String s : spawners) {
				 UUID uuid = UUID.fromString(s);
				 CelestialPlayer owner = SkyblockManager.getManager().getCelestialPlayer(UUID.fromString(Main.data.getConfig().getString("spawners."+s+".owner")));
				 int amount = Main.data.getConfig().getInt("spawners."+s+".amount");
				 int level = Main.data.getConfig().getInt("spawners."+s+".level");
				 boolean activated = Main.data.getConfig().getBoolean("spawners."+s+".activated");
				 Location location = null;
				 if (Main.data.getConfig().contains("spawners."+s+".location")) {
					 location = Utils.getUtils().getLoc(Main.data.getConfig().getString("spawners."+s+".location"),false,false);
				 }
				 SpawnerType type = SpawnerType.valueOf(Main.data.getConfig().getString("spawners."+s+".type"));
				 
				CelestialSpawner spawner = new CelestialSpawner(uuid, owner, location, type,level);
				spawner.setSpawnedMobs(amount);
				if (activated) {
					spawner.activate();
				} else {
					spawner.desactivate();
				}
				 SpawnerManager.getManager().getSpawners().add(spawner);
			 }
			 
			 loadStackedSpawners();
		 }
	 }
	 
	 private void loadStackedSpawners() {
		 Set<String> sp = Main.data.getConfig().getConfigurationSection("spawners").getKeys(false);
		 for (String s : sp) {			 
		 if (Main.data.getConfig().getStringList("spawners."+s+".stackedspawners").size()>0) {
			 CelestialSpawner spawner = SpawnerManager.getManager().getSpawner(UUID.fromString(s));
			 List<String>  list = Main.data.getConfig().getStringList("spawners."+s+".stackedspawners");	 
		 for (String str : list) {
			 UUID uuid = UUID.fromString(str);
			 CelestialSpawner stacked = SpawnerManager.getManager().getSpawner(uuid);
			 spawner.getStackedSpawners().add(stacked);
		 }
		 }
		 }
	 }
	 
	 public void loadBlocks() {
		 if (Main.data.getConfig().contains("autoblockchests")) {
		 Set<String> abchests = Main.data.getConfig().getConfigurationSection("autoblockchests").getKeys(false);
		 for (String s : abchests) {
			 UUID uuid = UUID.fromString(s);
			 CelestialPlayer owner = SkyblockManager.getManager().getCelestialPlayer(UUID.fromString(Main.data.getConfig().getString("autoblockchests."+s+".owner")));
			 int times = Main.data.getConfig().getInt("autoblockchests."+s+".times");
			 Location location = null;
			 if (Main.data.getConfig().contains("autoblockchests."+s+".location")) {
				 location = Utils.getUtils().getLoc(Main.data.getConfig().getString("autoblockchests."+s+".location"),false,false);
			 }
			 
			 AutoBlockChest abchest = new AutoBlockChest(uuid,owner,location,times);
			 if (location!=null) {
				 abchest.place(location);
			 }
			 ChestManager.getManager().getAutoBlockChests().add(abchest);			 
		 }
	 }
		 
		 if (Main.data.getConfig().contains("autosellchests")) {
			 Set<String> abchests = Main.data.getConfig().getConfigurationSection("autosellchests").getKeys(false);
			 for (String s : abchests) {
				 UUID uuid = UUID.fromString(s);
				 CelestialPlayer owner = SkyblockManager.getManager().getCelestialPlayer(UUID.fromString(Main.data.getConfig().getString("autosellchests."+s+".owner")));
				 int times = Main.data.getConfig().getInt("autosellchests."+s+".times");
				 int money = Main.data.getConfig().getInt("autosellchests."+s+".money");
				 Location location = null;
				 if (Main.data.getConfig().contains("autosellchests."+s+".location")) {
					 location = Utils.getUtils().getLoc(Main.data.getConfig().getString("autosellchests."+s+".location"),false,false);
				 }
				 
				 AutoSellChest abchest = new AutoSellChest(uuid,owner,location,times,money);
				 if (location!=null) {
					 abchest.place(location);
				 }
				 ChestManager.getManager().getAutoSellChests().add(abchest);			 
			 }
		 }
		 
	   if (Main.data.getConfig().contains("stackedblocks")) {
		   Set<String> stackedblocks = Main.data.getConfig().getConfigurationSection("stackedblocks").getKeys(false);
		   for (String s : stackedblocks) {
			   UUID uuid = UUID.fromString(s);
			   Location location = Utils.getUtils().getLoc(Main.data.getConfig().getString("stackedblocks."+s+".location"), false, false);
			   StackeableBlockType type = StackeableBlockType.valueOf(Main.data.getConfig().getString("stackedblocks."+s+".type"));
			   int amount = Main.data.getConfig().getInt("stackedblocks."+s+".amount");
			   StackedBlock sblock = new StackedBlock(uuid, type, location, amount);
			   StackeableManager.getManager().getStackedBlocks().add(sblock);
			   
		   }
	   }
	   
	   if (Main.data.getConfig().contains("simpleblocks")) {
		   Set<String> stackedblocks = Main.data.getConfig().getConfigurationSection("simpleblocks").getKeys(false);
		   for (String s : stackedblocks) {
			   UUID uuid = UUID.fromString(s);
			   Location location = Utils.getUtils().getLoc(Main.data.getConfig().getString("simpleblocks."+s+".location"), false, false);
			   StackeableBlockType type = StackeableBlockType.valueOf(Main.data.getConfig().getString("simpleblocks."+s+".type"));
			   SimpleBlock sblock = new SimpleBlock(uuid, type, location);
			   StackeableManager.getManager().getSimpleBlocks().add(sblock);
			   
		   }
	   }
	 }
	 public void loadPlayers() {

		 if (Main.data.getConfig().contains("players")) {
			 Set<String> players = Main.data.getConfig().getConfigurationSection("players").getKeys(false);
			 for (String p : players) {
				boolean fly = Main.data.getConfig().getBoolean("players."+p+".fly");
			 CelestialPlayer cplayer = new CelestialPlayer(UUID.fromString(p),fly);
			PlayerLocation plocation = PlayerLocation.valueOf(Main.data.getConfig().getString("players."+p.toString()+".loc"));
			cplayer.setLocation(plocation);
			
			this.players.add(cplayer);
		 }
			
		 }
		 Bukkit.getServer().getLogger().info(this.players.size()+" players loaded!");
	 }
	 
	 public CelestialPlayer createPlayerData(UUID uuid) {
		 CelestialPlayer cplayer = new CelestialPlayer(uuid,false);
		 cplayer.setLocation(PlayerLocation.SPAWN);
		 this.players.add(cplayer);
		 return cplayer;
	 }
	 
 	 public void loadIslands() {
 		 SkyblockManager.getManager().getNextLocations().clear();
 		 int x = Main.config.getConfig().getInt("x-value");
 		 int z = Main.config.getConfig().getInt("z-value");
 		 SkyblockManager.getManager().getNextLocations().add(x);
 		 SkyblockManager.getManager().getNextLocations().add(z);
 		 if (!Main.islands.getConfig().contains("islands")) return;
		 Set<String> islands = Main.islands.getConfig().getConfigurationSection("islands").getKeys(false);
		 for (String s : islands) {
			 UUID uuid = UUID.fromString(Main.islands.getConfig().getString("islands."+s+".owner"));
			 CelestialPlayer cplayer = SkyblockManager.getManager().getCelestialPlayer(uuid);	
			 int money = Main.islands.getConfig().getInt("islands."+s+".money");
			 List<String> transactions = Main.islands.getConfig().getStringList("islands."+s+".transactions");
			 Location spawn = Utils.getUtils().getLoc(Main.islands.getConfig().getString("islands."+s+".spawn"), true,false);
			 Location home = Utils.getUtils().getLoc(Main.islands.getConfig().getString("islands."+s+".home"), true,false);
			 Location warp = Utils.getUtils().getLoc(Main.islands.getConfig().getString("islands."+s+".warp"), true,false);
			 boolean warpenabled =	Main.islands.getConfig().getBoolean("islands."+s+".warpenabled");
			 
			 int space = Main.islands.getConfig().getInt("islands."+s+".space");
			 int generation = Main.islands.getConfig().getInt("islands."+s+".generator");
			 int maxspawners = Main.islands.getConfig().getInt("islands."+s+".spawners");
			 int maxhoppers = Main.islands.getConfig().getInt("islands."+s+".hoppers");
			 int maxminions = Main.islands.getConfig().getInt("islands."+s+".minions");
			 int maxplayers = Main.islands.getConfig().getInt("islands."+s+".players");		 
			 List<CelestialPlayer> members = new ArrayList<CelestialPlayer>();
			 if (Main.islands.getConfig().contains("islands."+s+".members")) {
				 List<String> memb = Main.islands.getConfig().getStringList("islands."+s+".members");
				 for (String st : memb) {
					 CelestialPlayer cp = SkyblockManager.getManager().getCelestialPlayer(UUID.fromString(st));
					 members.add(cp);
				 }
			 }
			
			 HashMap<CelestialPlayer,List<PlayerPermissions>> perms = new HashMap<CelestialPlayer,List<PlayerPermissions>>();
			 if (Main.islands.getConfig().contains("islands."+s+".permissions")) {
			 Set<String> p = Main.islands.getConfig().getConfigurationSection("islands."+s+".permissions").getKeys(false);	 
			 for (String c : p) {
				 List<String> list = Main.islands.getConfig().getStringList("islands."+s+".permissions."+c);
				 List<PlayerPermissions> permslist = new ArrayList<PlayerPermissions>();
				 for (String str : list) {
					 permslist.add(PlayerPermissions.valueOf(str));
				 }
				
				 perms.put(SkyblockManager.getManager().getCelestialPlayer(UUID.fromString(c)), permslist);
			 }
			 }
			 SkyblockIsland island = new SkyblockIsland(UUID.fromString(s),money,transactions,cplayer,spawn,space,warp,warpenabled,members,perms,generation,maxspawners,maxhoppers,maxminions,maxplayers);
			 island.setHome(home);
			 this.islands.add(island);
		 }
			 
		 
		 Bukkit.getServer().getLogger().info(this.islands.size()+" islands loaded!");
	 }
	 
 	 
 	 
	 public void saveIslands() {
		 Main.config.getConfig().set("x-value", SkyblockManager.getManager().getNextLocations().get(0));
		 Main.config.getConfig().set("z-value",SkyblockManager.getManager().getNextLocations().get(1));
		 for (SkyblockIsland island : this.islands) {
			 UUID owner = island.getOwner().getUUID();
			 Location spawn = island.getSpawnPoint();
			 Location home = island.getHome();
			 Location warp = island.getWarp();
			 String uuid = island.getUUID().toString();
			 boolean warpenabled = island.isWarpEnabled();
			 
			 int hoppers = island.getMaxHoppersLevel();
			 int minions = island.getMaxMinionsLevel();
			 int generator = island.getGenerationLevel();
			 int players = island.getMaxPlayersLevel();
			 int space = island.getMaxSpaceLevel();
			 int spawners = island.getMaxSpawnersLevel();
			 
			 int money = island.getMoney();
			 List<String> transactions = island.getTransactions();
			Main.islands.getConfig().set("islands."+uuid+".owner", owner.toString());
			Main.islands.getConfig().set("islands."+uuid+".spawn", Utils.getUtils().setLoc(spawn, true));
			Main.islands.getConfig().set("islands."+uuid+".home", Utils.getUtils().setLoc(home, true));
			Main.islands.getConfig().set("islands."+uuid+".money", money);
			Main.islands.getConfig().set("islands."+uuid+".transactions", transactions);
			Main.islands.getConfig().set("islands."+uuid+".warp", Utils.getUtils().setLoc(warp, true));
			Main.islands.getConfig().set("islands."+uuid+".warpenabled", warpenabled);
						
			Main.islands.getConfig().set("islands."+uuid+".hoppers", hoppers);
			Main.islands.getConfig().set("islands."+uuid+".minions", minions);
			Main.islands.getConfig().set("islands."+uuid+".generator", generator);
			Main.islands.getConfig().set("islands."+uuid+".players", players);
			Main.islands.getConfig().set("islands."+uuid+".space", space);
			Main.islands.getConfig().set("islands."+uuid+".spawners", spawners);
			
			Main.islands.getConfig().set("islands."+uuid+".members", null);
			Main.islands.getConfig().set("islands."+uuid+".permissions", null);
			List<String> members = new ArrayList<String>();
			for (CelestialPlayer cp : island.getMembers()) {
				members.add(cp.getUUID().toString());
			}
			Main.islands.getConfig().set("islands."+uuid+".members", members);
			Iterator<Entry<CelestialPlayer, List<PlayerPermissions>>> it = island.getPermissionsHashMap().entrySet().iterator();
		    while (it.hasNext()) {
		        Map.Entry<CelestialPlayer,List<PlayerPermissions>> pair = (Map.Entry<CelestialPlayer,List<PlayerPermissions>>)it.next();
		       CelestialPlayer cp = pair.getKey();
		       List<PlayerPermissions> i = pair.getValue();	    
		       List<String> list = new ArrayList<String>();
		       for (PlayerPermissions pp : i) {
		    	   list.add(pp.toString());
		       }
		       Main.islands.getConfig().set("islands."+uuid+".permissions."+cp.getUUID().toString(), list);
		    }  
			
		 }
		 Main.config.saveConfig();
		 Main.islands.saveConfig();
	 }
	 
		
		public void deleteIsland(SkyblockIsland island) {
			this.islands.remove(island);
			for (StackedBlock sblock : island.getStackedBlocks()) {
				StackeableManager.getManager().removeStackedBlock(sblock);
			}
			Main.islands.getConfig().set("islands."+island.getUUID(), null);
			Main.islands.saveConfig();
		}
	 
}
