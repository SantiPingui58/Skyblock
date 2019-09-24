package me.santipingui58.celestialmc.gui.spawner;




import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import me.santipingui58.celestialmc.game.CelestialPlayer;
import me.santipingui58.celestialmc.game.Result;
import me.santipingui58.celestialmc.game.skyblock.SkyblockManager;
import me.santipingui58.celestialmc.game.spawner.CelestialSpawner;
import me.santipingui58.celestialmc.gui.MenuBuilder;
import me.santipingui58.celestialmc.utils.ItemBuilder;



public class SpawnerUpgradesMenu extends MenuBuilder {


	public SpawnerUpgradesMenu(Player p,CelestialSpawner spawner) {
		super("§5Spawner Upgrades",3);
		int size = spawner.getStackedSpawners().size()+1;
		
		s(26, new ItemBuilder(Material.ARROW).setTitle("§cGo back").build());
		
		s(10,new ItemBuilder(Material.SPAWNER).setTitle("§aSpawner Level I")
				.addLore("§7- Spawner Base Delay: §e60(s)")
				.addLore("§7- Spawner Spawn Amount: §e1")
				.addLore("§7- Spawner Player Range: §e10 blocks")
				.addLore("")
				.addLore("§aThis Spawner is already Level I or greater!").build());
		
		if (spawner.getLevel()>=2) {
			s(12,new ItemBuilder(Material.SPAWNER).setTitle("§aSpawner Level II")
					.addLore("§7- Spawner Delay Upgrade: §e10% off")
					.addLore("§7- Spawner Spawn Amount: §e3")
					.addLore("§7- Spawner Player Range: §e15 blocks")
					.addLore("")
					.addLore("§aThis Spawner is already Level II or greater!").build());
		} else {
			s(12,new ItemBuilder(Material.SPAWNER).setTitle("§cSpawner Level II")
					.addLore("§7- Spawner Delay Upgrade: §e10% off")
					.addLore("§7- Spawner Spawn Amount: §e3")
					.addLore("§7- Spawner Player Range: §e15 blocks")
					.addLore("")
					.addLore("§aUpgrade price §7(x"+size+" Spawners)§a: §6§l$" + getPrice(spawner)).build());
		}  
		
		if (spawner.getLevel()>=3) {
			s(14,new ItemBuilder(Material.SPAWNER).setTitle("§aSpawner Level III")
					.addLore("§7- Spawner Delay Upgrade: §e25% off")
					.addLore("§7- Spawner Spawn Amount: §e5")
					.addLore("§7- Spawner Player Range: §e20 blocks")
					.addLore("")
					.addLore("§aThis Spawner is already Level III or greater!").build());
		} else {
			s(14,new ItemBuilder(Material.SPAWNER).setTitle("§cSpawner Level III")
					.addLore("§7- Spawner Delay Upgrade: §e25% off")
					.addLore("§7- Spawner Spawn Amount: §e5")
					.addLore("§7- Spawner Player Range: §e20 blocks")
					.addLore("")
					.addLore("§aUpgrade price §7(x"+size+" Spawners)§a: §6§l$" + getPrice(spawner)).build());
		} 
		
		if (spawner.getLevel()>=4) {
			s(16,new ItemBuilder(Material.SPAWNER).setTitle("§aSpawner Level IV")
					.addLore("§7- Spawner Delay Upgrade: §e50% off")
					.addLore("§7- Spawner Spawn Amount: §e10")
					.addLore("§7- Spawner Player Range: §e30 blocks")
					.addLore("")
					.addLore("§aThis Spawner is already Level IV!").build());
		} else {
			s(16,new ItemBuilder(Material.SPAWNER).setTitle("§cSpawner Level IV")
					.addLore("§7- Spawner Delay Upgrade: §e50% off")
					.addLore("§7- Spawner Spawn Amount: §e10")
					.addLore("§7- Spawner Player Range: §e30 blocks")
					.addLore("")
					.addLore("§aUpgrade price §7(x"+size+" Spawners)§a: §6§l$" + getPrice(spawner)).build());
		} 
	}
	
	private int getPrice(CelestialSpawner spawner) {
		int size = spawner.getStackedSpawners().size()+1;
		if (spawner.getLevel()==1) {
			return 1000*size;
		}  else if (spawner.getLevel()==2) {
			return 10000*size;
		} else if (spawner.getLevel()==3) {
			return 100000*size;
		}
		return 0;
	}
	
	
	@Override
	public void onClick(Player p, ItemStack stack, int slot,InventoryClickEvent event) {
		CelestialPlayer cplayer = SkyblockManager.getManager().getCelestialPlayer(p);
		CelestialSpawner spawner = cplayer.getOpenSpawnerGUI();
		if (slot==26) {
			new SpawnerMenu(p,spawner).o(p);
		} else if (slot==12) {
			if (spawner.getLevel()==1) {
				if (cplayer.hasMoney(getPrice(spawner))) {
					spawner.levelUp();
					for (CelestialSpawner s : spawner.getStackedSpawners()) {
						s.levelUp();
					}
					cplayer.takeMoney(getPrice(spawner));
					cplayer.sendMessage("You have succesfully upgraded this Spawner to Level II!", Result.ALLOW);
					new SpawnerUpgradesMenu(p,spawner);
				} else {
					cplayer.sendMessage("You do not have enough money to do this.", Result.DENY);
				}
			}  else {
				cplayer.sendMessage("You have already have this upgrade.", Result.DENY);
			}
		} else if (slot==14) {
			if (spawner.getLevel()==2) {
				if (cplayer.hasMoney(getPrice(spawner))) {
					spawner.levelUp();
					for (CelestialSpawner s : spawner.getStackedSpawners()) {
						s.levelUp();
					}
					cplayer.takeMoney(getPrice(spawner));
					cplayer.sendMessage("You have succesfully upgraded this Spawner to Level III!", Result.ALLOW);
					new SpawnerUpgradesMenu(p,spawner);
				} else {
					cplayer.sendMessage("You do not have enough money to do this.", Result.DENY);
				}
			} else if (spawner.getLevel()==1) {
				cplayer.sendMessage("You need Level II upgrade before buy this!.", Result.DENY);
			} else {
				cplayer.sendMessage("You have already have this upgrade.", Result.DENY);
			}
		}  else if (slot==16) {
			if (spawner.getLevel()==3) {
				if (cplayer.hasMoney(getPrice(spawner))) {
					spawner.levelUp();
					for (CelestialSpawner s : spawner.getStackedSpawners()) {
						s.levelUp();
					}
					cplayer.takeMoney(getPrice(spawner));
					cplayer.sendMessage("You have succesfully upgraded this Spawner to Level IV!", Result.ALLOW);
					new SpawnerUpgradesMenu(p,spawner);
				} else {
					cplayer.sendMessage("You do not have enough money to do this.", Result.DENY);
				}
			} else if (spawner.getLevel()<=2) {
				cplayer.sendMessage("You need Level III upgrade before buy this!.", Result.DENY);
			} else {
				cplayer.sendMessage("You have already have this upgrade.", Result.DENY);
			}
		}
		
	}
	
	
	
	
	}



