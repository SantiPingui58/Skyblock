package me.santipingui58.celestialmc.game.chest;



import org.bukkit.Material;

public enum AutoBlockMaterial {
 COAL,CHARCOAL,IRON,DIAMOND,EMERALD,GOLD,REDSTONE,LAPIS,QUARTZ,BOOK,GLOWSTONE,WHEAT,BONE;
 
	
	public static AutoBlockMaterial fromMaterial(Material mat) {
		if (mat.equals(Material.COAL)) {
			return COAL;
		} else if (mat.equals(Material.CHARCOAL)) {
			return CHARCOAL;
		} else if (mat.equals(Material.IRON_INGOT)) {
			return IRON;
		} else if (mat.equals(Material.DIAMOND)) {
			return DIAMOND;
		} else if (mat.equals(Material.EMERALD)) {
			return EMERALD;
		} else if (mat.equals(Material.GOLD_INGOT)) {
			return GOLD;
		} else if (mat.equals(Material.REDSTONE)) {
			return REDSTONE;
		} else if (mat.equals(Material.LAPIS_LAZULI)) {
			return LAPIS;
		} else if (mat.equals(Material.QUARTZ)) {
			return QUARTZ;
		} else if (mat.equals(Material.BOOK)) {
			return BOOK;
		} else if (mat.equals(Material.GLOWSTONE_DUST)) {
			return GLOWSTONE;
		} else if (mat.equals(Material.WHEAT)) {
			return WHEAT;
		} else if (mat.equals(Material.BONE)) {
			return BONE;
		}
		return null;
	}
	
	public int getAmount() {
		if (this.equals(QUARTZ) || this.equals(GLOWSTONE)) {
			return 4;
		}  else if (this.equals(BOOK)) {
			return 3;
		} else {
			return 9;
		}
	}
	
	
	public Material getBlock() {
		if (this.equals(COAL)) {
			return Material.COAL_BLOCK;
		} else if (this.equals(CHARCOAL)) {
			return Material.COAL_BLOCK;
		} else if (this.equals(IRON)) {
			return Material.IRON_BLOCK;
		} else if (this.equals(DIAMOND)) {
			return Material.DIAMOND_BLOCK;
		} else if (this.equals(EMERALD)) {
			return Material.EMERALD_BLOCK;
		} else if (this.equals(GOLD)) {
			return Material.GOLD_BLOCK;
		} else if (this.equals(REDSTONE)) {
			return Material.REDSTONE_BLOCK;
		} else if (this.equals(LAPIS)) {
			return Material.LAPIS_BLOCK;
		} else if (this.equals(QUARTZ)) {
			return Material.QUARTZ_BLOCK;
		} else if (this.equals(BOOK)) {
			return Material.BOOKSHELF;
		} else if (this.equals(GLOWSTONE)) {
			return Material.GLOWSTONE;
		} else if (this.equals(WHEAT)) {
			return Material.HAY_BLOCK;
		} else if (this.equals(BONE)) {
			return Material.BONE_BLOCK;
		}
		return null;
	}
	
}
