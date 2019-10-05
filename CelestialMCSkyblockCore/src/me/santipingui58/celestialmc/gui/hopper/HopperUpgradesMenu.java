package me.santipingui58.celestialmc.gui.hopper;




import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import me.santipingui58.celestialmc.game.CelestialPlayer;
import me.santipingui58.celestialmc.game.Result;
import me.santipingui58.celestialmc.game.hopper.CelestialHopper;
import me.santipingui58.celestialmc.game.skyblock.SkyblockManager;
import me.santipingui58.celestialmc.gui.MenuBuilder;
import me.santipingui58.celestialmc.gui.hopper.HopperMenu;
import me.santipingui58.celestialmc.utils.ItemBuilder;



public class HopperUpgradesMenu extends MenuBuilder {


	public HopperUpgradesMenu(Player p,CelestialHopper hopper) {
		super("§5Hopper Upgrades",3);
		
		s(26, new ItemBuilder(Material.ARROW).setTitle("§cGo back").build());
		
		s(10,new ItemBuilder(Material.HOPPER).addEnchantment(Enchantment.ARROW_DAMAGE, 1).setTitle("§aHopper Level I")
				.addLore("§7- Transfer items every 8 ticks")
				.addLore("")
				.addLore("§aThis Hopper is already Level I or greater!").build());
		
		if (hopper.getLevel()>=2) {
			s(12,new ItemBuilder(Material.HOPPER).addEnchantment(Enchantment.ARROW_DAMAGE, 1).setTitle("§aHopper Level II")
					.addLore("§7- Transfer 2 items every 8 ticks")
					.addLore("")
					.addLore("§aThis Spawner is already Level II or greater!").build());
		} else {
			s(12,new ItemBuilder(Material.HOPPER).addEnchantment(Enchantment.ARROW_DAMAGE, 1).setTitle("§cHopper Level II")
					.addLore("§7- Transfer 2 items every 8 ticks")
					.addLore("")
					.addLore("§aUpgrade price §a: §6§l$5000").build());
		}  
		
		if (hopper.getLevel()>=3) {
			s(14,new ItemBuilder(Material.HOPPER).addEnchantment(Enchantment.ARROW_DAMAGE, 1).setTitle("§aHopper Level III")
					.addLore("§7- Transfer 4 items every 8 ticks")
					.addLore("§7- Able to pick up all items in the Chunk")
					.addLore("")
					.addLore("§aThis Spawner is already Level III or greater!").build());
		} else {
			s(14,new ItemBuilder(Material.HOPPER).addEnchantment(Enchantment.ARROW_DAMAGE, 1).setTitle("§cHopper Level III")
					.addLore("§7- Transfer 4 items every 8 ticks")
					.addLore("§7- Able to pick up all items in the Chunk")
					.addLore("")
					.addLore("§aUpgrade price §a: §6§l$50000").build());
		} 
		
		if (hopper.getLevel()>=4) {
			s(16,new ItemBuilder(Material.HOPPER).addEnchantment(Enchantment.ARROW_DAMAGE, 1).setTitle("§aHopper Level IV")
					.addLore("§7- Transfer 8 items every 8 ticks")
					.addLore("§7- Able to pick up all items in the Chunk")		
					.addLore("§7- AutoSell")
					.addLore("")
					.addLore("§aThis Spawner is already Level IV!").build());
		} else {
			s(16,new ItemBuilder(Material.HOPPER).addEnchantment(Enchantment.ARROW_DAMAGE, 1).setTitle("§cHopper Level IV")
					.addLore("§7- Transfer 8 items every 8 ticks")
					.addLore("§7- Able to pick up all items in the Chunk")		
					.addLore("§7- AutoSell")
					.addLore("")
					.addLore("§aUpgrade price §a: §6§l$500000").build());
		} 
	}
	
	
	@Override
	public void onClick(Player p, ItemStack stack, int slot,InventoryClickEvent event) {
		CelestialPlayer cplayer = SkyblockManager.getManager().getCelestialPlayer(p);
		CelestialHopper hopper = cplayer.getOpenHopperGUI();
		if (slot==26) {
			new HopperMenu(p,hopper).o(p);
		} else if (slot==12) {
			if (hopper.getLevel()==1) {
				if (cplayer.hasMoney(5000)) {
					hopper.levelUp();
					cplayer.takeMoney(5000);
					cplayer.sendMessage("You have succesfully upgraded this Hopper to Level II!", Result.ALLOW);
					new HopperUpgradesMenu(p,hopper).o(p);
				} else {
					cplayer.sendMessage("You do not have enough money to do this.", Result.DENY);
				}
			}  else {
				cplayer.sendMessage("You have already have this upgrade.", Result.DENY);
			}
		} else if (slot==14) {
			if (hopper.getLevel()==2) {
				if (cplayer.hasMoney(50000)) {
					hopper.levelUp();				
					cplayer.takeMoney(50000);
					cplayer.sendMessage("You have succesfully upgraded this Hopper to Level III!", Result.ALLOW);
					new HopperUpgradesMenu(p,hopper).o(p);
				} else {
					cplayer.sendMessage("You do not have enough money to do this.", Result.DENY);
				}
			} else if (hopper.getLevel()==1) {
				cplayer.sendMessage("You need Level II upgrade before buy this!.", Result.DENY);
			} else {
				cplayer.sendMessage("You have already have this upgrade.", Result.DENY);
			}
		}  else if (slot==16) {
			if (hopper.getLevel()==3) {
				if (cplayer.hasMoney(500000)) {
					hopper.levelUp();
					cplayer.takeMoney(500000);
					cplayer.sendMessage("You have succesfully upgraded this Hopper to Level IV!", Result.ALLOW);
					new HopperUpgradesMenu(p,hopper).o(p);
				} else {
					cplayer.sendMessage("You do not have enough money to do this.", Result.DENY);
				}
			} else if (hopper.getLevel()<=2) {
				cplayer.sendMessage("You need Level III upgrade before buy this!.", Result.DENY);
			} else {
				cplayer.sendMessage("You have already have this upgrade.", Result.DENY);
			}
		}
		
	}
	
	
	
	
	}



