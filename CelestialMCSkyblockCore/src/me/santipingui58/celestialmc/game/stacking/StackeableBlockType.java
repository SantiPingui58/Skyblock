package me.santipingui58.celestialmc.game.stacking;

import org.bukkit.Material;

public enum StackeableBlockType {
	
	BEDROCK,END_PORTAL,DRAGON_EGG,BEACON,DRAGON_HEAD,WITHER_HEAD,CREEPER_HEAD,SKELETON_HEAD,ZOMBIE_HEAD,PLAYER_HEAD,GLOWSTONE,DIAMOND_BLOCK,EMERALD_BLOCK,
	MAGMA_BLOCK,SPAWNERS,TNT,ENCHANTING_TABLE,SPONGE,BLUE_ICE,SEA_LANTERN,PACKED_ICE,ANVIL,GOLD_BLOCK,IRON_BLOCK,BOOKSHELF,REDSTONE_BLOCK,LAPIS_BLOCK,
	OBSIDIAN,SOUL_SAND,PURPUR,HAY_BLOCK;
	
	public Material toMaterial() {
		if (this.equals(BEDROCK)) {
			return Material.BEDROCK;
		} else if (this.equals(END_PORTAL)) {
			return Material.END_PORTAL;
		} else if (this.equals(DRAGON_EGG)) {
			return Material.DRAGON_EGG;
		} else if (this.equals(BEACON)) {
			return Material.BEACON;
		} else if (this.equals(DRAGON_HEAD)) {
			return Material.DRAGON_HEAD;
		} else if (this.equals(WITHER_HEAD)) {
			return Material.WITHER_SKELETON_SKULL;
		} else if (this.equals(SKELETON_HEAD)) {
			return Material.SKELETON_SKULL;
		} else if (this.equals(ZOMBIE_HEAD)) {
			return Material.ZOMBIE_HEAD;
		} else if (this.equals(DIAMOND_BLOCK)) {
			return Material.DIAMOND_BLOCK;
		} else if (this.equals(PLAYER_HEAD)) {
			return Material.PLAYER_HEAD;
		} else if (this.equals(GLOWSTONE))  {
			return Material.GLOWSTONE;
		} else if (this.equals(EMERALD_BLOCK)) {
			return Material.EMERALD_BLOCK;
		} else if (this.equals(MAGMA_BLOCK)) {
			return Material.MAGMA_BLOCK;
		} else if (this.equals(SPAWNERS)) {
			return Material.SPAWNER;
		} else if (this.equals(TNT)) {
			return Material.TNT;
		} else if (this.equals(ENCHANTING_TABLE)) {
			return Material.ENCHANTING_TABLE;
		} else if (this.equals(SPONGE)) {
			return Material.SPONGE;
		} else if (this.equals(BLUE_ICE)) {
			return Material.BLUE_ICE;
		 } else if (this.equals(SEA_LANTERN)) {
			 return Material.SEA_LANTERN;
		 } else if (this.equals(PACKED_ICE)) {
			 return Material.PACKED_ICE;
		 } else if (this.equals(ANVIL)) {
			 return Material.ANVIL;
		 } else if (this.equals(GOLD_BLOCK)) {
			 return Material.GOLD_BLOCK;
		 } else if (this.equals(IRON_BLOCK)) {
			 return Material.IRON_BLOCK;
		 } else if (this.equals(BOOKSHELF)) {
			 return Material.BOOKSHELF;
		 } else if (this.equals(REDSTONE_BLOCK)) {
			 return Material.REDSTONE_BLOCK;
		 } else if (this.equals(LAPIS_BLOCK)) {
			 return Material.LAPIS_BLOCK;
		 } else if (this.equals(OBSIDIAN)) {
			 return Material.OBSIDIAN;
		 } else if (this.equals(SOUL_SAND)) {
			 return Material.SOUL_SAND;
		 } else if (this.equals(PURPUR)) {
			 return Material.PURPUR_BLOCK;
		 } else if (this.equals(HAY_BLOCK)) {
			 return Material.HAY_BLOCK;
		 }
		return null;
	}
	
	public static StackeableBlockType fromMaterial(Material material) {
		 if (material.equals(Material.BEDROCK)) {
			return BEDROCK;
		} else if (material.equals(Material.END_PORTAL)) {
			return END_PORTAL;
		} else if (material.equals(Material.DRAGON_EGG)) {
			return DRAGON_EGG;
		} else if (material.equals(Material.BEACON)) {
			return BEACON;
		} else if (material.equals(Material.DRAGON_HEAD)) {
			return DRAGON_HEAD;
		} else if (material.equals(Material.WITHER_SKELETON_SKULL)) {
			return WITHER_HEAD;
		} else if (material.equals(Material.CREEPER_HEAD)) {
			return CREEPER_HEAD;
		} else if (material.equals(Material.SKELETON_SKULL)) {
			return SKELETON_HEAD;
		} else if (material.equals(Material.ZOMBIE_HEAD)) {
			return ZOMBIE_HEAD;
		} else if (material.equals(Material.PLAYER_HEAD)) {
			return PLAYER_HEAD;
		} else if (material.equals(Material.GLOWSTONE)) {
			return GLOWSTONE;
		} else if (material.equals(Material.DIAMOND_BLOCK)) {
			return DIAMOND_BLOCK;
		} else if (material.equals(Material.EMERALD_BLOCK)) {
			return EMERALD_BLOCK;
		} else if (material.equals(Material.MAGMA_BLOCK)) {
			return MAGMA_BLOCK;
		} else if (material.equals(Material.SPAWNER)) {
			return SPAWNERS;
		} else if (material.equals(Material.TNT)) {
			return TNT;
		} else if (material.equals(Material.ENCHANTING_TABLE)) {
			return ENCHANTING_TABLE;
		} else if (material.equals(Material.SPONGE)) {
			return SPONGE;
		} else if (material.equals(Material.BLUE_ICE)) {
			return BLUE_ICE;
		} else if (material.equals(Material.SEA_LANTERN)) {
			return SEA_LANTERN;
		} else if (material.equals(Material.PACKED_ICE)) {
			return PACKED_ICE;
		} else if (material.equals(Material.ANVIL)) {
			return ANVIL;
		} else if (material.equals(Material.GOLD_BLOCK)) {
			return GOLD_BLOCK;
		} else if (material.equals(Material.IRON_BLOCK)) {
			return IRON_BLOCK;
		} else if (material.equals(Material.BOOKSHELF)) {
			return BOOKSHELF;
		} else if (material.equals(Material.REDSTONE_BLOCK)) {
			return REDSTONE_BLOCK;
		} else if (material.equals(Material.LAPIS_BLOCK)) {
			return LAPIS_BLOCK;
		} else if (material.equals(Material.OBSIDIAN)) {
			return OBSIDIAN;
		} else if (material.equals(Material.SOUL_SAND)) {
			return SOUL_SAND;
		} else if (material.equals(Material.PURPUR_BLOCK)) {
			return PURPUR;
		} else if (material.equals(Material.HAY_BLOCK)) {
			return HAY_BLOCK;
		}
		return null;
	}
	
	public int getValue() {
		if (this.equals(BEDROCK)) {
			return 10000;
		} else if (this.equals(END_PORTAL)) {
			return 5000;
		} else if (this.equals(DRAGON_EGG)) {
			return 3000;
		} else if (this.equals(BEACON)) {
			return 2500;
		} else if (this.equals(DRAGON_HEAD)) {
			return 25000;
		} else if (this.equals(WITHER_HEAD)) {
			return 2000;
		} else if (this.equals(CREEPER_HEAD)) {
			return 1500;
		} else if (this.equals(SKELETON_HEAD)) {
			return 1250;
		} else if (this.equals(ZOMBIE_HEAD)) {
			return 1250;
		} else if (this.equals(PLAYER_HEAD)) {
			return 1000;
		} else if (this.equals(GLOWSTONE)) {
			return 1000;
		} else if (this.equals(DIAMOND_BLOCK)) {
			return 750;
		} else if (this.equals(EMERALD_BLOCK)) {
			return 600;
		} else if (this.equals(MAGMA_BLOCK)) {
			return 600;
		} else if (this.equals(TNT)) {
			return 400;
		} else if (this.equals(ENCHANTING_TABLE)) {
			return 350;
		} else if (this.equals(SPONGE)) {
			return 300;
		} else if (this.equals(BLUE_ICE)) {
			return 250;
		} else if (this.equals(SEA_LANTERN)) {
			return 250;
		} else if (this.equals(PACKED_ICE)) {
			return 200;
		} else if (this.equals(ANVIL)) {
			return 150;
		} else if (this.equals(GOLD_BLOCK)) {
			return 150;
		} else if (this.equals(IRON_BLOCK)) {
			return 75;
		} else if (this.equals(BOOKSHELF)) {
			return 30;
		} else if (this.equals(REDSTONE_BLOCK)) {
			return 50;
		} else if (this.equals(LAPIS_BLOCK)) {
			return 50;
		} else if (this.equals(OBSIDIAN)) {
			return 40; 
		} else if (this.equals(SOUL_SAND)) {
			return 30;
		} else if (this.equals(PURPUR)) {
			return 25;
		} else if (this.equals(HAY_BLOCK)) {
			return 10;
		}
		return 0;
	}
}
