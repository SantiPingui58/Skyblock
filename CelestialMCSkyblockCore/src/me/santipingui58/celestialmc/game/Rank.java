package me.santipingui58.celestialmc.game;

import org.bukkit.ChatColor;

public enum Rank {
 DEFAULT,SKYBORN,RECRUIT,TIMEKEEPER,BALLER,ACTIVIST,CRAFTSMAN,BLACKSMITH,LUMBERJACK,TROOPER,DRIFTER,SKYHERO,SKYLORD,SKYLEGEND,AIRBORN,FARMER,INFINITY,HUSTLER,SKYGOD;
	
	
	public String getName() {
		String name = "";
		if (this.equals(SKYGOD)) {
			name = "SkyGod";
			} else if (this.equals(HUSTLER)) {
				name = "Hustler";
			} else if (this.equals(INFINITY)) {
				name = "Infinity";
			} else if (this.equals(FARMER)) {
				name = "Farmer";
			} else if (this.equals(AIRBORN)) {
				name = "Airborn";
			} else if (this.equals(SKYLEGEND)) {
				name = "SkyLegend";
			} else if (this.equals(SKYLORD)) {
				name = "SkyLord";
			} else if (this.equals(SKYHERO)) {
				name = "SkyHero";
			} else if (this.equals(DRIFTER)) {
				name = "Drifter";
			} else if (this.equals(TROOPER)) {
				name = "Trooper";
			} else if (this.equals(LUMBERJACK)) {
				name = "Lumberjack";
			} else if (this.equals(BLACKSMITH)) {
				name = "Blacksmith";
			} else if (this.equals(CRAFTSMAN)) {
				name = "Craftsman";
			} else if (this.equals(ACTIVIST)) {
				name = "Activist";
			} else if (this.equals(BALLER)) {
				name = "Baller";
			} else if (this.equals(TIMEKEEPER)) {
				name = "Timekeeper";
			} else if (this.equals(RECRUIT)) {
				name = "Recruit";
			} else if (this.equals(SKYBORN)) {
				name = "SkyBorn";
			} else {
				name = "Default";
			}
		return ChatColor.ITALIC + ""+ ChatColor.GRAY+ "("+name+")";
	}
	
	public int toLevel() {
		if (this.equals(SKYGOD)) {
			return 19;
			} else if (this.equals(HUSTLER)) {
				return 18;
			} else if (this.equals(INFINITY)) {
				return 17;
			} else if (this.equals(FARMER)) {
				return 16;
			} else if (this.equals(AIRBORN)) {
				return 15;
			} else if (this.equals(SKYLEGEND)) {
				return 14;
			} else if (this.equals(SKYLORD)) {
				return 13;
			} else if (this.equals(SKYHERO)) {
				return 12;
			} else if (this.equals(DRIFTER)) {
				return 11;
			} else if (this.equals(TROOPER)) {
				return 10;
			} else if (this.equals(LUMBERJACK)) {
				return 9;
			} else if (this.equals(BLACKSMITH)) {
				return 8;
			} else if (this.equals(CRAFTSMAN)) {
				return 7;
			} else if (this.equals(ACTIVIST)) {
				return 6;
			} else if (this.equals(BALLER)) {
				return 5;
			} else if (this.equals(TIMEKEEPER)) {
				return 4;
			} else if (this.equals(RECRUIT)) {
				return 3;
			} else if (this.equals(SKYBORN)) {
				return 2;
			} else {
				return 1;
			}
		}
		
	
	public int getValue() {
		if (this.equals(SKYBORN)) {
			return 100;
		} else if (this.equals(RECRUIT)) {
			return 200;
		} else if (this.equals(TIMEKEEPER)) {
			return 500;
		} else if (this.equals(BALLER)) {
			return 1000;
		} else if (this.equals(ACTIVIST)) {
			return 2000;
		} else if (this.equals(CRAFTSMAN)) {
			return 5000;
		} else if (this.equals(BLACKSMITH)) {
			return 10000;
		} else if (this.equals(LUMBERJACK)) {
			return 20000;
		} else if (this.equals(TROOPER)) {
			return 50000;
		} else if (this.equals(DRIFTER)) {
			return 100000;
		} else if (this.equals(SKYHERO)) {
			return 200000;
		} else if (this.equals(SKYLORD)) {
			return 500000;
		} else if (this.equals(SKYLEGEND)) {
			return 1000000;
		} else if (this.equals(AIRBORN)) {
			return 2000000;
		} else if (this.equals(FARMER)) {
			return 5000000;
		} else if (this.equals(INFINITY)) {
			return 10000000;
		} else if (this.equals(HUSTLER)) {
			return 20000000;
		} else if (this.equals(SKYGOD)) {
			return 50000000;
		}
		return 0;
	}
}
