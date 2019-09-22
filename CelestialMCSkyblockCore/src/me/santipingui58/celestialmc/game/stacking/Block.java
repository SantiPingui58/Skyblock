package me.santipingui58.celestialmc.game.stacking;

import java.util.UUID;

import org.bukkit.Location;

import me.santipingui58.celestialmc.game.skyblock.SkyblockIsland;
import me.santipingui58.celestialmc.game.skyblock.SkyblockManager;

public class Block {

	private UUID uuid;
	private StackeableBlockType type;
	private Location location;
	private int amount;
	SkyblockIsland island;
	public Block(UUID uuid,StackeableBlockType type, Location location, int amount) {
		this.uuid = uuid;
		this.type = type;
		this.location = location;
		this.amount = amount;	
		SkyblockIsland island = SkyblockManager.getManager().getIslandByLocation(location);
		if (island!=null) {
			this.island = island;
			}		
	}
	
	public SkyblockIsland getIsland() {
		return island;
	}
	public UUID getUUID() {
		return this.uuid;
	}

	public StackeableBlockType getType() {
		return this.type;
	}
	
	public Location getLocation() {
		return this.location;
	}
	
	public int getAmount() {
		return this.amount;
	}

	public void setAmount(int i) {
		this.amount = i;
	}
	
} 
