package me.santipingui58.celestialmc.game.stacking;

import java.util.UUID;

import org.bukkit.Location;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;

import me.santipingui58.celestialmc.Main;
import me.santipingui58.celestialmc.game.skyblock.SkyblockIsland;
import me.santipingui58.celestialmc.game.skyblock.SkyblockManager;

public class StackedBlock extends Block{

	private int amount;
	private int oldamount;
	private Hologram hologram;
	
	public StackedBlock(UUID uuid,StackeableBlockType type, Location location, int amount) {
		super(uuid,type,location);
		this.amount = amount;
		Location l = new Location(location.getWorld(),location.getBlockX(),location.getBlockY()+2,location.getBlockZ());
		l = l.add( 0.5, 0.0, 0.5);
		this.hologram = HologramsAPI.createHologram(Main.get(), l);
		SkyblockIsland island = SkyblockManager.getManager().getIslandByLocation(location);
		if (island!=null) {
			if (!island.getStackedBlocks().contains(this)) {
			island.getStackedBlocks().add(this);
			}
		}
	}
	
	public int getAmount() {
		return this.amount;
	}

	public void setAmount(int i) {
		this.amount = i;
	}
	
	public Hologram getHologram() {
		return this.hologram;
	}
	public void updateHologram() {		
		if (this.oldamount!=getAmount()) {
			this.oldamount = getAmount();
		hologram.clearLines();
		hologram.appendTextLine("§6§l"+getType().toString()+" §7("+getAmount()+")");
		}
	}
} 
