package me.santipingui58.celestialmc.game.chest;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.earth2me.essentials.Essentials;
import com.earth2me.essentials.Worth;

import me.santipingui58.celestialmc.Main;

public class MaterialSellValue {
	
	public static int getValue(Material m) {
		Worth worth = new Worth(Main.get().getDataFolder());
		ItemStack item = new ItemStack(m);
		return worth.getPrice((Essentials) Main.get().getServer().getPluginManager().getPlugin("Essentials"), item).intValue();
		
	}
	
	
	
}
