package me.santipingui58.celestialmc.game.spawner;

import org.bukkit.entity.EntityType;

public enum SpawnerType {
	GHAST,IRON_GOLEM,BLAZE,SPIDER,CHICKEN,COW,CREEPER,GUARDIAN,ENDERMAN,DROWNED,MAGMA_CUBE,PIG,SHEEP,SILVERFISH,SKELETON,SLIME,WITCH,WITHER_SKELETON,ZOMBIE,PIGMAN;
	
	public EntityType toEntityType() {
		SpawnerType type = this;
		if (type.equals(GHAST)) {
			return EntityType.GHAST;
		} else if (type.equals(IRON_GOLEM)) {
			return EntityType.IRON_GOLEM;
		} else if (type.equals(BLAZE)) {
			return EntityType.BLAZE;
		} else if (type.equals(SPIDER)) {
			return EntityType.SPIDER;
		} else if (type.equals(CHICKEN)) {
			return EntityType.CHICKEN;
		} else if (type.equals(COW)) {
			return EntityType.COW;
		} else if (type.equals(CREEPER)) {
			return EntityType.CREEPER;
		}else if (type.equals(GUARDIAN)) {
			return EntityType.GUARDIAN;
		}else if (type.equals(ENDERMAN)) {
			return EntityType.ENDERMAN;
		}else if (type.equals(DROWNED)) {
			return EntityType.DROWNED;
		}else if (type.equals(MAGMA_CUBE)) {
			return EntityType.MAGMA_CUBE;
		}else if (type.equals(PIG)) {
			return EntityType.PIG;
		}else if (type.equals(SHEEP)) {
			return EntityType.SHEEP;
		}else if (type.equals(SILVERFISH)) {
			return EntityType.SILVERFISH;
		}else if (type.equals(SKELETON)) {
			return EntityType.SKELETON;
		}else if (type.equals(SLIME)) {
			return EntityType.SLIME;
		}else if (type.equals(WITCH)) {
			return EntityType.WITCH;
		}else if (type.equals(WITHER_SKELETON)) {
			return EntityType.WITHER_SKELETON;
		}else if (type.equals(ZOMBIE)) {
			return EntityType.ZOMBIE;
		}else {
			return EntityType.PIG_ZOMBIE;
	}
	}
}

