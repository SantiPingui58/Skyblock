package me.santipingui58.celestialmc.game.chest;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.santipingui58.celestialmc.game.CelestialPlayer;

public class AutoBlockChest extends CelestialChest {

	public AutoBlockChest(UUID uuid ,CelestialPlayer owner, Location location,int used) {
		super(uuid,owner, location, used);
	}

	public ItemStack getItem() {
		ItemStack item = new ItemStack(Material.CHEST);
		ItemMeta meta = item.getItemMeta();
		List<String> lore = new ArrayList<String>();
		lore.add("§7This chest will automatically try to");
		lore.add("§7turn all items inside it into their");
		lore.add("§7crafted block version.");
		lore.add(" ");
		lore.add("§cOwner: §3" + Bukkit.getOfflinePlayer(getOwner().getUUID()).getName());
		lore.add("§aBlocks crafted: §3" + this.getTimesUsed());
		lore.add(" ");
		lore.add("§8"+getUUID().toString());
		lore.add(" ");
		meta.setLore(lore);
		meta.setDisplayName("§6§lAutoBlock Chest");
		item.setItemMeta(meta);
		return item;
	}
}
