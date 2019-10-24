package me.santipingui58.celestialmc.game.rank;

public enum Rank {
 DEFAULT,SKYBORN,RECRUIT,TIMEKEEPER,BALLER,ACTIVIST,CRAFTSMAN,BLACKSMITH,LUMBERJACK,TROOPER,DRIFTER,SKYHERO,SKYLORD,SKYLEGEND,AIRBORN,FARMER,INFINITY,HUSTLER,SKYGOD;
	
	
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
		
}
