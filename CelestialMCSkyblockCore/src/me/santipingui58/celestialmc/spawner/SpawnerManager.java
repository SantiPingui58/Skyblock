package me.santipingui58.celestialmc.spawner;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.SpawnerSpawnEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import me.santipingui58.celestialmc.Main;
import me.santipingui58.celestialmc.game.CelestialPlayer;
import me.santipingui58.celestialmc.gui.spawner.SpawnerMenu;

public class SpawnerManager implements Listener {

	
	private static SpawnerManager manager;	
	 public static SpawnerManager getManager() {
	        if (manager == null)
	        	manager = new SpawnerManager();
	        return manager;
	    }
	
	private List<CelestialSpawner> spawners = new ArrayList<CelestialSpawner>();
	
	public List<CelestialSpawner> getSpawners() {
	return this.spawners;
	}
	
	 public void createSpawner(CelestialPlayer owner,SpawnerType type) {
		 CelestialSpawner spawner = new CelestialSpawner(UUID.randomUUID(), owner, null, type);
		 spawners.add(spawner);	
		 owner.getPlayer().getInventory().addItem(spawner.getItem());
		 		 
	 }
	 
	 public CelestialSpawner getSpawnerByItem(ItemStack item) {
		 for (CelestialSpawner spawner : SpawnerManager.getManager().getSpawners()) {
			 if (spawner.getItem().isSimilar(item)) {
				 return spawner;
			 }
		 }
		 return null;
	 }
	
	 @EventHandler
	 public void onInteract(PlayerInteractEvent e) {
		 Player p = e.getPlayer();
		 if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
		 if (e.getClickedBlock().getType().equals(Material.SPAWNER)) {
			 for (CelestialSpawner spawner : SpawnerManager.getManager().getSpawners()) {
				 if (spawner.isPlaced()) {
				 if (spawner.getLocation().equals(e.getClickedBlock().getLocation())) {
					 new SpawnerMenu(p,spawner).o(p);
					 return;
				 }
				 } 
			 }
	 }
		 }
	 }
	 
	 @EventHandler
	 public void onPlace(BlockPlaceEvent e) {
		 if (e.getItemInHand().getType().equals(Material.SPAWNER)) {
			 ItemStack item = e.getItemInHand();
			 if (item.getItemMeta().getLore().size()>0) {
				 String string = ChatColor.stripColor(item.getItemMeta().getLore().get(5));			
				 UUID uuid = UUID.fromString(string);
		 for (CelestialSpawner spawner: SpawnerManager.getManager().getSpawners()) {
			 if (spawner.getUUID().toString().equalsIgnoreCase(uuid.toString())) {
				 spawner.place(e.getBlock().getLocation());
				 CreatureSpawner creaturespawner = (CreatureSpawner) e.getBlock().getState();			 
				 creaturespawner.setSpawnedType(spawner.getType().toEntityType());
				 creaturespawner.update();
				 
				 e.getPlayer().sendMessage(Main.skyblock_prefix+ " §aYou have placed an Spawner!");
				 if (e.getPlayer().getInventory().contains(spawner.getItem())) {
					 e.getPlayer().getInventory().remove(spawner.getItem());
				 }
				 return;
			 } 
		 }
			 } 
	 }
	 }
	 
	 @EventHandler
	 public void onBreak(BlockBreakEvent e) {
		 if (e.getBlock().getType().equals(Material.SPAWNER)) {
			 for (CelestialSpawner spawner: SpawnerManager.getManager().getSpawners()) {
				 if (spawner.isPlaced()) {
				 if (spawner.getLocation().equals(e.getBlock().getLocation())) {
					 e.getPlayer().sendMessage("Pick Up");
					 e.setCancelled(true);
					 e.getBlock().setType(Material.AIR);
					 e.getBlock().getWorld().dropItem(e.getBlock().getLocation(), spawner.getItem());
					 spawner.pickUp();			
					 return;
				 }
				 } 
			 }
	 }
	 }
	 
	 
	 @EventHandler
	 public void onSpawn(SpawnerSpawnEvent e) {
		for (CelestialSpawner spawner : SpawnerManager.getManager().getSpawners()) {
			if (spawner.isPlaced()) {
				if (spawner.getLocation().equals(e.getSpawner().getBlock().getLocation())) {
					if (!spawner.isActivated()) {
						e.setCancelled(true);
					} else {					
						CreatureSpawner creaturespawner = e.getSpawner();
						if (spawner.getCurrent()!=null) {
						creaturespawner.setSpawnedType(spawner.getCurrent().getType().toEntityType());	
						}
						if (!spawner.getSpawners().isEmpty()) {
							if (spawner.getCurrent()==null) {
								spawner.setCurrent(spawner);
							} else {
								spawner.setCurrent(spawner.getNext(spawner.getCurrent()));
							}
						}
					}
					return;
				}
			}
		}
	 }
}
