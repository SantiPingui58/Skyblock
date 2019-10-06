package me.santipingui58.celestialmc.gui.hopper;


import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import me.santipingui58.celestialmc.game.CelestialPlayer;
import me.santipingui58.celestialmc.game.Result;
import me.santipingui58.celestialmc.game.hopper.CelestialHopper;
import me.santipingui58.celestialmc.game.skyblock.SkyblockManager;
import me.santipingui58.celestialmc.gui.MenuBuilder;
import me.santipingui58.celestialmc.utils.ItemBuilder;


public class HopperMenu extends MenuBuilder {
		
	public HopperMenu(Player p,CelestialHopper hopper) {
	super("§f§lHopper Menu",3);
	CelestialPlayer cplayer = SkyblockManager.getManager().getCelestialPlayer(p);
	cplayer.setOpenHopperGUI(hopper);
	s(4, new ItemBuilder(Material.HOPPER).build());
	if (hopper.getLevel()>=4) {
		s(13,new ItemBuilder(Material.EMERALD).setTitle("§e§lWithdraw Money")
				.addLore("§7Current money: §6§l$" + hopper.getMoney())
				.addLore("")
				.addLore("§7Right click to withdraw money.").build());
		
	if (hopper.isAutoSellActivated()) {
	s(14, new ItemBuilder(Material.REDSTONE_TORCH).setTitle("§eAuto Sell: §aON")
			.addLore("§7Right click to turn off.").build());
	} else {
		s(14, new ItemBuilder(Material.REDSTONE_TORCH).setTitle("§eAuto Sell: §cOFF")
				.addLore("§7Right click to turn on.").build());
	}
	} else {
		s(13,new ItemBuilder(Material.BARRIER).setTitle("§cAuto Sell Options")
				.addLore("§cUnlock this feature at level IV.").build());
		s(14,new ItemBuilder(Material.BARRIER).setTitle("§cAuto Sell Options")
				.addLore("§cUnlock this feature at level IV.").build());
	}
	
	
	s(11,new ItemBuilder(Material.NETHER_STAR).setTitle("§5§lHopper Upgrades").build());
	if (p.hasPermission("celestialmc.hopper.filter")) {
	s(12,new ItemBuilder(Material.CRAFTING_TABLE).setTitle("§e§lFilter Options").build());
	} else {
		s(12,new ItemBuilder(Material.BARRIER).setTitle("§cFilter Options")
				.addLore("§cYou dont have permission to use this.").build());
	}
	if (hopper.getLevel()>=3) {
		if (hopper.isChunkPickUpActivated()) {
		s(15,new ItemBuilder(Material.REDSTONE_TORCH).setTitle("§eChunk Pick Up: §aON")
				.addLore("§7Right click to turn off.").build());
	} else {
		s(15,new ItemBuilder(Material.REDSTONE_TORCH).setTitle("§eChunk Pick Up: §cOFF")
				.addLore("§7Right click to turn on.").build());
	}
		} else {
			s(15,new ItemBuilder(Material.BARRIER).setTitle("§cChunk Pick Up")
					.addLore("§cUnlock this feature at level III.").build());
		}
	
	s(26,new ItemBuilder(Material.ARROW).setTitle("§cClose").build());
	
	}
	

	@Override
	public void onClick(Player p, ItemStack stack, int slot,InventoryClickEvent event) {
		CelestialPlayer cplayer = SkyblockManager.getManager().getCelestialPlayer(p);
		CelestialHopper hopper = cplayer.getOpenHopperGUI();
		if (slot==26) {
			p.closeInventory();
		} else if (slot==11) {
			new HopperUpgradesMenu(p,cplayer.getOpenHopperGUI()).o(p);
		} else if (slot==14) {
			if (hopper.getLevel()>=4) {
				if (hopper.isAutoSellActivated()) {
					hopper.desactivateAutoSell();
				} else {
					hopper.activeAutoSell();
				}
				new HopperMenu(p,hopper).o(p);
			}
		} else if (slot==15) {
			if (hopper.getLevel()>=3) {
				if (hopper.isChunkPickUpActivated()) {
					hopper.desactiveChunkPickUp();
				} else {
					hopper.activeChunkPickUp();
				}
				new HopperMenu(p,hopper).o(p);
			}
		} else if (slot==13) {
			if (hopper.getLevel()>=4) {
				if (hopper.getMoney()>0) {
					cplayer.addMoney(hopper.getMoney());
					cplayer.sendMessage("You have succesfully withdrawn &6&l$"+hopper.getMoney(), Result.ALLOW);
					hopper.setMoney(0);
					new HopperMenu(p,hopper).o(p);
				} else {
					cplayer.sendMessage("This Hopper doesnt have money.", Result.DENY);
				}
			} 
		}
	}
	
	
	
	
	}



