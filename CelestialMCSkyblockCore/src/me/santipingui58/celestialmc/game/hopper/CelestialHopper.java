package me.santipingui58.celestialmc.game.hopper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import me.santipingui58.celestialmc.Main;
import me.santipingui58.celestialmc.game.CelestialPlayer;
import me.santipingui58.celestialmc.game.skyblock.SkyblockIsland;
import me.santipingui58.celestialmc.game.skyblock.SkyblockManager;

public class CelestialHopper {

	private CelestialPlayer owner;
	private Location location;
	private UUID uuid;
	private int level;
	private int money;
	private boolean isautosellactivated;
	private boolean ischunkpickupactivated;
	
	private boolean transfering;
	private Inventory destiny;
	private Inventory source;
	
	public CelestialHopper(UUID uuid, CelestialPlayer owner, Location location,int level) {
		if (uuid==null) {
			this.uuid = UUID.randomUUID();
		} else {
			this.uuid = uuid;	
		}
		
		this.owner = owner;
		this.location = location;
		this.level = level;			
	}
	
	public int getMoney() {
		return this.money;
	}
	
	public void setMoney(int i) {
		this.money = i;
	}
	public boolean isAutoSellActivated() {
		return this.isautosellactivated;
	}
	public void activeAutoSell() {
		this.isautosellactivated = true;
	}
	
	public void desactivateAutoSell() {
		this.isautosellactivated = false;
	}
	
	
	public boolean isChunkPickUpActivated() {
		return this.ischunkpickupactivated;
	}
	public void activeChunkPickUp() {
		this.ischunkpickupactivated = true;
	}
	
	public void desactiveChunkPickUp() {
		this.ischunkpickupactivated = false;
	}
	
	
	public boolean isTransfering() {
		return this.transfering;
	}
	
	public void transfer(Inventory destiny,Inventory source) {
		this.transfering = true;
		this.destiny = destiny;
		this.source = source;
		t();
		
	}
	public void stopTransfer() {
		this.transfering = false;
	}
	
	
	public int getLevel() {
		return this.level;
	}
	
	public void levelUp( ) {
		if (this.level<4) {
			this.level = this.level+1;
		}
	}
	public UUID getUUID() {
		return this.uuid;
	}
	
	public CelestialPlayer getOwner() {
		return this.owner;
	}
	
	public Location getLocation() {
		return this.location;
	}
	public void place(Location l) {
		this.location = l;
		SkyblockIsland island = SkyblockManager.getManager().getIslandByLocation(location);
		if (island!=null) {
			if (!island.getHoppers().contains(this)) {
			island.getHoppers().add(this);
			}
		}
	}
	
	public void pickUp() {
		this.location = null;
	}
	
	public boolean isPlaced() {
		if (this.location==null) {
			return false;
		} else {
			return true;
		}
	}
	
	
		
	public ItemStack getItem() {
		ItemStack item = new ItemStack(Material.HOPPER);
		ItemMeta meta = item.getItemMeta();
		List<String> lore = new ArrayList<String>();
		lore.add("§7Place this hopper to");
		lore.add("§7transfer items to another hoppers or chest.");
		lore.add("§7(Right click while sneaking to open Hopper Menu.)");
		lore.add(" ");
		lore.add("§3§lLevel: " + this.level);
		lore.add(" ");
		lore.add("§cOriginal Owner: §3" + Bukkit.getOfflinePlayer(getOwner().getUUID()).getName());
		lore.add(" ");
		lore.add("§8"+getUUID().toString());
		lore.add(" ");
		meta.setLore(lore);
		meta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);		
		meta.setDisplayName("§7§lHopper");
		item.setItemMeta(meta);
		return item;
	}
	
	
	private void t() {		
		Inventory destiny = this.destiny;
		Inventory source = this.source;
	
		new BukkitRunnable() {
			int i = 0;			
			int t = 1;		
			@Override
			public void run() {		
				if (getLevel()==2) {
					t = 2;
				} else if (getLevel()==3) {
					t=4;
				} else if (getLevel()==4) {
					t=8;
				}
				
					if (!isEmpty(source)) {
					if (isPlaced()) {
				
				if (i<source.getContents().length) {
					try {
						
						if (source.getItem(i).getAmount()<t) {
							t = source.getItem(i).getAmount();
						}
						
					ItemStack item = new ItemStack(source.getItem(i).getType(),t);				
					int a = source.getItem(i).getAmount()-t;
				source.getItem(i).setAmount(a);
				HashMap<Integer,ItemStack> map = destiny.addItem(item);
				if (!map.isEmpty()) {
					i++;
				}
				} catch(Exception e) {
					i++;
				}
				} else {
					this.cancel();
					stopTransfer();
				}
			} else {
				this.cancel();
				stopTransfer();
			}
				} else {
					this.cancel();
					stopTransfer();
				}
				 
			}
		}.runTaskTimerAsynchronously(Main.get(), 0L, 8L);
		
	}
	
	private boolean isEmpty(Inventory i) {
		for(ItemStack it : i.getContents())
		{
		    if(it != null) return false;
		}
		return true;
	}
	
}
