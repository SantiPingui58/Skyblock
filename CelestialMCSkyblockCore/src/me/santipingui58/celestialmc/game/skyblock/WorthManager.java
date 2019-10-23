package me.santipingui58.celestialmc.game.skyblock;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import me.santipingui58.celestialmc.game.DataManager;
import me.santipingui58.celestialmc.game.stacking.SimpleBlock;
import me.santipingui58.celestialmc.game.stacking.StackeableBlockType;
import me.santipingui58.celestialmc.game.stacking.StackedBlock;
import me.santipingui58.celestialmc.utils.Utils;

public class WorthManager {

	private static WorthManager manager;	
	 public static WorthManager getManager() {
	        if (manager == null)
	        	manager = new WorthManager();
	        return manager;
	    }
	
	private TreeMap<SkyblockIsland, Integer> worth = new TreeMap<SkyblockIsland,Integer>();
	

	
	public int getTotalWorth(SkyblockIsland island) {
		int totalworth = 0;
		Iterator<Entry<StackeableBlockType, Integer>> it = WorthManager.getManager().getAllBlocksAmount(island).entrySet().iterator();
	    while (it.hasNext()) {
	        Entry<StackeableBlockType, Integer> pair = (Map.Entry<StackeableBlockType,Integer>)it.next();
	        StackeableBlockType type = pair.getKey();
	        int amount = pair.getValue();
	        totalworth = totalworth + type.getValue()*amount;
	}
	    return totalworth;
	}
	
	public int getWorth(SkyblockIsland island, StackeableBlockType type) {
		int worth = 0;
		int amount = WorthManager.getManager().getBlocksAmount(island, type);
		worth = amount*type.getValue();
		return worth;
	}
	
	
	public HashMap<StackeableBlockType,Integer> getAllBlocksAmount(SkyblockIsland island) {		
		HashMap<StackeableBlockType,Integer> hashmap = new HashMap<StackeableBlockType,Integer>();
		for (StackedBlock sblock : island.getStackedBlocks()) {		
			for (StackeableBlockType type : StackeableBlockType.values()) {
				if (sblock.getType().equals(type)) {
					if (hashmap.containsKey(type)) {
						int i = hashmap.get(type) + sblock.getAmount();
						hashmap.remove(type);
						hashmap.put(type, i);
					} else {
						hashmap.put(type, sblock.getAmount());
					}
				}
			}
			
		}
		for (SimpleBlock sblock : island.getBlocks()) {
			for (StackeableBlockType type : StackeableBlockType.values()) {
				if (sblock.getType().equals(type)) {
					if (hashmap.containsKey(type)) {
						int i = hashmap.get(type) + 1;
						hashmap.remove(type);
						hashmap.put(type, i);
					} else {
						hashmap.put(type, 1);
					}
				}
			}
		}
		return hashmap;
		
	}
	
	public int getBlocksAmount(SkyblockIsland island, StackeableBlockType type) {
		int amount = 0;
		for (StackedBlock sblock : island.getStackedBlocks()) {
			if (sblock.getType().equals(type)) {
				amount = amount + sblock.getAmount();
			}
		}
		
		for (SimpleBlock sblock : island.getBlocks()) {
			if (sblock.getType().equals(type)) {
				amount++;
			}
		}
		return amount;
	}
	
	public TreeMap<SkyblockIsland,Integer> getWorthHashMap() {
		return this.worth;
	}
	
	public void sortWorthTop() {
		this.worth.clear();
		TreeMap<SkyblockIsland, Integer> hashmap = new TreeMap<SkyblockIsland,Integer>();
		for (SkyblockIsland island : DataManager.getManager().getIslands()) {
			int w = getTotalWorth(island);
			hashmap.put(island, w);
		}
	this.worth = Utils.getUtils().sortMapByValue(hashmap);
	
	}
	
	public TreeMap<SkyblockIsland,Integer> getWorthTopFromTo(int ini,int fin) {
		return this.worth;
	}
	
	
	
}

