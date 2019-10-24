package me.santipingui58.celestialmc.gui.spawner;




import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import me.santipingui58.celestialmc.game.CelestialPlayer;
import me.santipingui58.celestialmc.game.Result;
import me.santipingui58.celestialmc.game.skyblock.SkyblockManager;
import me.santipingui58.celestialmc.game.spawner.CelestialSpawner;
import me.santipingui58.celestialmc.game.spawner.SpawnerManager;
import me.santipingui58.celestialmc.gui.MenuBuilder;
import me.santipingui58.celestialmc.utils.ItemBuilder;
import me.santipingui58.celestialmc.utils.Utils;



public class SpawnerManageMenu extends MenuBuilder {

	private HashMap<CelestialPlayer,List<CelestialSpawner>> hashmap = new HashMap<CelestialPlayer,List<CelestialSpawner>>();
	
	
	public SpawnerManageMenu(Player p,CelestialSpawner spawner) {
		super("§aAdd/Remove Spawners",4);
		CelestialPlayer cplayer = SkyblockManager.getManager().getCelestialPlayer(p);
		List<CelestialSpawner> inventory = new ArrayList<CelestialSpawner>();
		for (ItemStack i : p.getInventory().getContents()) {
			if (i!=null) {
				CelestialSpawner sp = SpawnerManager.getManager().getSpawnerByItem(i);
				if (sp!=null) {
					if (spawner.getType().equals(sp.getType()) && spawner.getLevel()==sp.getLevel()) {
					inventory.add(sp);
					}
				}
			}
		}
		hashmap.put(cplayer, inventory);
		
		s(1,new ItemBuilder(Material.SPAWNER).setTitle("§aAdd 1 Spawner").build());
		s(3,new ItemBuilder(Material.SPAWNER).setTitle("§aAdd 16 Spawners").build());
		s(5,new ItemBuilder(Material.SPAWNER).setTitle("§aAdd 32 Spawners").build());
		s(7,new ItemBuilder(Material.SPAWNER).setTitle("§aAdd all Spawners").build());
		
		s(19, new ItemBuilder(Material.SPAWNER).setTitle("§cRemove 1 Spawner").build());
		s(21, new ItemBuilder(Material.SPAWNER).setTitle("§cRemove 16 Spawners").build());
		s(23, new ItemBuilder(Material.SPAWNER).setTitle("§cRemove 32 Spawners").build());
		s(25, new ItemBuilder(Material.SPAWNER).setTitle("§cRemove all Spawners").build());
		
		s(35, new ItemBuilder(Material.ARROW).setTitle("§cGo back").build());
	}
	

	@Override
	public void onClick(Player p, ItemStack stack, int slot,InventoryClickEvent event) {
		CelestialPlayer cplayer = SkyblockManager.getManager().getCelestialPlayer(p);
		if (hashmap.containsKey(cplayer)) {
		List<CelestialSpawner> list = hashmap.get(cplayer);
		if (slot==1 ||slot==3||slot==5||slot==7) {
		if (!list.isEmpty()) {
		if (slot==1) {
			if (list.size()>=1) {
				cplayer.getOpenSpawnerGUI().getStackedSpawners().add(list.get(0));
				p.getInventory().removeItem(list.get(0).getItem());
				return;
			} else {
				cplayer.sendMessage("You dont have enough compatibile Spawners on your inventory to do this.", Result.DENY);
				return;
			}
		} else if (slot==3) {
			if (list.size()>=16) {
				for (int i = 0;i<16;i++) {
				cplayer.getOpenSpawnerGUI().getStackedSpawners().add(list.get(i));
				p.getInventory().removeItem(list.get(i).getItem());			
				}
				return;
			} else {
				cplayer.sendMessage("You dont have enough compatibile Spawners on your inventory to do this.", Result.DENY);
				return;
			}
		} else if (slot==5) {
			if (list.size()>=32) {
				for (int i = 0;i<32;i++) {
				cplayer.getOpenSpawnerGUI().getStackedSpawners().add(list.get(i));
				p.getInventory().removeItem(list.get(i).getItem());
				}
				return;
			} else {
				cplayer.sendMessage("You dont have enough compatibile Spawners on your inventory to do this.", Result.DENY);
				return;
			}
		} else if (slot==7) {
				for (int i = 0;i<=list.size()-1;i++) {
				cplayer.getOpenSpawnerGUI().getStackedSpawners().add(list.get(i));
				p.getInventory().removeItem(list.get(i).getItem());
				}	
				return;
		}
		} else {
			cplayer.sendMessage("You dont have any compatible Spawner on your inventory.", Result.DENY);
		}
		new SpawnerManageMenu(p,cplayer.getOpenSpawnerGUI()).o(p);
		} else if (slot==19 ||slot==21||slot==23||slot==25) {
		if (cplayer.getOpenSpawnerGUI().getStackedSpawners().size()>0) {
		if (slot==19) {
			if (Utils.getUtils().getEmptySlots(p.getInventory())>=1) {
				p.getInventory().addItem(cplayer.getOpenSpawnerGUI().getStackedSpawners().get(0).getItem());
				cplayer.getOpenSpawnerGUI().getStackedSpawners().remove(0);
				
			} else {
				cplayer.sendMessage("You dont have enough space on your inventory to do this.", Result.DENY);
			}
		} else if (slot==21) {
			if (Utils.getUtils().getEmptySlots(p.getInventory())>=16) {
				List<CelestialSpawner> l = new ArrayList<CelestialSpawner>();
				for (int i = 0; i<16;i++) {
					if (i<cplayer.getOpenSpawnerGUI().getStackedSpawners().size()) {
					l.add(cplayer.getOpenSpawnerGUI().getStackedSpawners().get(i));
					p.getInventory().addItem(cplayer.getOpenSpawnerGUI().getStackedSpawners().get(i).getItem());
				} else break;
				}
				for (CelestialSpawner cs : l) {
					cplayer.getOpenSpawnerGUI().getStackedSpawners().remove(cs);
				}
			} else {
				cplayer.sendMessage("You dont have enough space on your inventory to do this.", Result.DENY);
			}
		} else if (slot==23) {
			if (Utils.getUtils().getEmptySlots(p.getInventory())>=32) {
				List<CelestialSpawner> l = new ArrayList<CelestialSpawner>();
				for (int i = 0; i<32;i++) {
					if (i<cplayer.getOpenSpawnerGUI().getStackedSpawners().size()) {
					l.add(cplayer.getOpenSpawnerGUI().getStackedSpawners().get(i));
					p.getInventory().addItem(cplayer.getOpenSpawnerGUI().getStackedSpawners().get(i).getItem());
				} else break;
				} 
				for (CelestialSpawner cs : l) {
					cplayer.getOpenSpawnerGUI().getStackedSpawners().remove(cs);
				}
			} else {
				cplayer.sendMessage("You dont have enough space on your inventory to do this.", Result.DENY);
			}
		} else if (slot==25) {
			List<CelestialSpawner> l = new ArrayList<CelestialSpawner>();
				for (int i = 0; i<=cplayer.getOpenSpawnerGUI().getStackedSpawners().size();i++) {
					if (i<cplayer.getOpenSpawnerGUI().getStackedSpawners().size()) {
					p.getInventory().addItem(cplayer.getOpenSpawnerGUI().getStackedSpawners().get(i).getItem());
					l.add(cplayer.getOpenSpawnerGUI().getStackedSpawners().get(i));
				} else break;
				}
				for (CelestialSpawner cs : l) {
					cplayer.getOpenSpawnerGUI().getStackedSpawners().remove(cs);
				}
		}
		} else {
			cplayer.sendMessage("This Spawner doesnt have any Stacked Spawner.", Result.DENY);
		}
		new SpawnerManageMenu(p,cplayer.getOpenSpawnerGUI()).o(p);
		} else if (slot==35) {
			new SpawnerMenu(p,cplayer.getOpenSpawnerGUI()).o(p);
		}
		}
	}
	

	
	
	}



