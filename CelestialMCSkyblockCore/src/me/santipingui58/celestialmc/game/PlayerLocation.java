package me.santipingui58.celestialmc.game;

public enum PlayerLocation {
SPAWN,OTHER_PLAYER_ISLAND,OWN_ISLAND,OFFLINE;
	
	public String asString() {
		if (this.equals(SPAWN)) {
			return "§aSpawn";
		} else if (this.equals(OTHER_PLAYER_ISLAND)) {
			return "§a%player%'s Island";
		} else if (this.equals(OWN_ISLAND)) {
			return "§aYour Island";
		}
		return null;
	}
}
