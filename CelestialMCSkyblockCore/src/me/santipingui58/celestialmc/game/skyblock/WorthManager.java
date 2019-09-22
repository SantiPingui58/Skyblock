package me.santipingui58.celestialmc.game.skyblock;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import me.santipingui58.celestialmc.game.stacking.StackeableBlockType;
import me.santipingui58.celestialmc.game.stacking.StackedBlock;

public class WorthManager {

	private static WorthManager manager;	
	 public static WorthManager getManager() {
	        if (manager == null)
	        	manager = new WorthManager();
	        return manager;
	    }
	
	
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
		return hashmap;
		
	}
	
	public int getBlocksAmount(SkyblockIsland island, StackeableBlockType type) {
		int amount = 0;
		for (StackedBlock sblock : island.getStackedBlocks()) {
			if (sblock.getType().equals(type)) {
				amount = amount + sblock.getAmount();
			}
		}
		return amount;
	}
}
