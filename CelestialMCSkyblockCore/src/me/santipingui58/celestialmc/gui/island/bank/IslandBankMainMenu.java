package me.santipingui58.celestialmc.gui.island.bank;


import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import me.santipingui58.celestialmc.Main;
import me.santipingui58.celestialmc.game.CelestialPlayer;
import me.santipingui58.celestialmc.game.skyblock.SkyblockIsland;
import me.santipingui58.celestialmc.game.skyblock.SkyblockManager;
import me.santipingui58.celestialmc.gui.MenuBuilder;
import me.santipingui58.celestialmc.gui.island.IslandMainMenu;
import me.santipingui58.celestialmc.utils.ItemBuilder;


public class IslandBankMainMenu extends MenuBuilder {
		
	public IslandBankMainMenu(Player p) {
	super("§2Island Bank Menu",3);
	CelestialPlayer cplayer = SkyblockManager.getManager().getCelestialPlayer(p);
	SkyblockIsland island = cplayer.getIsland();
	s(11, new ItemBuilder(Material.DROPPER).setTitle("§aWithdraw Coins").build());
	s(13, new ItemBuilder(Material.EMERALD_BLOCK).setTitle("§a§lIsland Bank")
			.addLore("§7Current balance: §6$" + island.getMoney())
			.addLore("")
			.addLore("§7Members of your Skyblock Island can deposit")
			.addLore("§7with §b/is deposit "+p.getName() + " <Amount>")
			.addLore("§7You will earn an 0.5% interest every 24 hours.").build());
	
	s(15, new ItemBuilder(Material.PAPER).setTitle("§2Transactions").addLores(island.getTransactions()).build());
	s(26, new ItemBuilder(Material.ARROW).setTitle("§cGo back").build());
	}
	

	@Override
	public void onClick(Player p, ItemStack stack, int slot,InventoryClickEvent event) {
		CelestialPlayer cplayer = SkyblockManager.getManager().getCelestialPlayer(p);
		SkyblockIsland island = cplayer.getIsland();
		if (slot==11) {
			if (island.getMoney()>0) {
			new IslandBankWithdrawMenu(p).o(p);
			} else {
				p.sendMessage(Main.skyblock_prefix + " §cYou do not have enough money on the bank to do this.");
			}
		} else if (slot==26) {
			new IslandMainMenu(p).o(p);
		}
	}
	
	
	
	
	}



