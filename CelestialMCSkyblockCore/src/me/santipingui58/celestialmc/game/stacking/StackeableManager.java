package me.santipingui58.celestialmc.game.stacking;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class StackeableManager implements Listener {
	private static StackeableManager manager;	
	 public static StackeableManager getManager() {
	        if (manager == null)
	        	manager = new StackeableManager();
	        return manager;
	    }
	 
	 private List<StackedBlock> stackedblocks = new ArrayList<StackedBlock>();
	 private List<SimpleBlock> simpleblocks = new ArrayList<SimpleBlock>();
	 
	 public List<StackedBlock> getStackedBlocks() {
		 return this.stackedblocks;
	 }
	 
	 public List<SimpleBlock> getSimpleBlocks() {
		 return this.simpleblocks;
	 }
	 
	 public void removeStackedBlock(StackedBlock sblock) {
		 sblock.getHologram().delete();
		 StackeableManager.getManager().getStackedBlocks().remove(sblock);
		 if (sblock.getIsland()!=null) {
			 sblock.getIsland().getStackedBlocks().remove(sblock);
		 }
	 }
	 
	 public void removeSimpleBlock(SimpleBlock sblock) {
		 StackeableManager.getManager().getSimpleBlocks().remove(sblock);
		 if (sblock.getIsland()!=null) {
			 sblock.getIsland().getBlocks().remove(sblock);
		 }
	 }
	 
	 public boolean thereIsStackedBlockAtLocation(Location l) {
		 for (StackedBlock sblock : StackeableManager.getManager().getStackedBlocks()) {
			 if (sblock.getLocation().equals(l)) {
				 return true;
			 }
		 } 
		 return false;
	 }
	 
	 public boolean thereIsSimpleBlockAtLocation(Location l) {
		 for (SimpleBlock sblock : StackeableManager.getManager().getSimpleBlocks()) {
			 if (sblock.getLocation().equals(l)) {
				 return true;
			 }
		 } 
		 return false;
	 }
	 
	 public StackedBlock getStackedBlockAt(Location l) {
		 for (StackedBlock sblock : StackeableManager.getManager().getStackedBlocks()) {
			 if (sblock.getLocation().equals(l)) {
				 return sblock;
			 }
		 }
		 return null;
	 }
	 
	 
	 public SimpleBlock getSimpleBlockAt(Location l) {
		 for (SimpleBlock sblock : StackeableManager.getManager().getSimpleBlocks()) {
			 if (sblock.getLocation().equals(l)) {
				 return sblock;
			 }
		 } 
		 return null;
	 }
	 
	 @EventHandler 
	 public void onInteract(PlayerInteractEvent e) {
		 if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
		 Player p = e.getPlayer();
		 if (!p.isSneaking()) {
			 if (e.getItem()!=null) {
		 if (StackeableBlockType.fromMaterial(e.getItem().getType())!=null && StackeableBlockType.fromMaterial(e.getClickedBlock().getType())!=null) {
			 StackeableBlockType block = StackeableBlockType.fromMaterial(e.getItem().getType());
			 StackeableBlockType against = StackeableBlockType.fromMaterial(e.getClickedBlock().getType());
			 if (block==against) {
				 if (StackeableManager.getManager().thereIsStackedBlockAtLocation(e.getClickedBlock().getLocation())) {
					 StackedBlock sblock = StackeableManager.getManager().getStackedBlockAt(e.getClickedBlock().getLocation());	
					 sblock.setAmount(sblock.getAmount()+1);
				 } else {
					 if (thereIsSimpleBlockAtLocation(e.getClickedBlock().getLocation())) {
						 removeSimpleBlock(StackeableManager.getManager().getSimpleBlockAt(e.getClickedBlock().getLocation()));
					 }
					 StackedBlock sblock = new StackedBlock(UUID.randomUUID(),block,e.getClickedBlock().getLocation(),2);
					 StackeableManager.getManager().getStackedBlocks().add(sblock);
				 }
				 				
				 if (e.getItem().getAmount()>1) {
				 e.getItem().setAmount(e.getItem().getAmount()-1);
				 } else {
					 p.getInventory().removeItem(e.getItem());
				 }
				 e.setCancelled(true);
			 }
		 }
	 }
		 }
	 }
	 }
	 

	 
	 @EventHandler
	 public void onBreak(BlockBreakEvent e) {
		 if (StackeableBlockType.fromMaterial(e.getBlock().getType()) !=null) {
			 if (StackeableManager.getManager().thereIsStackedBlockAtLocation(e.getBlock().getLocation())) {
				 StackedBlock sblock = StackeableManager.getManager().getStackedBlockAt(e.getBlock().getLocation()); 
				 
				 ItemStack items = new ItemStack(sblock.getType().toMaterial());
				 items.setAmount(sblock.getAmount());
				 e.getBlock().getWorld().dropItem(e.getBlock().getLocation(), items);
				 removeStackedBlock(sblock);
				 e.setCancelled(true);
				 e.getBlock().setType(Material.AIR);
				 
			 } else if (StackeableManager.getManager().thereIsSimpleBlockAtLocation(e.getBlock().getLocation())) {
				 SimpleBlock sblock = StackeableManager.getManager().getSimpleBlockAt(e.getBlock().getLocation());
				 removeSimpleBlock(sblock);
			 }
		 }
	 }
	 
	 @EventHandler
	 public void onPlace(BlockPlaceEvent e) {
		 if (StackeableBlockType.fromMaterial(e.getBlock().getType())!=null) {
			 if (!StackeableManager.getManager().thereIsStackedBlockAtLocation(e.getBlock().getLocation())) {
				 StackeableBlockType type = StackeableBlockType.fromMaterial(e.getBlock().getType());
				 SimpleBlock sblock = new SimpleBlock(UUID.randomUUID(),type,e.getBlock().getLocation());
				 StackeableManager.getManager().getSimpleBlocks().add(sblock);
			 }
		 }
	 }
	 
}
