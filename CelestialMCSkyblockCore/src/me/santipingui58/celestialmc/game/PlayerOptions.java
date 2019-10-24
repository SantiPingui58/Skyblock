package me.santipingui58.celestialmc.game;

public class PlayerOptions {

private boolean chat;
private boolean scoreboard;

	public PlayerOptions(boolean chat, boolean scoreboard) {
		this.chat = chat;
		this.scoreboard = scoreboard;
	}
	
	public boolean hasChatEnabled() {
		return this.chat;
	}
	public void chat(boolean b) {
		this.chat = b;
	}
	
	public boolean hasScoreboardEnabled() {
		return this.scoreboard;
	}
	
	public void scoreboard(boolean b) {
		this.scoreboard = b;
	}
	
}
