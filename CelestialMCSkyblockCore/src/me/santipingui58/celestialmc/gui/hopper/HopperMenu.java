package me.santipingui58.celestialmc.gui.hopper;


import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import me.santipingui58.celestialmc.game.hopper.CelestialHopper;
import me.santipingui58.celestialmc.gui.MenuBuilder;
import me.santipingui58.celestialmc.utils.ItemBuilder;


public class HopperMenu extends MenuBuilder {
		
	public HopperMenu(Player p,CelestialHopper hopper) {
	super("§f§lHopper Menu",3);
	s(4, new ItemBuilder(Material.HOPPER).build());
	if (hopper.getLevel()>=4) {
		s(13,new ItemBuilder(Material.EMERALD).setTitle("§e§lWithdraw Money")
				.addLore("§7Current money: §6§l$" + hopper.getMoney())
				.addLore("")
				.addLore("§7Right click to withdraw money.").build());
		
	if (hopper.isAutoSellActivated()) {
	s(15, new ItemBuilder(Material.REDSTONE_TORCH).setTitle("§eAuto Sell: §aON")
			.addLore("§7Right click to turn off.").build());
	} else {
		s(15, new ItemBuilder(Material.REDSTONE_TORCH).setTitle("§eAuto Sell: §cOFF")
				.addLore("§7Right click to turn on.").build());
	}
	} else {
		s(13,new ItemBuilder(Material.BARRIER).setTitle("§cAuto Sell Options")
				.addLore("§cUnlock this feature at level IV.").build());
		s(15,new ItemBuilder(Material.BARRIER).setTitle("§cAuto Sell Options")
				.addLore("§cUnlock this feature at level IV.").build());
	}
	
	
	s(11,new ItemBuilder(Material.NETHER_STAR).setTitle("§5§lHopper Upgrades").build());
	if (p.hasPermission("celestialmc.hopper.filter")) {
	s(18,new ItemBuilder(Material.CRAFTING_TABLE).setTitle("§e§lFilter Options").build());
	} else {
		s(18,new ItemBuilder(Material.BARRIER).setTitle("§cFilter Options")
				.addLore("§cYou dont have permission to use this.").build());
	}
	
	s(26,new ItemBuilder(Material.ARROW).setTitle("§cClose").build());
	
	}
	

	@Override
	public void onClick(Player p, ItemStack stack, int slot,InventoryClickEvent event) {
		if (slot==26) {
			p.closeInventory();
		}
	}
	
	
	
	
	}



