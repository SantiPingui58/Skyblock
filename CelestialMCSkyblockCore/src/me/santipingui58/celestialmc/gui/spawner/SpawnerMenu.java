package me.santipingui58.celestialmc.gui.spawner;







import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import me.santipingui58.celestialmc.gui.MenuBuilder;
import me.santipingui58.celestialmc.spawner.CelestialSpawner;
import me.santipingui58.celestialmc.spawner.SpawnerManager;
import me.santipingui58.celestialmc.utils.ItemBuilder;



public class SpawnerMenu extends MenuBuilder {

		private HashMap<Player,CelestialSpawner> hashmap = new HashMap<Player,CelestialSpawner>();
		
	public SpawnerMenu(Player p,CelestialSpawner spawner) {
		super("§a" + spawner.getTitle() + " Spawner",4);
		hashmap.put(p, spawner);
		for (int i = 0; i <36;i++) {
			if (i<=8 ||  i>=27) {
			s(i,new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).setTitle(" ").build());
			} else {
				if (i<=spawner.getMaxSpawnersAllowed()+8) {
					
					if (i<=spawner.getSpawners().size()+8) {
						s(i,spawner.getSpawners().get(i-9).getItem());
					} else {
					s(i,new ItemBuilder(Material.YELLOW_STAINED_GLASS_PANE).setTitle("§aPut a Spawner here to combine them in 1 Spawner!").build());
					}
				} else {
					s(i,new ItemBuilder(Material.RED_STAINED_GLASS_PANE).setTitle("§cThis slot is blocked.").build());
				}
			}
		}
		
		if (spawner.isActivated()) {
			s(3,new ItemBuilder(Material.LIME_DYE).setTitle("§eState: §aON §7(Click to turn off the spawner.)").build());
		} else {
			s(3,new ItemBuilder(Material.GRAY_DYE).setTitle("§eState: §cOFF §7(Click to turn on the spawner.)").build());
		}
		
		
		
		List<String> spawnerlore = new ArrayList<String>();	
		s(4,new ItemBuilder(Material.SPAWNER).setTitle("§c" + spawner.getTitle() + " Spawner").addLores(spawnerlore).build());
				
		List<String> upgradeslore = new ArrayList<String>();	
		upgradeslore.add("§7Click to open the upgrades menu.");
		s(5,new ItemBuilder(Material.NETHER_STAR).setTitle("§bUpgrades").addLores(upgradeslore).build());
	}
	

	@Override
	public void onClick(Player p, ItemStack stack, int slot,InventoryClickEvent event) {
		CelestialSpawner spawner = hashmap.get(p);
		if (slot==4) {
			event.setCancelled(true);
		} else if (slot==3) {
			if (spawner.isActivated()) {
				spawner.desactivate();
			} else {
				spawner.activate();
			}
			new SpawnerMenu(p,spawner).o(p);
		} else if (slot==5) {
			new SpawnerUpgradesMenu(p,spawner).o(p);
		} else {
			if (stack.getType().equals(Material.YELLOW_STAINED_GLASS_PANE)) {
				if (event.getCursor()!=null) {
					if (event.getCursor().getType().equals(Material.SPAWNER)) {
						CelestialSpawner otherspawner = SpawnerManager.getManager().getSpawnerByItem(event.getCursor());
						spawner.getSpawners().add(otherspawner);
						p.setItemOnCursor(null);
						new SpawnerMenu(p,spawner).o(p);
					}
				}
			} else if (stack.getType().equals(Material.SPAWNER)) {		
				event.setCancelled(true);
				p.closeInventory();
				p.getInventory().addItem(stack);
				CelestialSpawner otherspawner = SpawnerManager.getManager().getSpawnerByItem(stack);
				spawner.getSpawners().remove(otherspawner);			
			}
		}
	}
	
	
	
	
	}



