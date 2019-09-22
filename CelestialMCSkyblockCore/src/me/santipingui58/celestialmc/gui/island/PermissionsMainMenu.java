package me.santipingui58.celestialmc.gui.island;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import me.santipingui58.celestialmc.game.CelestialPlayer;
import me.santipingui58.celestialmc.game.skyblock.PlayerPermissions;
import me.santipingui58.celestialmc.game.skyblock.SkyblockIsland;
import me.santipingui58.celestialmc.game.skyblock.SkyblockManager;
import me.santipingui58.celestialmc.gui.MenuBuilder;
import me.santipingui58.celestialmc.utils.ItemBuilder;



public class PermissionsMainMenu extends MenuBuilder {
		
	public PermissionsMainMenu(Player p) {
	super("§cPermisisons Main Menu",3);
	CelestialPlayer cplayer = SkyblockManager.getManager().getCelestialPlayer(p);
		SkyblockIsland island = cplayer.getIsland();
	
		int i = 0;
		for (CelestialPlayer member : island.getMembers()) {
			ItemStack player = new ItemStack(Material.PLAYER_HEAD);
			
			 SkullMeta playerMeta = (SkullMeta) player.getItemMeta();
			 OfflinePlayer off = member.getOfflinePlayer();
			 playerMeta.setOwningPlayer(off);
			playerMeta.setDisplayName("§a"+off.getName());
			List<String> lore = new ArrayList<String>();
			if (member.isOnline()) {
				lore.add("§aOnline");
			} else {
				lore.add("§cOffline");
			}
			lore.add("");			
			if (island.getPermissionsHashMap()!=null) {
			if (island.getAllPermissions(member).size()==0) {
				lore.add("§cThis player does not have any permission.");
			}
			}
			for (PlayerPermissions perm : island.getAllPermissions(member)) {
				String s = perm.toString().replace("_", " ");
				lore.add("§7- §b"+s);
			}
			
			playerMeta.setLore(lore);
			player.setItemMeta(playerMeta);
			s(i,player);
			i++;
		}
		
		
		s(26,new ItemBuilder(Material.ARROW).setTitle("§cGo back").build());
	}
	

	@Override
	public void onClick(Player p, ItemStack stack, int slot,InventoryClickEvent event) {
		CelestialPlayer cplayer = SkyblockManager.getManager().getCelestialPlayer(p);
		if (slot==26) {
			new IslandMainMenu(p).o(p);
		} else {
			if (stack!=null) {
				@SuppressWarnings("deprecation")
				OfflinePlayer off = Bukkit.getOfflinePlayer(ChatColor.stripColor(stack.getItemMeta().getDisplayName()));
				CelestialPlayer target = SkyblockManager.getManager().getCelestialPlayer(off.getUniqueId());
				if (cplayer.getIsland().getMembers().contains(target)) {
					new PlayerPermissionsMenu(p,target).o(p);
				}
			}
		}
		
	}
	
	
	
	
	}



