package me.santipingui58.celestialmc.gui.island;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import me.santipingui58.celestialmc.game.CelestialPlayer;
import me.santipingui58.celestialmc.game.skyblock.SkyblockManager;
import me.santipingui58.celestialmc.gui.MenuBuilder;
import me.santipingui58.celestialmc.utils.ItemBuilder;



public class PlayerOptionsMenu extends MenuBuilder {
		
	public PlayerOptionsMenu(Player p) {
	super("§aOptions Menu",3);
	CelestialPlayer cplayer = SkyblockManager.getManager().getCelestialPlayer(p);
	if (cplayer.getOptions().hasChatEnabled()) {
		s(11, new ItemBuilder(Material.BOOK).addEnchantment(Enchantment.ARROW_DAMAGE, 1).setTitle("Read Users Chat - §aENABLED")
				.addLore("§7Right click to disable").build());
	} else {
		s(11, new ItemBuilder(Material.BOOK).setTitle("Read Users Chat - §cDISABLED")
				.addLore("§7Right click to enable").build());
	}
	if (cplayer.getOptions().hasScoreboardEnabled()) {
		s(15, new ItemBuilder(Material.PAPER).addEnchantment(Enchantment.ARROW_DAMAGE, 1).setTitle("View Scoreboard - §aENABLED")
				.addLore("§7Right click to disable").build());
	} else {
		s(15, new ItemBuilder(Material.PAPER).setTitle("View Scoreboard - §cDISABLED")
				.addLore("§7Right click to enable").build());
	}
	
	s(26, new ItemBuilder(Material.ARROW).setTitle("§cClose").build());
	}
	

	@Override
	public void onClick(Player p, ItemStack stack, int slot,InventoryClickEvent event) {
		CelestialPlayer cplayer = SkyblockManager.getManager().getCelestialPlayer(p);
		if (slot==26) {
			p.closeInventory();
		} else if (slot==11) {
			cplayer.getOptions().chat(!cplayer.getOptions().hasChatEnabled());
		} else if (slot==15) {
			cplayer.getOptions().scoreboard(!cplayer.getOptions().hasScoreboardEnabled());
		}
	}
	
	
	
	
	}



