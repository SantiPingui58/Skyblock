package me.santipingui58.celestialmc.game.chest;

import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.block.Chest;
import org.bukkit.inventory.Inventory;


import me.santipingui58.celestialmc.game.CelestialPlayer;

public class CelestialChest {

	private UUID uuid;
	private Location location;
	private CelestialPlayer owner;
	private Chest chest;
	private int used;
	public CelestialChest(UUID uuid, CelestialPlayer owner, Location location,int used) {
		if (uuid==null) {
			uuid = UUID.randomUUID();
		}
		this.uuid = uuid;
		this.owner = owner;
		this.location = location;
		this.used = used;
	}
	
	public UUID getUUID() {
		return this.uuid;
	}
	public Location getLocation() {
		return this.location;
	}
	
	public void setLocation(Location l) {
		this.location = l;
	}
	
	public CelestialPlayer getOwner() {
		return this.owner;
	}
	
	public Inventory getInventory() {
		return this.chest.getInventory();
	}
	public boolean isPlaced() {
		if (this.location==null) {
			return false;
		} else {
			return true;
		}
	}
	

	public void place(Location l) {
		setLocation(l);
		this.chest = (Chest) l.getBlock().getState();
	}
	
	public void pickUp() {
		setLocation(null);
		this.chest = null;
	}
	
	public int getTimesUsed() {
		return this.used;
	}

	public void setTimesUsed(int i) {
		this.used = i;
	}

	
	
}
