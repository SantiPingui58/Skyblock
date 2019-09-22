package me.santipingui58.celestialmc.gui.island.bank;


import java.text.SimpleDateFormat;
import java.util.Date;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import me.santipingui58.celestialmc.Main;
import me.santipingui58.celestialmc.game.CelestialPlayer;
import me.santipingui58.celestialmc.game.skyblock.SkyblockIsland;
import me.santipingui58.celestialmc.game.skyblock.SkyblockManager;
import me.santipingui58.celestialmc.gui.MenuBuilder;
import me.santipingui58.celestialmc.utils.ItemBuilder;


public class IslandBankWithdrawMenu extends MenuBuilder {
		
	public IslandBankWithdrawMenu(Player p) {
	super("§aSelect amount to withdraw",3);
	CelestialPlayer cplayer = SkyblockManager.getManager().getCelestialPlayer(p);
	SkyblockIsland island = cplayer.getIsland();
	s(10,new ItemBuilder(Material.DROPPER).setAmount(64).setTitle("§aWithdraw all the money")
			.addLore("§7Current balance: §6$" + island.getMoney())
			.addLore("§7Amount to withdraw: §6$" + island.getMoney()).build());
	s(12,new ItemBuilder(Material.DROPPER).setAmount(32).setTitle("§aWithdraw 50% of the money")
			.addLore("§7Current balance: §6$" + island.getMoney())
			.addLore("§7Amount to withdraw: §6$" + island.getMoney()/2).build());
	s(14,new ItemBuilder(Material.DROPPER).setAmount(16).setTitle("§aWithdraw 25% of the money")
			.addLore("§7Current balance: §6$" + island.getMoney())
			.addLore("§7Amount to withdraw: §6$" + island.getMoney()/4).build());
	s(16,new ItemBuilder(Material.DROPPER).setTitle("§aWithdraw specific amount")
			.addLore("§7Current balance: §6$" + island.getMoney())
			.addLore("§7You can withdraw an specific amount")
			.addLore("§7with the §b/is withdraw <Amount> command.").build());
	s(26, new ItemBuilder(Material.ARROW).setTitle("§cGo back").build());
	}
	

	@Override
	public void onClick(Player p, ItemStack stack, int slot,InventoryClickEvent event) {
		CelestialPlayer cplayer = SkyblockManager.getManager().getCelestialPlayer(p);
		SkyblockIsland island = cplayer.getIsland();
		if (slot==10) {
			int money = island.getMoney();
			Date now = new Date();
			SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy");	
			island.withdrawMoney(money, p.getName(), format.format(now));
			cplayer.addMoney(money);
			new IslandBankMainMenu(p).o(p);
			p.sendMessage(Main.skyblock_prefix +"§aYou have succesfully withdrawn §6$"+ money + "§a from your Skyblock Island Bank!");
			p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
		} else if (slot==12) {
			int money = island.getMoney()/2;
			Date now = new Date();
			SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy");	
			island.withdrawMoney(money, p.getName(), format.format(now));
			cplayer.addMoney(money);
			new IslandBankMainMenu(p).o(p);
			p.sendMessage(Main.skyblock_prefix +"§aYou have succesfully withdrawn §6$"+ money + "§a from your Skyblock Island Bank!");
			p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
		} else if (slot==14) {
			int money = island.getMoney()/4;
			Date now = new Date();
			SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy");	
			island.withdrawMoney(money, p.getName(), format.format(now));
			cplayer.addMoney(money);
			new IslandBankMainMenu(p).o(p);
			p.sendMessage(Main.skyblock_prefix +"§aYou have succesfully withdrawn §6$"+ money + "§a from your Skyblock Island Bank!");
			p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
		} else if (slot==26) {
			new IslandBankMainMenu(p).o(p);
		}
	}
	
	
	
	
	}



