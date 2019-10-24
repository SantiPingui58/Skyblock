package me.santipingui58.celestialmc.gui.island;



import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import me.santipingui58.celestialmc.Main;
import me.santipingui58.celestialmc.game.CelestialPlayer;
import me.santipingui58.celestialmc.game.skyblock.SkyblockIsland;
import me.santipingui58.celestialmc.game.skyblock.SkyblockManager;
import me.santipingui58.celestialmc.gui.MenuBuilder;
import me.santipingui58.celestialmc.utils.ItemBuilder;


public class IslandUpgradesMenu extends MenuBuilder {

		
	public IslandUpgradesMenu(Player p) {
		super("§5§lIsland Upgrades",3);
		CelestialPlayer cplayer = SkyblockManager.getManager().getCelestialPlayer(p);
		SkyblockIsland island = cplayer.getIsland();
		
		s(26, new ItemBuilder(Material.ARROW).setTitle("§cGo back").build());
		
		
		
		List<String> maxspawners = new ArrayList<String>();
		maxspawners.add(ChatColor.RED+"- Max Spawners Level I (1 Spawner)");
		maxspawners.add(ChatColor.RED+"- Max Spawners Level II (2 Spawners)");
		maxspawners.add(ChatColor.RED+"- Max Spawners Level III (3 Spawners)");
		maxspawners.add(ChatColor.RED+"- Max Spawners Level IV (5 Spawners)");
		maxspawners.add(ChatColor.RED+"- Max Spawners Level V (7 Spawners)");
		maxspawners.add(ChatColor.RED+"- Max Spawners Level VI (10 Spawners)");
		maxspawners.add(ChatColor.RED+"- Max Spawners Level VII (12 Spawners)");
		maxspawners.add(ChatColor.RED+"- Max Spawners Level VIII (15 Spawners)");
		maxspawners.add(ChatColor.RED+"- Max Spawners Level IX (20 Spawners)");
		maxspawners.add(ChatColor.RED+"- Max Spawners Level X (30 Spawners)");
		
		for (int i = 0; i<island.getMaxSpawnersLevel();i++) {	
			maxspawners.set(i,maxspawners.get(i).replace(""+ChatColor.RED, ""+ChatColor.GREEN));
		}
		
		maxspawners.add("");
		maxspawners.add("§7Level up this to be able to");
		maxspawners.add("§7have more Spawners in your");
		maxspawners.add("§7Skyblock Island.");
		maxspawners.add("");
		if (island.getMaxHoppersLevel()<10) {
		maxspawners.add("§aNext Upgrade price: §6§l$"+getMaxSpawnersPrice(island.getMaxSpawnersLevel()));
		}
		s(10,new ItemBuilder(Material.SPAWNER).setTitle("§6§lSpawners Upgrade §7- §aLevel " + intToRoman(island.getMaxSpawnersLevel())).addLores(maxspawners).build());
		
		
		
		
		List<String> maxminions = new ArrayList<String>();
		maxminions.add(ChatColor.RED+"- Max Minions Level I (1 Minion)");
		maxminions.add(ChatColor.RED+"- Max Minions Level II (2 Minions)");
		maxminions.add(ChatColor.RED+"- Max Minions Level III (3 Minions)");
		maxminions.add(ChatColor.RED+"- Max Minions Level IV (4 Minions)");
		maxminions.add(ChatColor.RED+"- Max Minions Level V (5 Minions)");
		maxminions.add(ChatColor.RED+"- Max Minions Level VI (7 Minions)");
		maxminions.add(ChatColor.RED+"- Max Minions Level VII (10 Minions)");
		maxminions.add(ChatColor.RED+"- Max Minions Level VIII (12 Minions)");
		maxminions.add(ChatColor.RED+"- Max Minions Level IX (15 Minions)");
		maxminions.add(ChatColor.RED+"- Max Minions Level X (20 Minions)");
		
		for (int i = 0; i<island.getMaxMinionsLevel();i++) {	
			maxminions.set(i,maxminions.get(i).replace(""+ChatColor.RED, ""+ChatColor.GREEN));
		}
		
		maxminions.add("");
		maxminions.add("§7Level up this to be able to");
		maxminions.add("§7have more Minions in your");
		maxminions.add("§7Skyblock Island.");
		maxminions.add("");
		if (island.getMaxMinionsLevel()<10) {
		maxminions.add("§aNext Upgrade price: §6§l$"+getMaxMinionsPrice(island.getMaxMinionsLevel()));
		}
		ItemStack player = new ItemStack(Material.PLAYER_HEAD);
		
		 SkullMeta playerMeta = (SkullMeta) player.getItemMeta();
		 playerMeta.setOwningPlayer(p);
		playerMeta.setDisplayName("§6§lMinions Upgrade §7- §aLevel " + intToRoman(island.getMaxMinionsLevel()));
		playerMeta.setLore(maxminions);
		player.setItemMeta(playerMeta);
		s(11,player);
	
		
		List<String> maxhoppers = new ArrayList<String>();
		maxhoppers.add(ChatColor.RED+"- Max Hoppers Level I (1 Hopper)");
		maxhoppers.add(ChatColor.RED+"- Max Hoppers Level II (2 Hoppers)");
		maxhoppers.add(ChatColor.RED+"- Max Hoppers Level III (3 Hoppers)");
		maxhoppers.add(ChatColor.RED+"- Max Hoppers Level IV (4 Hoppers)");
		maxhoppers.add(ChatColor.RED+"- Max Hoppers Level V (5 Hoppers)");
		maxhoppers.add(ChatColor.RED+"- Max Hoppers Level VI (7 Hoppers)");
		maxhoppers.add(ChatColor.RED+"- Max Hoppers Level VII (10 Hoppers)");
		maxhoppers.add(ChatColor.RED+"- Max Hoppers Level VIII (12 Hoppers)");
		maxhoppers.add(ChatColor.RED+"- Max Hoppers Level IX (15 Hoppers)");
		maxhoppers.add(ChatColor.RED+"- Max Hoppers Level X (20 Hoppers)");
		
		for (int i = 0; i<island.getMaxHoppersLevel();i++) {	
			maxhoppers.set(i,maxhoppers.get(i).replace(""+ChatColor.RED, ""+ChatColor.GREEN));
		}
		
		maxhoppers.add("");
		maxhoppers.add("§7Level up this to be able to");
		maxhoppers.add("§7have more Hoppers in your");
		maxhoppers.add("§7Skyblock Island.");
		maxhoppers.add("");
		if (island.getMaxHoppersLevel()<10) {
		maxhoppers.add("§aNext Upgrade price: §6§l$"+getMaxSpawnersPrice(island.getMaxHoppersLevel()));
		}
		s(12,new ItemBuilder(Material.HOPPER).setTitle("§6§lHoppers Upgrade §7- §aLevel " + intToRoman(island.getMaxHoppersLevel())).addLores(maxhoppers).build());
		
		
		List<String> generation = new ArrayList<String>();
		generation.add(ChatColor.RED+"- Cobblestone Generator Level I (7% of ores)");
		generation.add(ChatColor.RED+"- Cobblestone Generator Level II (14% of ores)");
		generation.add(ChatColor.RED+"- Cobblestone Generator Level III (21% of ores)");
		generation.add(ChatColor.RED+"- Cobblestone Generator Level IV (28% of ores)");
		generation.add(ChatColor.RED+"- Cobblestone Generator Level V (35% of ores)");
		generation.add(ChatColor.RED+"- Cobblestone Generator Level VI (42% of ores)");
		generation.add(ChatColor.RED+"- Cobblestone Generator Level VII (49% of ores)");
		generation.add(ChatColor.RED+"- Cobblestone Generator Level VIII (56% of ores)");
		generation.add(ChatColor.RED+"- Cobblestone Generator Level IX (63% of ores)");
		generation.add(ChatColor.RED+"- Cobblestone Generator Level X (70% of ores)");
		
		for (int i = 0; i<island.getGenerationLevel();i++) {	
			generation.set(i,generation.get(i).replace(""+ChatColor.RED, ""+ChatColor.GREEN));
		}
		
		generation.add("");
		generation.add("§7Level up this to genereate ores");
		generation.add("§7in your cobblestone generator with higher");
		generation.add("§7changes in your Skyblock Island.");
		generation.add("");
		if (island.getGenerationLevel()<10) {
		generation.add("§aNext Upgrade price: §6§l$"+getGenerationPrice(island.getGenerationLevel()));
		}
		s(14,new ItemBuilder(Material.COBBLESTONE).setTitle("§6§lCobblestone Generator Upgrade §7- §aLevel " + intToRoman(island.getGenerationLevel())).addLores(generation).build());
		
		List<String> space = new ArrayList<String>();
		space.add(ChatColor.RED+"- Island Expansion Level I (100x100)");
		space.add(ChatColor.RED+"- Island Expansion Level II (125x125)");
		space.add(ChatColor.RED+"- Island Expansion Level III (150x150)");
		space.add(ChatColor.RED+"- Island Expansion Level IV (175x175)");
		space.add(ChatColor.RED+"- Island Expansion Level V (250x250)");
		space.add(ChatColor.RED+"- Island Expansion Level VI (300x300)");
		space.add(ChatColor.RED+"- Island Expansion Level VII (350x350)");
		space.add(ChatColor.RED+"- Island Expansion Level VIII (400x400)");
		space.add(ChatColor.RED+"- Island Expansion Level IX (450x450)");
		space.add(ChatColor.RED+"- Island Expansion Level X (500x500)");
		
		for (int i = 0; i<island.getMaxSpaceLevel();i++) {	
			space.set(i,space.get(i).replace(""+ChatColor.RED, ""+ChatColor.GREEN));
		}
		
		space.add("");
		space.add("§7Level up this to expand");
		space.add("§7your Skyblock Island area.");
		space.add("");
		if (island.getMaxSpaceLevel()<10) {
		space.add("§aNext Upgrade price: §6§l$"+getMaxSpacePrice(island.getMaxSpaceLevel()));
		}
		s(15,new ItemBuilder(Material.GRASS_BLOCK).setTitle("§6§lIsland Expansion Upgrade §7- §aLevel " + intToRoman(island.getMaxSpaceLevel())).addLores(space).build());
		
		List<String> players = new ArrayList<String>();
		players.add(ChatColor.RED+"- Max Players Level I (1 player)");
		players.add(ChatColor.RED+"- Max Players Level II (2 players)");
		players.add(ChatColor.RED+"- Max Players Level III (3 players)");
		players.add(ChatColor.RED+"- Max Players Level IV (4 players)");
		players.add(ChatColor.RED+"- Max Players Level V (5 players)");
		players.add(ChatColor.RED+"- Max Players Level VI (7 players)");
		players.add(ChatColor.RED+"- Max Players Level VII (10 players)");
		players.add(ChatColor.RED+"- Max Players Level VIII (12 players)");
		players.add(ChatColor.RED+"- Max Players Level IX (15 players)");
		players.add(ChatColor.RED+"- Max Players Level X (20 players)");
		
		for (int i = 0; i<island.getMaxPlayersLevel();i++) {	
			players.set(i,players.get(i).replace(""+ChatColor.RED, ""+ChatColor.GREEN));
		}
		
		players.add("");
		players.add("§7Level up this to be able to");
		players.add("§7invite more players to");
		players.add("§7your Skyblock Island.");
		if (island.getMaxPlayersLevel()<10) {
		players.add("§aNext Upgrade price: §6§l$"+getMaxPlayersPrice(island.getMaxPlayersLevel()));
		}
		s(16,new ItemBuilder(Material.TOTEM_OF_UNDYING).setTitle("§6§lMax Players Upgrade §7- §aLevel " + intToRoman(island.getMaxPlayersLevel())).addLores(players).build());
	}
	

	@Override
	public void onClick(Player p, ItemStack stack, int slot,InventoryClickEvent event) {
		CelestialPlayer cplayer = SkyblockManager.getManager().getCelestialPlayer(p);
		SkyblockIsland island = cplayer.getIsland();
		if (slot==26) {
			new IslandMainMenu(p).o(p);
		} else if (slot==10) {
			if (island.getMaxSpawnersLevel()<10) {
				if (cplayer.hasMoney(getMaxSpawnersPrice(island.getMaxSpawnersLevel()))) {
					cplayer.takeMoney(island.getMaxSpawnersLevel());
					island.levelUpMaxSpawnersLevel();
					p.sendMessage(Main.skyblock_prefix + "§a You have bought the upgrade: §5§lMax Spawners Level " +intToRoman(island.getMaxSpawnersLevel())+ "§a!");
					p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
					new IslandUpgradesMenu(p).o(p);
				} else {
					p.sendMessage(Main.skyblock_prefix + "§c You do not have enough money to afford this.");
				}
			}
		}  else if (slot==11) {
			if (island.getMaxMinionsLevel()<10) {
				if (cplayer.hasMoney(getMaxMinionsPrice(island.getMaxMinionsLevel()))) {
					cplayer.takeMoney(island.getMaxMinionsLevel());
					island.levelUpMaxMinionsLevel();
					p.sendMessage(Main.skyblock_prefix + "§a You have bought the upgrade: §5§lMax Minions Level " +intToRoman(island.getMaxMinionsLevel())+ "§a!");
					p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
					new IslandUpgradesMenu(p).o(p);
				} else {
					p.sendMessage(Main.skyblock_prefix + "§c You do not have enough money to afford this.");
				}
			}
		} else if (slot==12) {
			if (island.getMaxHoppersLevel()<10) {
				if (cplayer.hasMoney(getMaxHoppersPrice(island.getMaxHoppersLevel()))) {
					cplayer.takeMoney(island.getMaxHoppersLevel());
					island.levelUpMaxHoppersLevel();
					p.sendMessage(Main.skyblock_prefix + "§a You have bought the upgrade: §5§lMax Hoppers Level " +intToRoman(island.getMaxHoppersLevel())+ "§a!");
					p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
					new IslandUpgradesMenu(p).o(p);
				} else {
					p.sendMessage(Main.skyblock_prefix + "§c You do not have enough money to afford this.");
				}
			}
		} else if (slot==14) {
			if (island.getGenerationLevel()<10) {
				if (cplayer.hasMoney(getGenerationPrice(island.getGenerationLevel()))) {
					cplayer.takeMoney(island.getGenerationLevel());
					island.levelUpGenerationLevel();
					p.sendMessage(Main.skyblock_prefix + "§a You have bought the upgrade: §5§lCobblestone Generator Level " +intToRoman(island.getGenerationLevel())+ "§a!");
					p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
					new IslandUpgradesMenu(p).o(p);
				} else {
					p.sendMessage(Main.skyblock_prefix + "§c You do not have enough money to afford this.");
				}
			}
		} else if (slot==15) {
			if (island.getMaxSpaceLevel()<10) {
				if (cplayer.hasMoney(getMaxSpacePrice(island.getMaxSpaceLevel()))) {
					cplayer.takeMoney(island.getMaxSpaceLevel());
					island.levelUpMaxSpaceLevel();
					p.sendMessage(Main.skyblock_prefix + "§a You have bought the upgrade: §5§lIsland Expansion Level " +intToRoman(island.getMaxSpaceLevel())+ "§a!");
					p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
					new IslandUpgradesMenu(p).o(p);
				} else {
					p.sendMessage(Main.skyblock_prefix + "§c You do not have enough money to afford this.");
				}
			}
		} else if (slot==16) {
			if (island.getMaxPlayersLevel()<10) {
				if (cplayer.hasMoney(getMaxPlayersPrice(island.getMaxPlayersLevel()))) {
					cplayer.takeMoney(island.getMaxPlayersLevel());
					island.levelUpMaxPlayersLevel();
					p.sendMessage(Main.skyblock_prefix + "§a You have bought the upgrade: §5§lMax Players Level " +intToRoman(island.getMaxPlayersLevel())+ "§a!");
					p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
					new IslandUpgradesMenu(p).o(p);
				} else {
					p.sendMessage(Main.skyblock_prefix + "§c You do not have enough money to afford this.");
				}
			}
		}
	}
	
	private int getMaxSpacePrice(int level) {
		if (level==1) {
			return 10000;
		} else if (level==2) {
			return 20000;
		} else if (level==3) {
			return 50000;
		} else if (level==4) {
			return 150000;
		} else if (level==5) {
			return 250000;
		} else if (level==6) {
			return 450000;
		} else if (level==7) {
			return 1000000;
		} else if (level==8) {
			return 2000000;
		} else if (level==9) {
			return 4000000;
		} else if (level==10) {
			return 10000000;
		}
		return 0;
	}
	
	private int getMaxPlayersPrice(int level) {
		if (level==1) {
			return 1000;
		} else if (level==2) {
			return 5000;
		} else if (level==3) {
			return 10000;
		} else if (level==4) {
			return 30000;
		} else if (level==5) {
			return 75000;
		} else if (level==6) {
			return 150000;
		} else if (level==7) {
			return 300000;
		} else if (level==8) {
			return 500000;
		} else if (level==9) {
			return 1000000;
		} 
		return 0;
	}
	
	
	private int getMaxSpawnersPrice(int level) {
		if (level==1) {
			return 5000;
		} else if (level==2) {
			return 25000;
		} else if (level==3) {
			return 75000;
		} else if (level==4) {
			return 150000;
		} else if (level==5) {
			return 300000;
		} else if (level==6) {
			return 500000;
		} else if (level==7) {
			return 1500000;
		} else if (level==8) {
			return 3000000;
		} else if (level==9) {
			return 5000000;
		}
		return 0;
	}
	
	private int getMaxHoppersPrice(int level) {
		if (level==1) {
			return 5000;
		} else if (level==2) {
			return 25000;
		} else if (level==3) {
			return 75000;
		} else if (level==4) {
			return 150000;
		} else if (level==5) {
			return 300000;
		} else if (level==6) {
			return 500000;
		} else if (level==7) {
			return 1500000;
		} else if (level==8) {
			return 3000000;
		} else if (level==9) {
			return 5000000;
		}
		return 0;
	}
	
	
	private int getMaxMinionsPrice(int level) {
		if (level==1) {
			return 5000;
		} else if (level==2) {
			return 25000;
		} else if (level==3) {
			return 75000;
		} else if (level==4) {
			return 150000;
		} else if (level==5) {
			return 300000;
		} else if (level==6) {
			return 500000;
		} else if (level==7) {
			return 1500000;
		} else if (level==8) {
			return 3000000;
		} else if (level==9) {
			return 5000000;
		} 
		return 0;
	}
	
	private int getGenerationPrice(int level) {
		if (level==1) {
			return 10000;
		} else if (level==2) {
			return 30000;
		} else if (level==3) {
			return 100000;
		} else if (level==4) {
			return 250000;
		} else if (level==5) {
			return 500000;
		} else if (level==6) {
			return 1500000;
		} else if (level==7) {
			return 5000000;
		} else if (level==8) {
			return 10000000;
		} else if (level==9) {
			return 50000000;
		}
		return 0;
	}
	
	private String intToRoman(int i) {
		if (i==1) {
			return "I";
		} else if (i==2) {
			return "II";
		} else if (i==3) {
			return "III";
		} else if (i==4) {
			return "IV";
		} else if (i==5) {
			return "V";
		} else if (i==6) {
			return "VI";
		} else if (i==7) {
			return "VII";
		} else if (i==8) {
			return "VIII";
		} else if (i==9) {
			return "IX";
		} else if (i==10) {
			return "X";
		}
		
		return null;
	}
	
	}



