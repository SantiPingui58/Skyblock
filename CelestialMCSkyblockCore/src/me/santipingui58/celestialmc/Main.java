package me.santipingui58.celestialmc;



import java.io.File;

import org.bukkit.WorldCreator;
import org.bukkit.Bukkit;
import org.bukkit.World.Environment;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.NameTagVisibility;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import me.santipingui58.celestialmc.commands.AdminCommand;
import me.santipingui58.celestialmc.commands.BlockChestCommand;
import me.santipingui58.celestialmc.commands.IslandCommand;
import me.santipingui58.celestialmc.commands.SpawnCommand;
import me.santipingui58.celestialmc.commands.SpawnerCommand;
import me.santipingui58.celestialmc.game.CelestialPlayer;
import me.santipingui58.celestialmc.game.DataManager;
import me.santipingui58.celestialmc.game.chest.ChestManager;
import me.santipingui58.celestialmc.game.stacking.StackeableManager;
import me.santipingui58.celestialmc.listener.PlayerChatEvent;
import me.santipingui58.celestialmc.listener.PlayerConnectEvent;
import me.santipingui58.celestialmc.listener.PlayerListener;
import me.santipingui58.celestialmc.listener.SkyblockListener;
import me.santipingui58.celestialmc.scoreboard.PinguiScoreboard;
import me.santipingui58.celestialmc.spawner.SpawnerManager;
import me.santipingui58.celestialmc.task.AutoBlockTask;
import me.santipingui58.celestialmc.task.MinuteTask;
import me.santipingui58.celestialmc.task.PlayerMoveTask;
import me.santipingui58.celestialmc.task.StackedBlockHologramsTask;
import me.santipingui58.celestialmc.task.TabTask;
import me.santipingui58.celestialmc.utils.Configuration;
import me.santipingui58.celestialmc.utils.FastBoard;
import net.milkbowl.vault.economy.Economy;




@SuppressWarnings("deprecation")
public class Main extends JavaPlugin {

	public static Plugin pl;
	public static String skyblock_prefix;
	public static String prefix;
	public static Configuration config,islands,data;
	private static Scoreboard scoreboard;
	 public static Economy econ = null;
	public static Team dev;
	public static Team owner;
	public static Team def;
	public static Plugin get() {
	    return pl;
	  }	
	
	
	@Override
	public void onEnable() {
		pl = this;
		 if (!setupEconomy() ) {
	            this.getLogger().severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
	            getServer().getPluginManager().disablePlugin(this);
	            return;
	        }
		 
		
		loadWorlds();
		config = new Configuration("config.yml",pl);
		data = new Configuration("data.yml",pl);
		islands = new Configuration("islands.yml",pl);	
		
		registerCommands();
		DataManager.getManager().loadPlayers();
		new BukkitRunnable() {
			@Override
			public void run() {
				ScoreboardManager manager = Bukkit.getScoreboardManager();
				scoreboard = manager.getNewScoreboard();
				
				 dev = scoreboard.registerNewTeam("dev");
				 dev.setPrefix("§5§l[DEV]§f ");
				dev.setNameTagVisibility(NameTagVisibility.ALWAYS);
				 owner = scoreboard.registerNewTeam("owner");
				 owner.setPrefix("§4§l[OWNER]§f ");
				 owner.setNameTagVisibility(NameTagVisibility.ALWAYS);
				 def = scoreboard.registerNewTeam("def");
				 def.setDisplayName("§7");
				 def.setNameTagVisibility(NameTagVisibility.ALWAYS);
				 
		skyblock_prefix = "§c[Skyblock]";
		prefix = "§b[CelestialMC]";
		File schematicsFolder =  new File( getDataFolder(),"schematics");
		if( !schematicsFolder.exists()) schematicsFolder.mkdir();
	
		registerTasks();
		registerEvents();
		DataManager.getManager().loadIslands();
		DataManager.getManager().loadBlocks();
		
		for (CelestialPlayer cplayer : DataManager.getManager().getOnlinePlayers()) {
			FastBoard board = new FastBoard(cplayer.getPlayer());
			board.updateTitle("§a§lCelestialMC");		 
			PinguiScoreboard.getScoreboard().getPlayerScoreboards().put(cplayer, board);
		}
 			}
			}.runTaskLater(Main.get(), 40L);	
			
			
	}
		
	@Override
	public void onDisable() {
		DataManager.getManager().saveData();
	}

	
	private void registerEvents() {
		getServer().getPluginManager().registerEvents(new SkyblockListener(), this);
		getServer().getPluginManager().registerEvents(new ChestManager(), this);
		getServer().getPluginManager().registerEvents(new SpawnerManager(), this);
		getServer().getPluginManager().registerEvents(new StackeableManager(), this);
		getServer().getPluginManager().registerEvents(new PlayerChatEvent(), this);
		getServer().getPluginManager().registerEvents(new PlayerListener(), this);
		getServer().getPluginManager().registerEvents(new PlayerConnectEvent(), this);
	}
	
	private void registerCommands() {
		 getCommand("island").setExecutor(new IslandCommand());
		 getCommand("spawn").setExecutor(new SpawnCommand());
		 getCommand("blockchest").setExecutor(new BlockChestCommand());
		 getCommand("spawner").setExecutor(new SpawnerCommand());
		 getCommand("admin").setExecutor(new AdminCommand());
	}
	
	
	private void registerTasks() {
		new PlayerMoveTask();
		new TabTask();
		new AutoBlockTask();
		new StackedBlockHologramsTask();
		new MinuteTask();
	}
	
	
	private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }
	
	private void loadWorlds() {
		new BukkitRunnable() {
			@Override
			public void run() {
	    getServer().createWorld(new WorldCreator("skyblock").environment(Environment.NORMAL));
	    getServer().createWorld(new WorldCreator("dragonspawn").environment(Environment.NORMAL));
			
			}
		}.runTaskLater(Main.get(), 1L);	
	}

	  
	public static Scoreboard getScoreboard() {
		return scoreboard;
	}

	
}
