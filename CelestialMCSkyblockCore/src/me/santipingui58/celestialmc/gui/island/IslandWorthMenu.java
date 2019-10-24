package me.santipingui58.celestialmc.gui.island;


import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import me.santipingui58.celestialmc.game.CelestialPlayer;
import me.santipingui58.celestialmc.game.skyblock.SkyblockIsland;
import me.santipingui58.celestialmc.game.skyblock.SkyblockManager;
import me.santipingui58.celestialmc.game.skyblock.WorthManager;
import me.santipingui58.celestialmc.game.stacking.StackeableBlockType;
import me.santipingui58.celestialmc.gui.MenuBuilder;
import me.santipingui58.celestialmc.utils.ItemBuilder;


public class IslandWorthMenu extends MenuBuilder {
		
	public IslandWorthMenu(Player p) {
	super("§2Island Worth Menu",5);
	ItemStack nada = new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).setTitle(" ").build();
	for (int i = 0;i<44;i++) {
		s(i, nada);
	}
	CelestialPlayer cplayer = SkyblockManager.getManager().getCelestialPlayer(p);
	SkyblockIsland island = cplayer.getIsland();
	s(4,new ItemBuilder(Material.EMERALD_BLOCK).setTitle("§2§lTotal Island Worth")
			.addLore("§3§l" + WorthManager.getManager().getTotalWorth(island)).addEnchantment(Enchantment.ARROW_DAMAGE, 1).build());
	s(44, new ItemBuilder(Material.ARROW).setTitle("§cGo back").build());
	
	int slot = 9;
	
	if (WorthManager.getManager().getTotalWorth(island)>0) {
	for (StackeableBlockType types : StackeableBlockType.values()) {	
		if (WorthManager.getManager().getBlocksAmount(island, types)>0) { 
			int amount = 1;
			if (WorthManager.getManager().getBlocksAmount(island, types)>64) {
				amount = 64;
			} else {
				amount = WorthManager.getManager().getBlocksAmount(island, types);
			}
			
			String name = "§c§l"+types.toString().replace("_", " ");
		s(slot,new ItemBuilder(types.toMaterial()).setTitle(name)
				.addLore("§7Worth: §3" + WorthManager.getManager().getWorth(island, types))
				.addLore("§7Amount: §a" + WorthManager.getManager().getBlocksAmount(island, types)).setAmount(amount).build());
		slot++;
		}		
	}
	} else {
		s(13,new ItemBuilder(Material.BARRIER).setTitle("§cIsland does not have any worth.").build());
	}
	}
	

	@Override
	public void onClick(Player p, ItemStack stack, int slot,InventoryClickEvent event) {
if (slot==44) {
	new IslandMainMenu(p).o(p);
}
	}
	
	
	
	
	}



