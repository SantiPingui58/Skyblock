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

public class AutoSellChest extends CelestialChest {

	private int money;
	public AutoSellChest(UUID uuid,CelestialPlayer owner, Location location,int used,int money) {
		super(uuid, owner, location,used);
		this.money = money;
	}
	
	public int getMoney() {
		return this.money;
	}
	
	public void setMoney(int i) {
		this.money = i;
	}
	
	public ItemStack getItem() {
		ItemStack item = new ItemStack(Material.CHEST);
		ItemMeta meta = item.getItemMeta();
		List<String> lore = new ArrayList<String>();
		lore.add("§7This chest will automatically try to");
		lore.add("§7sell all items inside it.");
		lore.add("§7(Right click and shift the chest to ");
		lore.add("§7take the generated money.)");
		lore.add(" ");
		lore.add("§cOriginal Owner: §3" + Bukkit.getOfflinePlayer(getOwner().getUUID()).getName());
		lore.add("§aItems sold: §3" + this.getTimesUsed());
		lore.add(" ");
		lore.add("§aCurrent money: §6$" + getMoney());
		lore.add(" ");
		lore.add("§8"+getUUID().toString());
		lore.add(" ");
		meta.setLore(lore);
		meta.setDisplayName("§a§lAutoSell Chest - §6§l$"+getMoney());
		item.setItemMeta(meta);
		return item;
	}
	
}
