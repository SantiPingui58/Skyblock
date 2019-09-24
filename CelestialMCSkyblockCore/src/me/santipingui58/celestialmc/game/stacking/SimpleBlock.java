package me.santipingui58.celestialmc.game.stacking;

import java.util.UUID;

import org.bukkit.Location;

import me.santipingui58.celestialmc.game.skyblock.SkyblockIsland;
import me.santipingui58.celestialmc.game.skyblock.SkyblockManager;

public class SimpleBlock extends Block{

	
	public SimpleBlock(UUID uuid,StackeableBlockType type, Location location) {
		super(uuid,type,location);
		Location l = new Location(location.getWorld(),location.getBlockX(),location.getBlockY()+2,location.getBlockZ());
		l = l.add( 0.5, 0.0, 0.5);
		SkyblockIsland island = SkyblockManager.getManager().getIslandByLocation(location);
		if (island!=null) {
			if (!island.getBlocks().contains(this)) {
			island.getBlocks().add(this);
			}
		}
	}
	

} 
