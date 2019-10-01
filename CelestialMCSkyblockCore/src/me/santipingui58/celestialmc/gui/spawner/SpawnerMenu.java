package me.santipingui58.celestialmc.gui.spawner;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import me.santipingui58.celestialmc.game.CelestialPlayer;
import me.santipingui58.celestialmc.game.skyblock.SkyblockManager;
import me.santipingui58.celestialmc.game.spawner.CelestialSpawner;
import me.santipingui58.celestialmc.gui.MenuBuilder;
import me.santipingui58.celestialmc.utils.ItemBuilder;



public class SpawnerMenu extends MenuBuilder {

	public SpawnerMenu(Player p,CelestialSpawner spawner) {
		super("§a" + spawner.getTitle() + " Spawner",3);
		CelestialPlayer cplayer = SkyblockManager.getManager().getCelestialPlayer(p);
		cplayer.setOpenSpawnerGUI(spawner);
		if (spawner.isActivated()) {
			s(11,new ItemBuilder(Material.LIME_DYE).setTitle("§eState: §aON §7(Click to turn off the spawner.)").build());
		} else {
			s(11,new ItemBuilder(Material.GRAY_DYE).setTitle("§eState: §cOFF §7(Click to turn on the spawner.)").build());
		}
		
		s(18,new ItemBuilder(Material.REDSTONE_TORCH).setTitle("§eInformation")
				.addLore("§aSpawner player range §7is the")
				.addLore("§7amount of blocks a player needs")
				.addLore("§7to be close to a Spawner to spawn mobs.")
				.addLore("")
				.addLore("§aSpawner Spawn Amount §7is the")
				.addLore("§7amount of mobs the spawner will try")
				.addLore("§7to spawn at same time.")
				.addLore("")
				.addLore("§aSpawner delay §7is the time in")
				.addLore("§7seconds the spawner takes to spawn")
				.addLore("§7a new mob.")
				.addLore("")
				.addLore("§7For every Spawner you stack, §aSpawner delay")
				.addLore("§7will be reduced by §e0.25(s)§7.").build());
		
		
		s(4,new ItemBuilder(Material.SPAWNER).setTitle("§2§lSpawner info")
				.addLore("§7Spawner Mob Type: §c§l" +spawner.getTitle())
				.addLore("§7Spawned mobs: §b" + spawner.getSpawnedMobs())
				.addLore("§3Stacked Spawners: §f" + spawner.getStackedSpawners().size())
				.addLore("")
				.addLore("§7Spawner Player Range: §e" + spawner.getRange())
				.addLore("§7Spawner Spawn Amount: §e" + spawner.getSpawnCount())
				.addLore("§7Spawner delay: §e" + spawner.getDelay()+"(s)")
				.addLore("")
				.addLore("§7Stack spawners or upgrade it to")
				.addLore("§7improve the Spawner perfomance!").build());
		
		s(13,new ItemBuilder(Material.CHEST).setTitle("§6§lAdd/Remove Spawners").build());
		s(15,new ItemBuilder(Material.NETHER_STAR).setTitle("§5§lSpawner Upgrades").build());
		
		s(26,new ItemBuilder(Material.ARROW).setTitle("§cClose").build());
	}
	

	@Override
	public void onClick(Player p, ItemStack stack, int slot,InventoryClickEvent event) {
		CelestialPlayer cplayer = SkyblockManager.getManager().getCelestialPlayer(p);
		CelestialSpawner spawner = cplayer.getOpenSpawnerGUI();
		if (slot==11) {
			if (spawner.isActivated()) {
				spawner.desactivate();
			} else {
				spawner.activate();
			}
			new SpawnerMenu(p,spawner).o(p);
		} else if (slot==13) {
			new SpawnerManageMenu(p,spawner).o(p);
		}else if (slot==15) {
			new SpawnerUpgradesMenu(p,spawner).o(p);
		} else if (slot==26) {
			p.closeInventory();
		}
	}
	
	
	
	
	}



