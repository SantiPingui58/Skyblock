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
import me.santipingui58.celestialmc.game.chest.ChestManager;
import me.santipingui58.celestialmc.game.skyblock.PlayerPermissions;
import me.santipingui58.celestialmc.game.skyblock.SkyblockIsland;
import me.santipingui58.celestialmc.game.skyblock.SkyblockManager;
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
		 }
		 Main.data.saveConfig();
	 }
	 
	 public void saveData() {
		 savePlayerData();
		 saveIslands();		 
		 saveBlocks();
	 }
	 
	 public void loadData() {
		 loadPlayers();
		 loadIslands();
	 }
	 
	 public void saveBlocks() {
		for (AutoBlockChest chests : ChestManager.getManager().getAutoBlockChests()) {
			UUID uuid = chests.getUUID();
			Location location = chests.getLocation();
			CelestialPlayer owner = chests.getOwner();
			int times = chests.getTimesUsed();
			boolean isplaced =chests.isPlaced();
			Main.data.getConfig().set("autoblockchests."+uuid+".isplaced", isplaced);
			if(isplaced) {
				Main.data.getConfig().set("autoblockchests."+uuid+".location", Utils.setLoc(location, false));
			}
			Main.data.getConfig().set("autoblockchests."+uuid+".owner", owner.getUUID().toString());
			Main.data.getConfig().set("autoblockchests."+uuid+".times", times);
			
		} 
		Main.data.saveConfig();
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
				 location = Utils.getLoc(Main.data.getConfig().getString("autoblockchests."+s+".location"));
			 }
			 
			 AutoBlockChest abchest = new AutoBlockChest(uuid,owner,location,times);
			 ChestManager.getManager().getAutoBlockChests().add(abchest);			 
		 }
	 }
	 }
	 public void loadPlayers() {

		 if (Main.data.getConfig().contains("players")) {
			 Set<String> players = Main.data.getConfig().getConfigurationSection("players").getKeys(false);
			 for (String p : players) {
			 CelestialPlayer cplayer = new CelestialPlayer(UUID.fromString(p));
			PlayerLocation plocation = PlayerLocation.valueOf(Main.data.getConfig().getString("players."+p.toString()+".loc"));
			cplayer.setLocation(plocation);
			
			this.players.add(cplayer);
		 }
			
		 }
		 Bukkit.getServer().getLogger().info(this.players.size()+" players loaded!");
	 }
	 
	 public CelestialPlayer createPlayerData(UUID uuid) {
		 CelestialPlayer cplayer = new CelestialPlayer(uuid);
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
			 Location spawn = Utils.getLoc(Main.islands.getConfig().getString("islands."+s+".spawn"), true);
			 Location home = Utils.getLoc(Main.islands.getConfig().getString("islands."+s+".home"), true);
			 Location warp = Utils.getLoc(Main.islands.getConfig().getString("islands."+s+".warp"), true);
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
			Main.islands.getConfig().set("islands."+uuid+".spawn", Utils.setLoc(spawn, true));
			Main.islands.getConfig().set("islands."+uuid+".home", Utils.setLoc(home, true));
			Main.islands.getConfig().set("islands."+uuid+".money", money);
			Main.islands.getConfig().set("islands."+uuid+".transactions", transactions);
			Main.islands.getConfig().set("islands."+uuid+".warp", Utils.setLoc(warp, true));
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
