package me.santipingui58.celestialmc.gui.island;


import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.santipingui58.celestialmc.game.CelestialPlayer;
import me.santipingui58.celestialmc.game.skyblock.PlayerPermissions;
import me.santipingui58.celestialmc.game.skyblock.SkyblockIsland;
import me.santipingui58.celestialmc.game.skyblock.SkyblockManager;
import me.santipingui58.celestialmc.gui.MenuBuilder;
import me.santipingui58.celestialmc.utils.ItemBuilder;


public class PlayerPermissionsMenu extends MenuBuilder {

		private HashMap<CelestialPlayer,CelestialPlayer> target_player = new HashMap<CelestialPlayer,CelestialPlayer>();
		
	public PlayerPermissionsMenu(Player p,CelestialPlayer otherplayer) {
		super("§a" + otherplayer.getOfflinePlayer().getName() + " Permissions",4);
		CelestialPlayer cplayer = SkyblockManager.getManager().getCelestialPlayer(p);
		target_player.put(cplayer, otherplayer);
		SkyblockIsland island = cplayer.getIsland();	
		s(2, new ItemBuilder(Material.GRASS_BLOCK).setTitle("§aADMIN Permissions").addLore("§7Give ADMIN permissions to the selected player.").build());
		s(4, new ItemBuilder(Material.GRASS_BLOCK).setTitle("§aMEMBER Permissions").addLore("§7Give MEMBER permissions to the selected player.").build());
		s(6, new ItemBuilder(Material.GRASS_BLOCK).setTitle("§aGUEST Permissions").addLore("§7Give GUEST permissions to the selected player.").build());
		s(31, new ItemBuilder(Material.RED_DYE).setTitle("§cRemove all Permissions").build());
		s(27,new ItemBuilder(Material.BARRIER).setTitle("§cKick player from your Island.").build());
		s(35, new ItemBuilder(Material.ARROW).setTitle("§cGo back").build());
		
		
		if (island.hasPermission(otherplayer, PlayerPermissions.CAN_BREAK)) {
			ItemStack item = new ItemBuilder(Material.LIME_TERRACOTTA).addEnchantment(Enchantment.DURABILITY, 1).setTitle("§eCan break blocks - §aENABLED")
					.addLore("§7Players with this permission")
					.addLore("§7will be able to break blocks on your")
					.addLore("§7Skyblock Island.").build();
			ItemMeta meta = item.getItemMeta();
			meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
			item.setItemMeta(meta);
			s(19, item);
		} else {
			s(19, new ItemBuilder(Material.RED_TERRACOTTA).setTitle("§eCan break blocks - §cDISABLED")
					.addLore("§7Players with this permission")
					.addLore("§7will be able to break blocks on your")
					.addLore("§7Skyblock Island.").build());
		}
		
		if (island.hasPermission(otherplayer, PlayerPermissions.CAN_KILL_ENTITIES)) {
			ItemStack item = new ItemBuilder(Material.LIME_TERRACOTTA).addEnchantment(Enchantment.DURABILITY, 1).setTitle("§eCan kill mobs - §aENABLED")
					.addLore("§7Players with this permission")
					.addLore("§7will be able to damage and/or kill")
					.addLore("§7mobs on your Skyblock Island.").build();
			ItemMeta meta = item.getItemMeta();
			meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
			item.setItemMeta(meta);
			s(20, item);
		} else {
			s(20, new ItemBuilder(Material.RED_TERRACOTTA).setTitle("§eCan kill mobs - §cDISABLED")
					.addLore("§7Players with this permission")
					.addLore("§7will be able to damage and/or kill")
					.addLore("§7mobs on your Skyblock Island.").build());
		}
		
		
		if (island.hasPermission(otherplayer, PlayerPermissions.CAN_OPEN_INVENTORIES)) {
			ItemStack item = new ItemBuilder(Material.LIME_TERRACOTTA).addEnchantment(Enchantment.DURABILITY, 1).setTitle("§eCan open inventories - §aENABLED")
					.addLore("§7Players with this permission")
					.addLore("§7will be able to open inventories like")
					.addLore("§7chests, furnaces, dispensers, etc.").build();
			ItemMeta meta = item.getItemMeta();
			meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
			item.setItemMeta(meta);
			s(21, item);
		} else {
			s(21, new ItemBuilder(Material.RED_TERRACOTTA).setTitle("§eCan open inventories - §cDISABLED")
					.addLore("§7Players with this permission")
					.addLore("§7will be able to open inventories like")
					.addLore("§7chests, furnaces, dispensers, etc.").build());
		}
		
		if (island.hasPermission(otherplayer, PlayerPermissions.CAN_PLACE)) {
			ItemStack item = new ItemBuilder(Material.LIME_TERRACOTTA).addEnchantment(Enchantment.DURABILITY, 1).setTitle("§eCan place blocks - §aENABLED")
					.addLore("§7Players with this permission")
					.addLore("§7will be able to place blocks")
					.addLore("§7in your Skyblock Island").build();
			ItemMeta meta = item.getItemMeta();
			meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
			item.setItemMeta(meta);
			s(22, item);
		} else {
			s(22, new ItemBuilder(Material.RED_TERRACOTTA).setTitle("§eCan place blocks - §cDISABLED")
					.addLore("§7Players with this permission")
					.addLore("§7will be able to place blocks")
					.addLore("§7in your Skyblock Island").build());
		}
		
		if (island.hasPermission(otherplayer, PlayerPermissions.CAN_SET_HOME)) {
			ItemStack item = new ItemBuilder(Material.LIME_TERRACOTTA).addEnchantment(Enchantment.DURABILITY, 1).setTitle("§eCan change home - §aENABLED")
					.addLore("§7Players with this permission")
					.addLore("§7will be able to do /is sethome")
					.addLore("§7to change the home of your")
					.addLore("§7Skyblock Island.").build();
			ItemMeta meta = item.getItemMeta();
			meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
			item.setItemMeta(meta);
			s(23, item);
		} else {
			s(23, new ItemBuilder(Material.RED_TERRACOTTA).setTitle("§eCan change home - §cDISABLED")
					.addLore("§7Players with this permission")
					.addLore("§7will be able to do /is sethome")
					.addLore("§7to change the home of your")
					.addLore("§7Skyblock Island.").build());
		}
		
		
		if (island.hasPermission(otherplayer, PlayerPermissions.CAN_SET_WARP)) {
			ItemStack item = new ItemBuilder(Material.LIME_TERRACOTTA).addEnchantment(Enchantment.DURABILITY, 1).setTitle("§eCan change warp - §aENABLED")
					.addLore("§7Players with this permission")
					.addLore("§7will be able to do /is setwarp")
					.addLore("§7to change the spawn of your")
					.addLore("§7Skyblock Island.").build();
			ItemMeta meta = item.getItemMeta();
			meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
			item.setItemMeta(meta);
			s(24, item);
		} else {
			s(24, new ItemBuilder(Material.RED_TERRACOTTA).setTitle("§eCan change warp - §cDISABLED")
					.addLore("§7Players with this permission")
					.addLore("§7will be able to do /is setwarp")
					.addLore("§7to change the spawn of your")
					.addLore("§7Skyblock Island.").build());
		}
		
		if (island.hasPermission(otherplayer, PlayerPermissions.CAN_DO_HOME)) {
			ItemStack item = new ItemBuilder(Material.LIME_TERRACOTTA).addEnchantment(Enchantment.DURABILITY, 1).setTitle("§eCan do home - §aENABLED")
					.addLore("§7Players with this permission")
					.addLore("§7will be able to do /is home " + p.getName())
					.addLore("§7to teleport to your")
					.addLore("§7Skyblock Island.").build();
			ItemMeta meta = item.getItemMeta();
			meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
			item.setItemMeta(meta);
			s(25, item);
		} else {
			s(25, new ItemBuilder(Material.RED_TERRACOTTA).setTitle("§eCan do home - §cDISABLED")
					.addLore("§7Players with this permission")
					.addLore("§7will be able to do /is home " + p.getName())
					.addLore("§7to teleport to your")
					.addLore("§7Skyblock Island.").build());
		}
		
	}
	

	@Override
	public void onClick(Player p, ItemStack stack, int slot,InventoryClickEvent event) {
		CelestialPlayer cplayer = SkyblockManager.getManager().getCelestialPlayer(p);
		SkyblockIsland island = cplayer.getIsland();
		CelestialPlayer otherPlayer = target_player.get(cplayer);
		if (slot==2) {			
			island.addPermission(otherPlayer, PlayerPermissions.CAN_BREAK);
			island.addPermission(otherPlayer, PlayerPermissions.CAN_KILL_ENTITIES);
			island.addPermission(otherPlayer, PlayerPermissions.CAN_OPEN_INVENTORIES);
			island.addPermission(otherPlayer, PlayerPermissions.CAN_PLACE);
			island.addPermission(otherPlayer, PlayerPermissions.CAN_SET_HOME);
			island.addPermission(otherPlayer, PlayerPermissions.CAN_SET_WARP);
			island.addPermission(otherPlayer, PlayerPermissions.CAN_DO_HOME);
			new PlayerPermissionsMenu(p,otherPlayer).o(p);
		} else if (slot==4) {
			island.addPermission(otherPlayer, PlayerPermissions.CAN_BREAK);
			island.addPermission(otherPlayer, PlayerPermissions.CAN_KILL_ENTITIES);
			island.addPermission(otherPlayer, PlayerPermissions.CAN_OPEN_INVENTORIES);
			island.addPermission(otherPlayer, PlayerPermissions.CAN_PLACE);
			island.addPermission(otherPlayer, PlayerPermissions.CAN_DO_HOME);
			
			island.removePermission(otherPlayer, PlayerPermissions.CAN_SET_HOME);
			island.removePermission(otherPlayer, PlayerPermissions.CAN_SET_WARP);
			new PlayerPermissionsMenu(p,otherPlayer).o(p);
		} else if (slot==6) {
			island.addPermission(otherPlayer, PlayerPermissions.CAN_BREAK);
			island.addPermission(otherPlayer, PlayerPermissions.CAN_PLACE);
			island.addPermission(otherPlayer, PlayerPermissions.CAN_DO_HOME);
			
			island.removePermission(otherPlayer, PlayerPermissions.CAN_SET_HOME);
			island.removePermission(otherPlayer, PlayerPermissions.CAN_SET_WARP);
			island.removePermission(otherPlayer, PlayerPermissions.CAN_KILL_ENTITIES);
			island.removePermission(otherPlayer, PlayerPermissions.CAN_OPEN_INVENTORIES);
			
			new PlayerPermissionsMenu(p,otherPlayer).o(p);
		} else if (slot==31) {
			for (PlayerPermissions perm : PlayerPermissions.values()) {
				island.removePermission(otherPlayer, perm);
			}
			new PlayerPermissionsMenu(p,otherPlayer).o(p);
		} else if (slot==35) {
			new PermissionsMainMenu(p).o(p);
		} else if (slot==27){
			SkyblockManager.getManager().removeFromIsland(otherPlayer, island,true);
			if (island.getMembers().size()>0) {
			new PermissionsMainMenu(p).o(p);
			} else {
				new IslandMainMenu(p).o(p);
			}
		}else { 
			PlayerPermissions perm = null;
			if (slot==19) {
				perm = PlayerPermissions.CAN_BREAK;
		} else if (slot==20) {
			perm = PlayerPermissions.CAN_KILL_ENTITIES;
		} else if (slot==21) {
			perm = PlayerPermissions.CAN_OPEN_INVENTORIES;
		} else if (slot==22) {
			perm = PlayerPermissions.CAN_PLACE;
		} else if (slot==23) {
			perm = PlayerPermissions.CAN_SET_HOME;
		} else if (slot==24) {
			perm = PlayerPermissions.CAN_SET_WARP;
		} else if (slot==25) {
			perm = PlayerPermissions.CAN_DO_HOME;
		}
			if (perm!=null) {
				if (island.hasPermission(otherPlayer, perm)) {
					island.removePermission(otherPlayer, perm);
				} else {
				island.addPermission(otherPlayer, perm);	
				}
				new PlayerPermissionsMenu(p,otherPlayer).o(p);
			}
	}
	
}
	
	
	}



