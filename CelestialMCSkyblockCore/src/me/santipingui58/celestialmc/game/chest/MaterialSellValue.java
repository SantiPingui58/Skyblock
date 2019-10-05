package me.santipingui58.celestialmc.game.chest;


import org.bukkit.Material;

public class MaterialSellValue {

	
	
	public static int getValue(Material m) {
		if (m.equals(Material.ACACIA_LOG)) {
			return 2;
		} else if (m.equals(Material.ACTIVATOR_RAIL)) {
			return 3;
		} else if (m.equals(Material.ANVIL)) {
			return 1500;
		} else if (m.equals(Material.APPLE)) {
			return 10;
		} else if (m.equals(Material.ARMOR_STAND)) {
			return 5;
		} else if (m.equals(Material.BEACON)) {
			return 10000;
		} else if (m.equals(Material.BELL)) {
			return 200;
		} else if (m.equals(Material.BIRCH_LOG)) {
			return 2;
		} else if (m.equals(Material.BLACK_SHULKER_BOX)) {
			return 100;
		} else if (m.equals(Material.BLAST_FURNACE)) {
			return 5;
		} else if (m.equals(Material.BLAZE_POWDER)) {
			return 10;
		} else if (m.equals(Material.BLAZE_ROD)) {
			return 20;
		} else if (m.equals(Material.BLUE_ICE)) {
			return 80;
		} else if (m.equals(Material.BLUE_SHULKER_BOX)) {
			return 200;
		} else if (m.equals(Material.BONE)) {
			return 2;
		} else if (m.equals(Material.BONE_BLOCK)) {
			return 15;
		} else if (m.equals(Material.BOOK)) {
			return 2;
		} else if (m.equals(Material.BOOKSHELF)) {
			return 10;
		} else if (m.equals(Material.BOW)) {
			return 2;
		} else if (m.equals(Material.CHAINMAIL_BOOTS)) {
			return 15;
		} else if (m.equals(Material.CHAINMAIL_CHESTPLATE)) {
			return 20;
		} else if (m.equals(Material.CHAINMAIL_HELMET)) {
			return 15;
		} else if (m.equals(Material.CHAINMAIL_LEGGINGS)) {
			return 20;
		} else if (m.equals(Material.BREWING_STAND)) {
			return 25;
		} else if (m.equals(Material.COAL_BLOCK)) {
			return 10;
		} else if (m.equals(Material.COBWEB)) {
			return 2;
		} else if (m.equals(Material.CREEPER_HEAD)) {
			return 3000;
		} else if (m.equals(Material.DRAGON_HEAD)) {
			return 15000;
		} else if (m.equals(Material.PLAYER_HEAD)) {
			return 1000;
		} else if (m.equals(Material.ZOMBIE_HEAD)) {
			return 500;
		} else if (m.equals(Material.SKELETON_SKULL)) {
			return 500;
		} else if (m.equals(Material.WITHER_SKELETON_SKULL)) {
			return 2500;
		} else if (m.equals(Material.CROSSBOW)) {
			return 2;
		}
 		return 1;
	}
	
	
	
}
