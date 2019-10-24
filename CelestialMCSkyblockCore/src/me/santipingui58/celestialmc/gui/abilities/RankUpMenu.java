package me.santipingui58.celestialmc.gui.abilities;


import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import me.santipingui58.celestialmc.game.CelestialPlayer;
import me.santipingui58.celestialmc.game.Rank;
import me.santipingui58.celestialmc.game.Result;
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
				.addLore("§aPrice to rank up: §6§l$" + Rank.SKYBORN.getValue()).build());
	} else {
		s(1,new ItemBuilder(Material.GOLD_NUGGET).setTitle("§cSkyborn Rank")
				.addLore("§7- /kit skyborn")
				.addLore("")
				.addLore("§aPrice to rank up: §6§l$" + Rank.SKYBORN.getValue()).build());
	}
	
	
	if (cplayer.getRank().toLevel()>=3) {
		s(2,new ItemBuilder(Material.GOLD_NUGGET).setTitle("§aRecruit Rank")
				.addLore("§7- Exp Boost Ability (§a/ab§7)")
				.addLore("")
				.addLore("§aPrice to rank up: §6§l$" + Rank.RECRUIT.getValue()).build());
	} else {
		s(2,new ItemBuilder(Material.GOLD_NUGGET).setTitle("§cRecruit Rank")
				.addLore("§7- Exp Boost Ability (§a/ab§7)")
				.addLore("")
				.addLore("§aPrice to rank up: §6§l$" + Rank.RECRUIT.getValue()).build());
	}
	
	if (cplayer.getRank().toLevel()>=4) {
		s(3,new ItemBuilder(Material.GOLD_NUGGET).setTitle("§aTimekeeper Rank")
				.addLore("§7- Change time (§a/ptime§7)")
				.addLore("")
				.addLore("§aPrice to rank up: §6§l$"+ Rank.TIMEKEEPER.getValue()).build());
	} else {
		s(3,new ItemBuilder(Material.GOLD_NUGGET).setTitle("§cTimekeeper Rank")
				.addLore("§7- Change time (§a/ptime§7)")
				.addLore("")
				.addLore("§aPrice to rank up: §6§l$" + Rank.TIMEKEEPER.getValue()).build());
	}
	
	if (cplayer.getRank().toLevel()>=5) {
		s(4,new ItemBuilder(Material.GOLD_NUGGET).setTitle("§aBaller Rank")
				.addLore("§7- /kit baller")
				.addLore("")
				.addLore("§aPrice to rank up: §6§l$" + Rank.BALLER.getValue()).build());
	} else {
		s(4,new ItemBuilder(Material.GOLD_NUGGET).setTitle("§cBaller Rank")
				.addLore("§7- /kit baller")
				.addLore("")
				.addLore("§aPrice to rank up: §6§l$"+ Rank.BALLER.getValue()).build());
	}
	
	if (cplayer.getRank().toLevel()>=6) {
		s(5,new ItemBuilder(Material.GOLD_NUGGET).setTitle("§aActivist Rank")
				.addLore("§7- Seeder Ability (§a/ab§7)")
				.addLore("")
				.addLore("§aPrice to rank up: §6§l$"+Rank.ACTIVIST.getValue()).build());
	} else {
		s(5,new ItemBuilder(Material.GOLD_NUGGET).setTitle("§cActivist Rank")
				.addLore("§7- Seeder Ability (§a/ab§7)")
				.addLore("")
				.addLore("§aPrice to rank up: §6§l$"+Rank.ACTIVIST.getValue()).build());
	}
	
	if (cplayer.getRank().toLevel()>=7) {
		s(6,new ItemBuilder(Material.GOLD_NUGGET).setTitle("§aCraftsman Rank")
				.addLore("§7- Virtual Bench (§a/wb§7)")
				.addLore("")
				.addLore("§aPrice to rank up: §6§l$"+Rank.CRAFTSMAN.getValue()).build());
	} else {
		s(6,new ItemBuilder(Material.GOLD_NUGGET).setTitle("§cCraftsman Rank")
				.addLore("§7- Virtual Bench (§a/wb§7)")
				.addLore("")
				.addLore("§aPrice to rank up: §6§l$" + Rank.CRAFTSMAN.getValue()).build());
	}
	
	if (cplayer.getRank().toLevel()>=8) {
		s(7,new ItemBuilder(Material.GOLD_NUGGET).setTitle("§aBlacksmith Rank")
				.addLore("§7- Virtual Anvil (§a/anvil§7)")
				.addLore("")
				.addLore("§aPrice to rank up: §6§l$" + Rank.BLACKSMITH.getValue()).build());
	} else {
		s(7,new ItemBuilder(Material.GOLD_NUGGET).setTitle("§cBlacksmith Rank")
				.addLore("§7- Virtual Anvil (§a/anvil§7)")
				.addLore("")
				.addLore("§aPrice to rank up: §6§l$" + Rank.BLACKSMITH.getValue()).build());
	}
	
	if (cplayer.getRank().toLevel()>=9) {
		s(8,new ItemBuilder(Material.GOLD_NUGGET).setTitle("§aLumberjack Rank")
				.addLore("§7- Lumberjack Ability (§a/ab§7)")
				.addLore("")
				.addLore("§aPrice to rank up: §6§l$"+Rank.LUMBERJACK.getValue()).build());
	} else {
		s(8,new ItemBuilder(Material.GOLD_NUGGET).setTitle("§cLumberjack Rank")
				.addLore("§7- Lumberjack Ability (§a/ab§7)")
				.addLore("")
				.addLore("§aPrice to rank up: §6§l$"+Rank.LUMBERJACK.getValue()).build());
	}
	
	if (cplayer.getRank().toLevel()>=10) {
		s(10,new ItemBuilder(Material.GOLD_INGOT).setTitle("§aTrooper Rank")
				.addLore("§7- Hat Command (§a/hat§7)")
				.addLore("")
				.addLore("§aPrice to rank up: §6§l$"+Rank.TROOPER.getValue()).build());
	} else {
		s(10,new ItemBuilder(Material.GOLD_INGOT).setTitle("§cTrooper Rank")
				.addLore("§7- Hat Command (§a/hat§7)")
				.addLore("")
				.addLore("§aPrice to rank up: §6§l$"+Rank.TROOPER.getValue()).build());
	}
	
	if (cplayer.getRank().toLevel()>=11) {
		s(11,new ItemBuilder(Material.GOLD_INGOT).setTitle("§aDrifter Rank")
				.addLore("§7- Haste Ability (§a/ab§7)")
				.addLore("")
				.addLore("§aPrice to rank up: §6§l$"+Rank.DRIFTER.getValue()).build());
	} else {
		s(11,new ItemBuilder(Material.GOLD_INGOT).setTitle("§cDrifter Rank")
				.addLore("§7- Haste Ability (§a/ab§7)")
				.addLore("")
				.addLore("§aPrice to rank up: §6§l$"+Rank.DRIFTER.getValue()).build());
	}
	
	if (cplayer.getRank().toLevel()>=12) {
		s(12,new ItemBuilder(Material.GOLD_INGOT).setTitle("§aSkyHero Rank")
				.addLore("§7- Virtual Enderchest (§a/enderchest§7)")
				.addLore("")
				.addLore("§aPrice to rank up: §6§l$"+Rank.SKYHERO.getValue()).build());
	} else {
		s(12,new ItemBuilder(Material.GOLD_INGOT).setTitle("§cSkyHero Rank")
				.addLore("§7- Virtual Enderchest (§a/enderchest§7)")
				.addLore("")
				.addLore("§aPrice to rank up: §6§l$"+Rank.SKYHERO.getValue()).build());
	}
	
	if (cplayer.getRank().toLevel()>=13) {
		s(13,new ItemBuilder(Material.GOLD_INGOT).setTitle("§aSkyLord Rank")
				.addLore("§7- Stop Burning (§a/ext§7)")
				.addLore("§7- /kit skylord")
				.addLore("")
				.addLore("§aPrice to rank up: §6§l$"+Rank.SKYLORD.getValue()).build());
	} else {
		s(13,new ItemBuilder(Material.GOLD_INGOT).setTitle("§cSkyLord Rank")
				.addLore("§7- Stop Burning (§a/ext§7)")
				.addLore("§7- /kit skylord")
				.addLore("")
				.addLore("§aPrice to rank up: §6§l$"+Rank.SKYLORD.getValue()).build());
	}
	
	if (cplayer.getRank().toLevel()>=14) {
		s(14,new ItemBuilder(Material.GOLD_INGOT).setTitle("§aSkyLegend Rank")
				.addLore("§7- Shards Ability (§a/ab§7)")
				.addLore("§7- /kit skylegend")
				.addLore("")
				.addLore("§aPrice to rank up: §6§l$"+Rank.SKYLEGEND.getValue()).build());
	} else {
		s(14,new ItemBuilder(Material.GOLD_INGOT).setTitle("§cSkyLegend Rank")
				.addLore("§7- Shards Ability (§a/ab§7)")
				.addLore("§7- /kit skylegend")
				.addLore("")
				.addLore("§aPrice to rank up: §6§l$"+Rank.SKYLEGEND.getValue()).build());
	}
	
	
	if (cplayer.getRank().toLevel()>=15) {
		s(15,new ItemBuilder(Material.GOLD_INGOT).setTitle("§aAirborn Rank")
				.addLore("§7- Top Command (§a/top§7)")
				.addLore("")
				.addLore("§aPrice to rank up: §6§l$"+Rank.AIRBORN.getValue()).build());
	} else {
		s(15,new ItemBuilder(Material.GOLD_INGOT).setTitle("§cAirborn Rank")
				.addLore("§7- Top Command (§a/top§7)")
				.addLore("")
				.addLore("§aPrice to rank up: §6§l$"+Rank.AIRBORN.getValue()).build());
	}
	
	if (cplayer.getRank().toLevel()>=16) {
		s(16,new ItemBuilder(Material.GOLD_INGOT).setTitle("§aFarmer Rank")
				.addLore("§7- Farmer Ability (§a/ab§7)")
				.addLore("")
				.addLore("§aPrice to rank up: §6§l$"+Rank.FARMER.getValue()).build());
	} else {
		s(16,new ItemBuilder(Material.GOLD_INGOT).setTitle("§cFarmer Rank")
				.addLore("§7- Farmer Ability (§a/ab§7)")
				.addLore("")
				.addLore("§aPrice to rank up: §6§l$"+Rank.FARMER.getValue()).build());
	}
	
	if (cplayer.getRank().toLevel()>=17) {
		s(21,new ItemBuilder(Material.GOLD_BLOCK).setTitle("§aInfinity Rank")
				.addLore("§7- Fly Command (§a/fly§7)")
				.addLore("§7- Chat Color in Chat")
				.addLore("")
				.addLore("§aPrice to rank up: §6§l$"+Rank.INFINITY.getValue()).build());
	} else {
		s(21,new ItemBuilder(Material.GOLD_BLOCK).setTitle("§cInfinity Rank")
				.addLore("§7- Fly Command (§a/fly§7)")
				.addLore("§7- Chat Color in Chat")
				.addLore("")
				.addLore("§aPrice to rank up: §6§l$"+Rank.INFINITY.getValue()).build());
	}
	
	if (cplayer.getRank().toLevel()>=18) {
		s(22,new ItemBuilder(Material.GOLD_BLOCK).setTitle("§aHustler Rank")
				.addLore("§7- 50% more money when killing mobs")
				.addLore("")
				.addLore("§aPrice to rank up: §6§l$"+Rank.HUSTLER.getValue()).build());
	} else {
		s(22,new ItemBuilder(Material.GOLD_BLOCK).setTitle("§cHustler Rank")
				.addLore("§7- 50% more money when killing mobs")
				.addLore("")
				.addLore("§aPrice to rank up: §6§l$"+Rank.HUSTLER.getValue()).build());
	}
	
	if (cplayer.getRank().toLevel()>=19) {
		s(23,new ItemBuilder(Material.GOLD_BLOCK).setTitle("§aSkyGod Rank")
				.addLore("§7- Feed Yourself (§a/feed§7)")
				.addLore("§7- Heal Yourself (§a/heal§7)")
				.addLore("§7- Fix Items (§a/fix all§7)")
				.addLore("§7- /kit skygod")
				.addLore("")
				.addLore("§aPrice to rank up: §6§l$"+Rank.SKYGOD.getValue()).build());
	} else {
		s(23,new ItemBuilder(Material.GOLD_BLOCK).setTitle("§cSkyGod Rank")
				.addLore("§7- Feed Yourself (§a/feed§7)")
				.addLore("§7- Heal Yourself (§a/heal§7)")
				.addLore("§7- Fix Items (§a/fix all§7)")
				.addLore("§7- /kit skygod")
				.addLore("")
				.addLore("§aPrice to rank up: §6§l$"+Rank.SKYGOD.getValue()).build());
	}
	
	}
	

	@Override
	public void onClick(Player p, ItemStack stack, int slot,InventoryClickEvent event) {
		CelestialPlayer cplayer = SkyblockManager.getManager().getCelestialPlayer(p);
		
		if (slot==1) {
			if (cplayer.getRank().toLevel()<2) {
				if (cplayer.hasMoney(Rank.SKYBORN.getValue())) {
					cplayer.takeMoney(Rank.SKYBORN.getValue());
					cplayer.rankUp();
					cplayer.sendMessage("You have succesfully ranked up to &bSkyBorn Rank&a!", Result.ALLOW);
				} else {
					p.closeInventory();
					cplayer.sendMessage("You don`t have enough money to do this.", Result.DENY);
				}
			}
		} else if (slot==2) {
			if (cplayer.getRank().toLevel()<3) {
				if (cplayer.hasMoney(Rank.RECRUIT.getValue())) {
					cplayer.takeMoney(Rank.RECRUIT.getValue());
					cplayer.rankUp();
					cplayer.sendMessage("You have succesfully ranked up to &bRecruit Rank&a!", Result.ALLOW);
				} else {
					p.closeInventory();
					cplayer.sendMessage("You don`t have enough money to do this.", Result.DENY);
				}
			}
		} else if (slot==3) {
			if (cplayer.getRank().toLevel()<4) {
				if (cplayer.hasMoney(Rank.TIMEKEEPER.getValue())) {
					cplayer.takeMoney(Rank.TIMEKEEPER.getValue());
					cplayer.rankUp();
					cplayer.sendMessage("You have succesfully ranked up to &bTimekeeper Rank&a!", Result.ALLOW);
				} else {
					p.closeInventory();
					cplayer.sendMessage("You don`t have enough money to do this.", Result.DENY);
				}
			}
		} else if (slot==4) {
			if (cplayer.getRank().toLevel()<4) {
				if (cplayer.hasMoney(Rank.BALLER.getValue())) {
					cplayer.takeMoney(Rank.BALLER.getValue());
					cplayer.rankUp();
					cplayer.sendMessage("You have succesfully ranked up to &bBaller Rank&a!", Result.ALLOW);
				} else {
					p.closeInventory();
					cplayer.sendMessage("You don`t have enough money to do this.", Result.DENY);
				}
			}
		}else if (slot==5) {
			if (cplayer.getRank().toLevel()<4) {
				if (cplayer.hasMoney(Rank.ACTIVIST.getValue())) {
					cplayer.takeMoney(Rank.ACTIVIST.getValue());
					cplayer.rankUp();
					cplayer.sendMessage("You have succesfully ranked up to &bActivist Rank&a!", Result.ALLOW);
				} else {
					p.closeInventory();
					cplayer.sendMessage("You don`t have enough money to do this.", Result.DENY);
				}
			}
		}else if (slot==6) {
			if (cplayer.getRank().toLevel()<4) {
				if (cplayer.hasMoney(Rank.CRAFTSMAN.getValue())) {
					cplayer.takeMoney(Rank.CRAFTSMAN.getValue());
					cplayer.rankUp();
					cplayer.sendMessage("You have succesfully ranked up to &bCraftsman Rank&a!", Result.ALLOW);
				} else {
					p.closeInventory();
					cplayer.sendMessage("You don`t have enough money to do this.", Result.DENY);
				}
			}
		}else if (slot==7) {
			if (cplayer.getRank().toLevel()<4) {
				if (cplayer.hasMoney(Rank.BLACKSMITH.getValue())) {
					cplayer.takeMoney(Rank.BLACKSMITH.getValue());
					cplayer.rankUp();
					cplayer.sendMessage("You have succesfully ranked up to &bBlacksmith Rank&a!", Result.ALLOW);
				} else {
					p.closeInventory();
					cplayer.sendMessage("You don`t have enough money to do this.", Result.DENY);
				}
			}
		}else if (slot==8) {
			if (cplayer.getRank().toLevel()<4) {
				if (cplayer.hasMoney(Rank.LUMBERJACK.getValue())) {
					cplayer.takeMoney(Rank.LUMBERJACK.getValue());
					cplayer.rankUp();
					cplayer.sendMessage("You have succesfully ranked up to &bLumberjack Rank&a!", Result.ALLOW);
				} else {
					p.closeInventory();
					cplayer.sendMessage("You don`t have enough money to do this.", Result.DENY);
				}
			}
		}else if (slot==10) {
			if (cplayer.getRank().toLevel()<4) {
				if (cplayer.hasMoney(Rank.TROOPER.getValue())) {
					cplayer.takeMoney(Rank.TROOPER.getValue());
					cplayer.rankUp();
					cplayer.sendMessage("You have succesfully ranked up to &bTrooper Rank&a!", Result.ALLOW);
				} else {
					p.closeInventory();
					cplayer.sendMessage("You don`t have enough money to do this.", Result.DENY);
				}
			}
		}else if (slot==11) {
			if (cplayer.getRank().toLevel()<4) {
				if (cplayer.hasMoney(Rank.DRIFTER.getValue())) {
					cplayer.takeMoney(Rank.DRIFTER.getValue());
					cplayer.rankUp();
					cplayer.sendMessage("You have succesfully ranked up to &bDrifter Rank&a!", Result.ALLOW);
				} else {
					p.closeInventory();
					cplayer.sendMessage("You don`t have enough money to do this.", Result.DENY);
				}
			}
		}else if (slot==12) {
			if (cplayer.getRank().toLevel()<4) {
				if (cplayer.hasMoney(Rank.SKYHERO.getValue())) {
					cplayer.takeMoney(Rank.SKYHERO.getValue());
					cplayer.rankUp();
					cplayer.sendMessage("You have succesfully ranked up to &bSkyHero Rank&a!", Result.ALLOW);
				} else {
					p.closeInventory();
					cplayer.sendMessage("You don`t have enough money to do this.", Result.DENY);
				}
			}
		}else if (slot==13) {
			if (cplayer.getRank().toLevel()<4) {
				if (cplayer.hasMoney(Rank.SKYLORD.getValue())) {
					cplayer.takeMoney(Rank.SKYLORD.getValue());
					cplayer.rankUp();
					cplayer.sendMessage("You have succesfully ranked up to &bSkyLord Rank&a!", Result.ALLOW);
				} else {
					p.closeInventory();
					cplayer.sendMessage("You don`t have enough money to do this.", Result.DENY);
				}
			}
		}else if (slot==14) {
			if (cplayer.getRank().toLevel()<4) {
				if (cplayer.hasMoney(Rank.SKYLEGEND.getValue())) {
					cplayer.takeMoney(Rank.SKYLEGEND.getValue());
					cplayer.rankUp();
					cplayer.sendMessage("You have succesfully ranked up to &bSkyLegend Rank&a!", Result.ALLOW);
				} else {
					p.closeInventory();
					cplayer.sendMessage("You don`t have enough money to do this.", Result.DENY);
				}
			}
		}else if (slot==15) {
			if (cplayer.getRank().toLevel()<4) {
				if (cplayer.hasMoney(Rank.AIRBORN.getValue())) {
					cplayer.takeMoney(Rank.AIRBORN.getValue());
					cplayer.rankUp();
					cplayer.sendMessage("You have succesfully ranked up to &bAirborn Rank&a!", Result.ALLOW);
				} else {
					p.closeInventory();
					cplayer.sendMessage("You don`t have enough money to do this.", Result.DENY);
				}
			}
		}else if (slot==16) {
			if (cplayer.getRank().toLevel()<4) {
				if (cplayer.hasMoney(Rank.FARMER.getValue())) {
					cplayer.takeMoney(Rank.FARMER.getValue());
					cplayer.rankUp();
					cplayer.sendMessage("You have succesfully ranked up to &bFarmer Rank&a!", Result.ALLOW);
				} else {
					p.closeInventory();
					cplayer.sendMessage("You don`t have enough money to do this.", Result.DENY);
				}
			}
		}else if (slot==21) {
			if (cplayer.getRank().toLevel()<4) {
				if (cplayer.hasMoney(Rank.INFINITY.getValue())) {
					cplayer.takeMoney(Rank.INFINITY.getValue());
					cplayer.rankUp();
					cplayer.sendMessage("You have succesfully ranked up to &bInfinity Rank&a!", Result.ALLOW);
				} else {
					p.closeInventory();
					cplayer.sendMessage("You don`t have enough money to do this.", Result.DENY);
				}
			}
		}else if (slot==22) {
			if (cplayer.getRank().toLevel()<4) {
				if (cplayer.hasMoney(Rank.HUSTLER.getValue())) {
					cplayer.takeMoney(Rank.HUSTLER.getValue());
					cplayer.rankUp();
					cplayer.sendMessage("You have succesfully ranked up to &bHustler Rank&a!", Result.ALLOW);
				} else {
					p.closeInventory();
					cplayer.sendMessage("You don`t have enough money to do this.", Result.DENY);
				}
			}
		}else if (slot==23) {
			if (cplayer.getRank().toLevel()<4) {
				if (cplayer.hasMoney(Rank.SKYGOD.getValue())) {
					cplayer.takeMoney(Rank.SKYGOD.getValue());
					cplayer.rankUp();
					cplayer.sendMessage("You have succesfully ranked up to &bSkyGod Rank&a!", Result.ALLOW);
				} else {
					p.closeInventory();
					cplayer.sendMessage("You don`t have enough money to do this.", Result.DENY);
				}
			}
		}
		
	}
	
	
	
	
	}



