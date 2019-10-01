package me.santipingui58.celestialmc.game.spawner;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.santipingui58.celestialmc.game.CelestialPlayer;

public class CelestialSpawner {

	private CelestialPlayer owner;
	private Location location;
	private SpawnerType type;
	private UUID uuid;
	private boolean isactived;
	
	
	private int level;

	private int spawnedmobs;
	
	private List<CelestialSpawner> stackedspawners = new ArrayList<CelestialSpawner>();
	
	public CelestialSpawner(UUID uuid, CelestialPlayer owner, Location location, SpawnerType type,int level) {
		if (uuid==null) {
			this.uuid = UUID.randomUUID();
		} else {
			this.uuid = uuid;	
		}
		
		this.owner = owner;
		this.location = location;
		this.type = type;
		this.level = level;
	}
	
	
	public List<CelestialSpawner> getStackedSpawners() {
		return this.stackedspawners;
	}
	
	public int getSpawnedMobs() {
		return this.spawnedmobs;
	}
	
	public void setSpawnedMobs(int i) {
		this.spawnedmobs = i;
	}
	public int getLevel() {
		return this.level;
	}
	
	public void levelUp( ) {
		updateSpawnerData();
		if (this.level<4) {
			this.level = this.level+1;
		}
	}
	public UUID getUUID() {
		return this.uuid;
	}
	
	public CelestialPlayer getOwner() {
		return this.owner;
	}
	
	public Location getLocation() {
		return this.location;
	}
	public void place(Location l) {
		this.location = l;
	}
	
	public void pickUp() {
		this.location = null;
	}
	
	public boolean isPlaced() {
		if (this.location==null) {
			return false;
		} else {
			return true;
		}
	}
	
	public boolean isActivated() {
		return this.isactived;
	}
	
	public void activate() {
		this.isactived = true;
	}
	
	public void desactivate() {
		this.isactived = false;
	}
	
	public SpawnerType getType() {
		return this.type;
	}
	
	public String getTitle() {
		String type = this.type.toString();
		type = type.replace("_", " ");
		return type;
	}
		
	public ItemStack getItem() {
		ItemStack item = new ItemStack(Material.SPAWNER);
		ItemMeta meta = item.getItemMeta();
		List<String> lore = new ArrayList<String>();
		lore.add("§7Place this spawner do to ");
		lore.add("§7spawner stuff.");
		lore.add(" ");
		lore.add("§3§lLevel: " + this.level);
		lore.add("§7Spawners stacked: " + this.stackedspawners.size());
		lore.add(" ");
		lore.add("§cOriginal Owner: §3" + Bukkit.getOfflinePlayer(getOwner().getUUID()).getName());
		lore.add(" ");
		lore.add("§8"+getUUID().toString());
		lore.add(" ");
		meta.setLore(lore);
		meta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);		
		meta.setDisplayName("§c§l" + getTitle() + " Spawner");
		item.setItemMeta(meta);
		return item;
	}
	
	public void updateSpawnerData() {
		if (this.location!=null) {
		 CreatureSpawner creaturespawner = (CreatureSpawner) this.location.getBlock().getState();			 
		 creaturespawner.setSpawnedType(this.type.toEntityType());
		 int ticks = (int) (getDelay()*20); 
		 if (ticks>0) {
			 if (creaturespawner.getMinSpawnDelay()<ticks) {
				 creaturespawner.setMaxSpawnDelay(ticks);
				 creaturespawner.setMinSpawnDelay(ticks);	
			 } else {
				 creaturespawner.setMinSpawnDelay(ticks);
				 creaturespawner.setMaxSpawnDelay(ticks);
			 }
			 
		 } else {
			 creaturespawner.setMaxSpawnDelay(1);
			 creaturespawner.setMinSpawnDelay(1);
		 }
		 creaturespawner.setRequiredPlayerRange(getRange());
		 creaturespawner.setSpawnCount(getSpawnCount());
		 creaturespawner.update();
	}
	}
	
	public double getDelay() {
		 double s = this.getStackedSpawners().size()*0.25;
		 int delay = 60;
		 double t = delay-s;
		 if (this.level==2) {
			 t = t- t*0.1;
		 } else if (this.level==3) {
			 t=t - t*0.25;
		 } else if (this.level==4) {
			 t=t- t*0.5;
		 }
		 if (t<0) {
			 t = 0.1;
		 }
		return Math.floor(t * 100) / 100;
	}
	
	public int getRange() {
		 int range = 10;
		 if (this.level==2) {
			 range = 15;
		 } else if (this.level==3) {
			 range = 20;
		 } else if (this.level==4) {
			 range = 30;
		 }
		 return range;
	}
	
	public int getSpawnCount() {
		int spawncount = 1;	 
		 if (this.level==2) {
			 spawncount = 3;
		 } else if (this.level==3) {
			 spawncount=5;
		 } else if (this.level==4) {
			 spawncount=10;
		 }
		 return spawncount;
	}
}
