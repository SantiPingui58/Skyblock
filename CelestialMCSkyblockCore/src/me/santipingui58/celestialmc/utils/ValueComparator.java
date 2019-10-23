package me.santipingui58.celestialmc.utils;

import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeMap;

import me.santipingui58.celestialmc.game.skyblock.SkyblockIsland;

public class ValueComparator implements Comparator<SkyblockIsland>{
	 
	HashMap<SkyblockIsland, Integer> map = new HashMap<SkyblockIsland, Integer>();
 
	public ValueComparator(TreeMap<SkyblockIsland, Integer> map2){
		this.map.putAll(map2);
	}
 
	@Override
	public int compare(SkyblockIsland s1, SkyblockIsland s2) {
		if(map.get(s1) >= map.get(s2)){
			return -1;
		}else{
			return 1;
		}	
	}
}