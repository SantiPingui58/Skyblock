package me.santipingui58.celestialmc.gui.island;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import me.santipingui58.celestialmc.game.CelestialPlayer;
import me.santipingui58.celestialmc.game.skyblock.SkyblockIsland;
import me.santipingui58.celestialmc.game.skyblock.SkyblockManager;
import me.santipingui58.celestialmc.game.skyblock.WorthManager;
import me.santipingui58.celestialmc.gui.MenuBuilder;
import me.santipingui58.celestialmc.utils.ItemBuilder;


public class IslandWorthTopMenu extends MenuBuilder {
		
	public IslandWorthTopMenu(Player p) {
	super("§2Island Worth Top",5);
	if (WorthManager.getManager().getWorthHashMap().isEmpty()) {
		WorthManager.getManager().sortWorthTop();
	}
	CelestialPlayer cplayer = SkyblockManager.getManager().getCelestialPlayer(p);
	int slot = 0;
	Iterator<Entry<SkyblockIsland, Integer>> it = WorthManager.getManager().getWorthHashMap().entrySet().iterator();
    while (it.hasNext()) {
        Map.Entry<SkyblockIsland,Integer> pair = (Map.Entry<SkyblockIsland,Integer>)it.next();
       SkyblockIsland island = pair.getKey();
      int w = pair.getValue();
      
      if (slot+9<=53*(cplayer.getWorthTopPage()+1)) {
      
      	ItemStack player = new ItemStack(Material.PLAYER_HEAD);		
		 SkullMeta playerMeta = (SkullMeta) player.getItemMeta();
		 OfflinePlayer off = island.getOwner().getOfflinePlayer();
		 playerMeta.setOwningPlayer(off);
		playerMeta.setDisplayName("§a"+off.getName() + "'s Island");
		List<String> lore = new ArrayList<String>();
		lore.add("§b"+off.getName());
		lore.add("");
		lore.add("§fTotal Worth: §2§l" + w);
		lore.add("");
		lore.add("§aLeft click to view more info of the Island");
		if (island.isWarpEnabled()) {
		lore.add("§aRight click to teleport to the Island");
		} else {
			lore.add("§cThe owner of this Island doesnt allow visits to the Island");
		}
		playerMeta.setLore(lore);
		player.setItemMeta(playerMeta);
		s(slot+9,player);
		slot++;
    } else {
    	break;
    }
    }
    if (cplayer.getWorthTopPage()>0) {
    	s(0,new ItemBuilder(Material.ARROW).setTitle("§aGo to page" + cplayer.getWorthTopPage()).build());
    }
    
    if (cplayer.getWorthTopPage()<WorthManager.getManager().getWorthHashMap().size()/53) {
    	s(8,new ItemBuilder(Material.ARROW).setTitle("§aGo to page" + cplayer.getWorthTopPage()+2).build());
    }
	}
	

	@Override
	public void onClick(Player p, ItemStack stack, int slot,InventoryClickEvent event) {
	}
	
	
	
	
	}



