package me.santipingui58.celestialmc.gui.spawner;




import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import me.santipingui58.celestialmc.gui.MenuBuilder;
import me.santipingui58.celestialmc.spawner.CelestialSpawner;



public class SpawnerUpgradesMenu extends MenuBuilder {


	public SpawnerUpgradesMenu(Player p,CelestialSpawner spawner) {
		super("§a" + spawner.getTitle() + " Spawner",4);
	}
	

	@Override
	public void onClick(Player p, ItemStack stack, int slot,InventoryClickEvent event) {
	}
	
	
	
	
	}



