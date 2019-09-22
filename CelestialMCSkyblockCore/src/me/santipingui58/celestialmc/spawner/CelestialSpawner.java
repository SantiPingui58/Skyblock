package me.santipingui58.celestialmc.spawner;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
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
	private List<CelestialSpawner> spawners = new ArrayList<CelestialSpawner>();
	private CelestialSpawner current;
	private int maxspawnersallowed;
	
	public CelestialSpawner(UUID uuid, CelestialPlayer owner, Location location, SpawnerType type) {
		if (uuid==null) {
		this.uuid = uuid;
		} else {
			this.uuid = UUID.randomUUID();
		}
		
		this.owner = owner;
		this.location = location;
		this.type = type;
		this.maxspawnersallowed = 3;
	}
	
	public List<CelestialSpawner> getSpawners() {
		return this.spawners;
	}
	public int getMaxSpawnersAllowed() {
		return this.maxspawnersallowed;
	}
	public CelestialSpawner getCurrent() {
		return this.current;
	}
	
	public void setCurrent(CelestialSpawner spawner) {
		this.current = spawner;
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
	
	public CelestialSpawner getNext(CelestialSpawner uid) {
	    int idx = this.spawners.indexOf(uid);
	    if (idx < 0 || idx+1 == this.spawners.size()) return null;
	    return this.spawners.get(idx + 1);
	}
	
	public ItemStack getItem() {
		ItemStack item = new ItemStack(Material.SPAWNER);
		ItemMeta meta = item.getItemMeta();
		List<String> lore = new ArrayList<String>();
		lore.add("§7Place this spawner do to ");
		lore.add("§7spawner stuff.");
		lore.add(" ");
		lore.add("§cOwner: §3" + Bukkit.getOfflinePlayer(getOwner().getUUID()).getName());
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
}
