package me.santipingui58.celestialmc.gui.island;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import me.santipingui58.celestialmc.Main;
import me.santipingui58.celestialmc.game.CelestialPlayer;
import me.santipingui58.celestialmc.game.skyblock.SkyblockIsland;
import me.santipingui58.celestialmc.game.skyblock.SkyblockManager;
import me.santipingui58.celestialmc.game.skyblock.WorthManager;
import me.santipingui58.celestialmc.gui.MenuBuilder;
import me.santipingui58.celestialmc.gui.island.bank.IslandBankMainMenu;
import me.santipingui58.celestialmc.utils.ItemBuilder;
import me.santipingui58.celestialmc.utils.Utils;



public class IslandMainMenu extends MenuBuilder {
		
	public IslandMainMenu(Player p) {
	super("§aSkyblock Island Menu",4);
	CelestialPlayer cplayer = SkyblockManager.getManager().getCelestialPlayer(p);
	SkyblockIsland island = cplayer.getIsland();
	
		s(0,new ItemBuilder(Material.GRASS_BLOCK).setTitle("§b§l"+p.getName() + "´s Island")
				.addLore("§7Here will show some ")
				.addLore("§7useless info of your island as")
				.addLore("§7when your island was created").build());
		s(8, new ItemBuilder(Material.EMERALD_BLOCK).setTitle("§a§lIsland Bank")
				.addLore("§7Current balance: §6$" + island.getMoney())
				.addLore(" ")
				.addLore("§7Go to Island Bank Menu.").build());
		s(11, new ItemBuilder(Material.NETHER_STAR).setTitle("§5§lUpgrades")
				.addLore("§7Go to the Island Upgrades Menu.").build());
		s(13,new ItemBuilder(Material.TOTEM_OF_UNDYING).setTitle("§c§lPermissions")
				.addLore("§7Go to the Player Permissions Menu.").build());
		s(15, new ItemBuilder(Material.EXPERIENCE_BOTTLE).setTitle("§2§lIsland Worth")
				.addLore("§7Current worth: §2" + WorthManager.getManager().getTotalWorth(island))
				.addLore(" ")
				.addLore("§7Go to the Island Worth Menu.").build());
		
		s(27,new ItemBuilder(Material.CRAFTING_TABLE).setTitle("§bHome Location")
				.addLore("§a"+ Utils.getUtils().setLoc(island.getWarp(), false)).build());
		s(28,new ItemBuilder(Material.CRAFTING_TABLE).setTitle("§bWarp Location")
				.addLore("§a"+ Utils.getUtils().setLoc(island.getWarp(), false)).build());
		if (island.isWarpEnabled()) {
			s(29,new ItemBuilder(Material.LIME_DYE).setTitle("§2Island Warp: §aENABLED")
					.addLore("§7Click to disable the warp.").build());
		} else {
			s(29,new ItemBuilder(Material.GRAY_DYE).setTitle("§2Island Warp: §cDISAABLED")
					.addLore("§7Click to enable the warp.").build());
		}
		s(35,new ItemBuilder(Material.ARROW).setTitle("§cClose").build());
	}
	

	@Override
	public void onClick(Player p, ItemStack stack, int slot,InventoryClickEvent event) {
		CelestialPlayer cplayer = SkyblockManager.getManager().getCelestialPlayer(p);
		SkyblockIsland island = cplayer.getIsland();
	if (slot==8) {
		new IslandBankMainMenu(p).o(p);
	} else if (slot==11) {
		new IslandUpgradesMenu(p).o(p);
	} else if (slot==13) {
		if (island.getMembers().size()>0) {
			new PermissionsMainMenu(p).o(p);
		} else {
			p.sendMessage(Main.skyblock_prefix + " §cYou need members on your Skyblock Island to access this menu.");
		}
	} else if (slot==15) {
		new IslandWorthMenu(p).o(p);
	} else if (slot==29) {
		if (island.isWarpEnabled()) {
			island.disableWarp();
		} else {
			island.enableWarp();
		}
		new IslandMainMenu(p).o(p);
	} else if (slot==35) {
		p.closeInventory();
	}
	}
	
	
	
	
	}



