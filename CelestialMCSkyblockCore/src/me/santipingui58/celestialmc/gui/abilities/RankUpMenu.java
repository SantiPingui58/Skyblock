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


public class RankUpMenu extends MenuBuilder {
		
	public RankUpMenu(Player p) {
	super("§c§lRank Up Menu",4);
	CelestialPlayer cplayer = SkyblockManager.getManager().getCelestialPlayer(p);
	
	
	s(0,new ItemBuilder(Material.GOLD_NUGGET).addEnchantment(Enchantment.ARROW_DAMAGE, 1).setTitle("§aDefault Rank")
			.addLore("§7- /kit default").build());
	
	if (cplayer.getRank().toLevel()>=2) {
		s(1,new ItemBuilder(Material.GOLD_NUGGET).setTitle("§aSkyborn Rank")
				.addLore("§7- /kit skyborn")
				.addLore("")
				.addLore("§aPrice to rank up: §6§l$100").build());
	} else {
		s(1,new ItemBuilder(Material.GOLD_NUGGET).setTitle("§cSkyborn Rank")
				.addLore("§7- /kit skyborn")
				.addLore("")
				.addLore("§aPrice to rank up: §6§l$100").build());
	}
	
	
	if (cplayer.getRank().toLevel()>=3) {
		s(2,new ItemBuilder(Material.GOLD_NUGGET).setTitle("§aRecruit Rank")
				.addLore("§7- Exp Boost Ability (§a/ab§7)")
				.addLore("")
				.addLore("§aPrice to rank up: §6§l$200").build());
	} else {
		s(2,new ItemBuilder(Material.GOLD_NUGGET).setTitle("§cRecruit Rank")
				.addLore("§7- Exp Boost Ability (§a/ab§7)")
				.addLore("")
				.addLore("§aPrice to rank up: §6§l$250").build());
	}
	
	if (cplayer.getRank().toLevel()>=4) {
		s(3,new ItemBuilder(Material.GOLD_NUGGET).setTitle("§aTimekeeper Rank")
				.addLore("§7- Change time (§a/ptime§7)")
				.addLore("")
				.addLore("§aPrice to rank up: §6§l$500").build());
	} else {
		s(3,new ItemBuilder(Material.GOLD_NUGGET).setTitle("§cTimekeeper Rank")
				.addLore("§7- Change time (§a/ptime§7)")
				.addLore("")
				.addLore("§aPrice to rank up: §6§l$500").build());
	}
	
	if (cplayer.getRank().toLevel()>=5) {
		s(4,new ItemBuilder(Material.GOLD_NUGGET).setTitle("§aBaller Rank")
				.addLore("§7- /kit baller")
				.addLore("")
				.addLore("§aPrice to rank up: §6§l$800").build());
	} else {
		s(4,new ItemBuilder(Material.GOLD_NUGGET).setTitle("§cBaller Rank")
				.addLore("§7- /kit baller")
				.addLore("")
				.addLore("§aPrice to rank up: §6§l$800").build());
	}
	
	if (cplayer.getRank().toLevel()>=6) {
		s(5,new ItemBuilder(Material.GOLD_NUGGET).setTitle("§aActivist Rank")
				.addLore("§7- Seeder Ability (§a/ab§7)")
				.addLore("")
				.addLore("§aPrice to rank up: §6§l$1500").build());
	} else {
		s(5,new ItemBuilder(Material.GOLD_NUGGET).setTitle("§cActivist Rank")
				.addLore("§7- Seeder Ability (§a/ab§7)")
				.addLore("")
				.addLore("§aPrice to rank up: §6§l$1500").build());
	}
	
	if (cplayer.getRank().toLevel()>=7) {
		s(6,new ItemBuilder(Material.GOLD_NUGGET).setTitle("§aCraftsman Rank")
				.addLore("§7- Virtual Bench (§a/wb§7)")
				.addLore("")
				.addLore("§aPrice to rank up: §6§l$2500").build());
	} else {
		s(6,new ItemBuilder(Material.GOLD_NUGGET).setTitle("§cCraftsman Rank")
				.addLore("§7- Virtual Bench (§a/wb§7)")
				.addLore("")
				.addLore("§aPrice to rank up: §6§l$2500").build());
	}
	
	if (cplayer.getRank().toLevel()>=8) {
		s(7,new ItemBuilder(Material.GOLD_NUGGET).setTitle("§aBlacksmith Rank")
				.addLore("§7- Virtual Anvil (§a/anvil§7)")
				.addLore("")
				.addLore("§aPrice to rank up: §6§l$3500").build());
	} else {
		s(7,new ItemBuilder(Material.GOLD_NUGGET).setTitle("§cBlacksmith Rank")
				.addLore("§7- Virtual Anvil (§a/anvil§7)")
				.addLore("")
				.addLore("§aPrice to rank up: §6§l$3500").build());
	}
	
	if (cplayer.getRank().toLevel()>=9) {
		s(8,new ItemBuilder(Material.GOLD_NUGGET).setTitle("§aLumberjack Rank")
				.addLore("§7- Lumberjack Ability (§a/ab§7)")
				.addLore("")
				.addLore("§aPrice to rank up: §6§l$6500").build());
	} else {
		s(8,new ItemBuilder(Material.GOLD_NUGGET).setTitle("§cLumberjack Rank")
				.addLore("§7- Lumberjack Ability (§a/ab§7)")
				.addLore("")
				.addLore("§aPrice to rank up: §6§l$6500").build());
	}
	
	if (cplayer.getRank().toLevel()>=10) {
		s(10,new ItemBuilder(Material.GOLD_INGOT).setTitle("§aTrooper Rank")
				.addLore("§7- Hat Command (§a/hat§7)")
				.addLore("")
				.addLore("§aPrice to rank up: §6§l$12500").build());
	} else {
		s(10,new ItemBuilder(Material.GOLD_INGOT).setTitle("§cTrooper Rank")
				.addLore("§7- Hat Command (§a/hat§7)")
				.addLore("")
				.addLore("§aPrice to rank up: §6§l$12500").build());
	}
	
	if (cplayer.getRank().toLevel()>=11) {
		s(11,new ItemBuilder(Material.GOLD_INGOT).setTitle("§aDrifter Rank")
				.addLore("§7- Haste Ability (§a/ab§7)")
				.addLore("")
				.addLore("§aPrice to rank up: §6§l$25000").build());
	} else {
		s(11,new ItemBuilder(Material.GOLD_INGOT).setTitle("§cDrifter Rank")
				.addLore("§7- Haste Ability (§a/ab§7)")
				.addLore("")
				.addLore("§aPrice to rank up: §6§l$25000").build());
	}
	
	if (cplayer.getRank().toLevel()>=12) {
		s(12,new ItemBuilder(Material.GOLD_INGOT).setTitle("§aSkyHero Rank")
				.addLore("§7- Virtual Enderchest (§a/enderchest§7)")
				.addLore("")
				.addLore("§aPrice to rank up: §6§l$50000").build());
	} else {
		s(12,new ItemBuilder(Material.GOLD_INGOT).setTitle("§cSkyHero Rank")
				.addLore("§7- Virtual Enderchest (§a/enderchest§7)")
				.addLore("")
				.addLore("§aPrice to rank up: §6§l$50000").build());
	}
	
	if (cplayer.getRank().toLevel()>=13) {
		s(13,new ItemBuilder(Material.GOLD_INGOT).setTitle("§aSkyLord Rank")
				.addLore("§7- Stop Burning (§a/ext§7)")
				.addLore("§7- /kit skylord")
				.addLore("")
				.addLore("§aPrice to rank up: §6§l$100000").build());
	} else {
		s(13,new ItemBuilder(Material.GOLD_INGOT).setTitle("§cSkyLord Rank")
				.addLore("§7- Stop Burning (§a/ext§7)")
				.addLore("§7- /kit skylord")
				.addLore("")
				.addLore("§aPrice to rank up: §6§l$100000").build());
	}
	
	if (cplayer.getRank().toLevel()>=14) {
		s(14,new ItemBuilder(Material.GOLD_INGOT).setTitle("§aSkyLegend Rank")
				.addLore("§7- Shards Ability (§a/ab§7)")
				.addLore("§7- /kit skylegend")
				.addLore("")
				.addLore("§aPrice to rank up: §6§l$200000").build());
	} else {
		s(14,new ItemBuilder(Material.GOLD_INGOT).setTitle("§cSkyLegend Rank")
				.addLore("§7- Shards Ability (§a/ab§7)")
				.addLore("§7- /kit skylegend")
				.addLore("")
				.addLore("§aPrice to rank up: §6§l$200000").build());
	}
	
	
	if (cplayer.getRank().toLevel()>=15) {
		s(15,new ItemBuilder(Material.GOLD_INGOT).setTitle("§aAirborn Rank")
				.addLore("§7- Top Command (§a/top§7)")
				.addLore("")
				.addLore("§aPrice to rank up: §6§l$500000").build());
	} else {
		s(15,new ItemBuilder(Material.GOLD_INGOT).setTitle("§cAirborn Rank")
				.addLore("§7- Top Command (§a/top§7)")
				.addLore("")
				.addLore("§aPrice to rank up: §6§l$500000").build());
	}
	
	if (cplayer.getRank().toLevel()>=16) {
		s(16,new ItemBuilder(Material.GOLD_INGOT).setTitle("§aFarmer Rank")
				.addLore("§7- Farmer Ability (§a/ab§7)")
				.addLore("")
				.addLore("§aPrice to rank up: §6§l$1000000").build());
	} else {
		s(16,new ItemBuilder(Material.GOLD_INGOT).setTitle("§cFarmer Rank")
				.addLore("§7- Farmer Ability (§a/ab§7)")
				.addLore("")
				.addLore("§aPrice to rank up: §6§l$1000000").build());
	}
	
	if (cplayer.getRank().toLevel()>=17) {
		s(21,new ItemBuilder(Material.GOLD_BLOCK).setTitle("§aInfinity Rank")
				.addLore("§7- Fly Command (§a/fly§7)")
				.addLore("§7- Chat Color in Chat")
				.addLore("")
				.addLore("§aPrice to rank up: §6§l$2500000").build());
	} else {
		s(21,new ItemBuilder(Material.GOLD_BLOCK).setTitle("§cInfinity Rank")
				.addLore("§7- Fly Command (§a/fly§7)")
				.addLore("§7- Chat Color in Chat")
				.addLore("")
				.addLore("§aPrice to rank up: §6§l$2500000").build());
	}
	
	if (cplayer.getRank().toLevel()>=18) {
		s(22,new ItemBuilder(Material.GOLD_BLOCK).setTitle("§aHustler Rank")
				.addLore("§7- 50% more money when killing mobs")
				.addLore("")
				.addLore("§aPrice to rank up: §6§l$5000000").build());
	} else {
		s(22,new ItemBuilder(Material.GOLD_BLOCK).setTitle("§cHustler Rank")
				.addLore("§7- 50% more money when killing mobs")
				.addLore("")
				.addLore("§aPrice to rank up: §6§l$5000000").build());
	}
	
	if (cplayer.getRank().toLevel()>=19) {
		s(23,new ItemBuilder(Material.GOLD_BLOCK).setTitle("§aSkyGod Rank")
				.addLore("§7- Feed Yourself (§a/feed§7)")
				.addLore("§7- Heal Yourself (§a/heal§7)")
				.addLore("§7- Fix Items (§a/fix all§7)")
				.addLore("§7- /kit skygod")
				.addLore("")
				.addLore("§aPrice to rank up: §6§l$10000000").build());
	} else {
		s(23,new ItemBuilder(Material.GOLD_BLOCK).setTitle("§cSkyGod Rank")
				.addLore("§7- Feed Yourself (§a/feed§7)")
				.addLore("§7- Heal Yourself (§a/heal§7)")
				.addLore("§7- Fix Items (§a/fix all§7)")
				.addLore("§7- /kit skygod")
				.addLore("")
				.addLore("§aPrice to rank up: §6§l$10000000").build());
	}
	
	}
	

	@Override
	public void onClick(Player p, ItemStack stack, int slot,InventoryClickEvent event) {

		
	}
	
	
	
	
	}



