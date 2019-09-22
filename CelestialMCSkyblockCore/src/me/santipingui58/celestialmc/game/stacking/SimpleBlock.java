package me.santipingui58.celestialmc.game.stacking;

import java.util.UUID;

import org.bukkit.Location;

import me.santipingui58.celestialmc.game.skyblock.SkyblockIsland;
import me.santipingui58.celestialmc.game.skyblock.SkyblockManager;

public class SimpleBlock extends Block{
	
	public SimpleBlock(UUID uuid,StackeableBlockType type, Location location, int amount) {
		super(uuid,type,location,amount);
		SkyblockIsland island = SkyblockManager.getManager().getIslandByLocation(location);
		if (island!=null) {
			if (!island.getBlocks().contains(this)) {
			island.getBlocks().add(this);
			}
		}
	}	
} 
