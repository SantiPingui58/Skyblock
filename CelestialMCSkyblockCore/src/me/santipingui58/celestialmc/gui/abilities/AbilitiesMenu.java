package me.santipingui58.celestialmc.gui.abilities;


import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import me.santipingui58.celestialmc.game.CelestialPlayer;
import me.santipingui58.celestialmc.game.skyblock.SkyblockManager;
import me.santipingui58.celestialmc.gui.MenuBuilder;
import me.santipingui58.celestialmc.utils.ItemBuilder;


public class AbilitiesMenu extends MenuBuilder {
		
	public AbilitiesMenu(Player p) {
	super("§7Togle an ability",3);
	CelestialPlayer cplayer = SkyblockManager.getManager().getCelestialPlayer(p);
	for (int i = 0; i<=26;i++) {
		s(i,new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setTitle(" ").build());
	}
	if (cplayer.getRank().toLevel()>=3) {
		if (cplayer.isExpBoosted()) {
			s(10,new ItemBuilder(Material.EXPERIENCE_BOTTLE).addEnchantment(Enchantment.ARROW_DAMAGE, 1).setTitle("§aExp Boost Ability")
					.addLore("§7Applies a 25% boost when you ")
					.addLore("§7gain Exp.")
					.addLore("")
					.addLore("§eStatus: §aON").build());
		} else {
			s(10,new ItemBuilder(Material.EXPERIENCE_BOTTLE).setTitle("§aExp Boost Ability")
					.addLore("§7Applies a 25% boost when you ")
					.addLore("§7gain Exp.")
					.addLore("")
					.addLore("§eStatus: §cOFF").build());
		}
	} else {
		s(10,new ItemBuilder(Material.BARRIER).setTitle("§cBlocked")
				.addLore("")
				.addLore("§cYou have not unlocked this ability yet.").build());
	}
	
	if (cplayer.getRank().toLevel()>=6) {
		if (cplayer.isAutoSeedsEnabled()) {
			s(11,new ItemBuilder(Material.WHEAT_SEEDS).addEnchantment(Enchantment.ARROW_DAMAGE, 1).setTitle("§aSeeder Ability")
					.addLore("§7Automatically replant seeds")
					.addLore("§7after collecting a crop.")
					.addLore("")
					.addLore("§eStatus: §aON").build());
		} else {
			s(11,new ItemBuilder(Material.WHEAT_SEEDS).setTitle("§aSeeder Ability")
					.addLore("§7Automatically replant seeds")
					.addLore("§7after collecting a crop.")
					.addLore("")
					.addLore("§eStatus: §cOFF").build());
		}
	} else {
		s(11,new ItemBuilder(Material.BARRIER).setTitle("§cBlocked")
				.addLore("")
				.addLore("§cYou have not unlocked this ability yet.").build());
	}
	
	if (cplayer.getRank().toLevel()>=9) {
		if (cplayer.isAutoChop()) {
			s(12,new ItemBuilder(Material.DIAMOND_AXE).addEnchantment(Enchantment.ARROW_DAMAGE, 1).setTitle("§aLumberjack Ability")
					.addLore("§7Chop trees down in")
					.addLore("§7only one hit.")
					.addLore("")
					.addLore("§eStatus: §aON").build());
		} else {
			s(12,new ItemBuilder(Material.DIAMOND_AXE).setTitle("§aLumberjack Ability")
					.addLore("§7Chop trees down in")
					.addLore("§7only one hit.")
					.addLore("")
					.addLore("§eStatus: §cOFF").build());
		}
	} else {
		s(12,new ItemBuilder(Material.BARRIER).setTitle("§cBlocked")
				.addLore("")
				.addLore("§cYou have not unlocked this ability yet.").build());
	}
	
	if (cplayer.getRank().toLevel()>=11) {
		if (cplayer.isHasteBoost()) {
			s(14,new ItemBuilder(Material.GOLDEN_PICKAXE).addEnchantment(Enchantment.ARROW_DAMAGE, 1).setTitle("§aHaste Ability")
					.addLore("§7Permanent Haste in your")
					.addLore("§7own Skyblock Island.")
					.addLore("")
					.addLore("§eStatus: §aON").build());
		} else {
			s(14,new ItemBuilder(Material.GOLDEN_PICKAXE).setTitle("§aHaste Ability")
					.addLore("§7Permanent Haste in your")
					.addLore("§7own Skyblock Island.")
					.addLore("")
					.addLore("§eStatus: §cOFF").build());
		}
	} else {
		s(14,new ItemBuilder(Material.BARRIER).setTitle("§cBlocked")
				.addLore("")
				.addLore("§cYou have not unlocked this ability yet.").build());
	}
	
	if (cplayer.getRank().toLevel()>=16) {
		if (cplayer.isMiningKey()) {
			s(15,new ItemBuilder(Material.TRIPWIRE_HOOK).addEnchantment(Enchantment.ARROW_DAMAGE, 1).setTitle("§aShards Ability")
					.addLore("§7Chance to get a Crate Key")
					.addLore("§7while mining.")
					.addLore("")
					.addLore("§eStatus: §aON").build());
		} else {
			s(15,new ItemBuilder(Material.TRIPWIRE_HOOK).setTitle("§aShards Ability")
					.addLore("§7Chance to get a Crate Key")
					.addLore("§7while mining.")
					.addLore("")
					.addLore("§eStatus: §cOFF").build());
		}
	} else {
		s(15,new ItemBuilder(Material.BARRIER).setTitle("§cBlocked")
				.addLore("")
				.addLore("§cYou have not unlocked this ability yet.").build());
	}
	
	
	if (cplayer.getRank().toLevel()>=18) {
		if (cplayer.isFarmerKey()) {
			s(16,new ItemBuilder(Material.IRON_HOE).addEnchantment(Enchantment.ARROW_DAMAGE, 1).setTitle("§aFarmer Ability")
					.addLore("§7Chance to get a Crate Key")
					.addLore("§7while collecting crops.")
					.addLore("")
					.addLore("§eStatus: §aON").build());
		} else {
			s(16,new ItemBuilder(Material.IRON_HOE).setTitle("§aFarmer Ability")
					.addLore("§7Chance to get a Crate Key")
					.addLore("§7while collecting crops.")
					.addLore("")
					.addLore("§eStatus: §cOFF").build());
		}
	} else {
		s(16,new ItemBuilder(Material.BARRIER).setTitle("§cBlocked")
				.addLore("")
				.addLore("§cYou have not unlocked this ability yet.").build());
	}
	
	}
	

	@Override
	public void onClick(Player p, ItemStack stack, int slot,InventoryClickEvent event) {
		CelestialPlayer cplayer = SkyblockManager.getManager().getCelestialPlayer(p);
		if (stack.getType().equals(Material.BARRIER)) {
			return;
		}
		
		if (slot==10) {
			if (cplayer.isExpBoosted()) {
				cplayer.expBoost(false);
			} else {
				cplayer.expBoost(true);
			}
			new AbilitiesMenu(p).o(p);
		} else if (slot==11) {
			if (cplayer.isAutoSeedsEnabled()) {
				cplayer.autoSeed(false);
			} else {
				cplayer.autoSeed(true);
			}
			new AbilitiesMenu(p).o(p);
		} else if (slot==12) {
			if (cplayer.isAutoChop()) {
				cplayer.autoChop(false);
			} else {
				cplayer.autoChop(true);
			}
			new AbilitiesMenu(p).o(p);
		}   else if (slot==14) {
			if (cplayer.isHasteBoost()) {
				cplayer.hasteBoost(false);
			} else {
				cplayer.hasteBoost(true);
			}
			new AbilitiesMenu(p).o(p);
		}  else if (slot==15) {
			if (cplayer.isMiningKey()) {
				cplayer.miningKey(false);
			} else {
				cplayer.miningKey(true);
			}
			new AbilitiesMenu(p).o(p);
		}  else if (slot==16) {
			if (cplayer.isFarmerKey()) {
				cplayer.farmerKey(false);
			} else {
				cplayer.farmerKey(true);
			}
			new AbilitiesMenu(p).o(p);
		} 
		
		
	}
	
	
	
	
	}



