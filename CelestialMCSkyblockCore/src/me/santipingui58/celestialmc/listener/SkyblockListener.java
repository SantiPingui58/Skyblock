package me.santipingui58.celestialmc.listener;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockFormEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitScheduler;

import me.santipingui58.celestialmc.Main;
import me.santipingui58.celestialmc.game.CelestialPlayer;
import me.santipingui58.celestialmc.game.skyblock.PlayerPermissions;
import me.santipingui58.celestialmc.game.skyblock.SkyblockIsland;
import me.santipingui58.celestialmc.game.skyblock.SkyblockManager;
import me.santipingui58.celestialmc.utils.RandomCollection;

public class SkyblockListener implements Listener{
	
	 @EventHandler
	 public void onBreak(BlockBreakEvent e) {
		 if (e.getBlock().getLocation().getWorld().getName().equalsIgnoreCase("skyblock")) {
		 Player p = e.getPlayer();		 
		 CelestialPlayer cplayer = SkyblockManager.getManager().getCelestialPlayer(p);
		 SkyblockIsland island = SkyblockManager.getManager().getIslandByLocation(e.getBlock().getLocation());
		 
		 
		 if (e.getBlock().getType().equals(Material.STONE) && cplayer.isMiningKey()) {
			 SkyblockManager.getManager().giveCrateKey(cplayer);
		 }
		 if (island!=null) {	
				if (!island.getOwner().getUUID().toString().equalsIgnoreCase(cplayer.getUUID().toString()) && !island.hasPermission(cplayer, PlayerPermissions.CAN_BREAK)) {
					 e.setCancelled(true);
					 p.sendMessage(ChatColor.RED+"You do not have permission to do this.");		
					 return;
			 } else {
				 int x = island.getSpawnPoint().getBlockX();
					int z = island.getSpawnPoint().getBlockZ();
					int value_x = Math.abs(Math.abs(e.getBlock().getLocation().getBlockX())-x);
					int value_z = Math.abs(Math.abs(e.getBlock().getLocation().getBlockZ())-z);
					if (value_x>island.getSpace()/2 || value_z>island.getSpace()/2) {
						e.setCancelled(true);
						return;
					} 
			 }
				
		 }
		 } else {
			 if (!e.getPlayer().hasPermission("celestialmc.admin")) {
				 e.setCancelled(true);
			 }
		 }
	 }
	 
	 @EventHandler
	 public void onPlace(BlockPlaceEvent e) {
		 if (e.getBlock().getLocation().getWorld().getName().equalsIgnoreCase("skyblock")) {
			 Player p = e.getPlayer();		 
			 CelestialPlayer cplayer = SkyblockManager.getManager().getCelestialPlayer(p);
			 SkyblockIsland island = SkyblockManager.getManager().getIslandByLocation(e.getBlock().getLocation());
			 if (island!=null) {	
					if (!island.getOwner().getUUID().toString().equalsIgnoreCase(cplayer.getUUID().toString()) && !island.hasPermission(cplayer, PlayerPermissions.CAN_PLACE)) {
						 e.setCancelled(true);
						 p.sendMessage(ChatColor.RED+"You do not have permission to do this.");		
						 return;
				 } else {
					 int x = island.getSpawnPoint().getBlockX();
						int z = island.getSpawnPoint().getBlockZ();
						int value_x = Math.abs(Math.abs(e.getBlock().getLocation().getBlockX())-x);
						int value_z = Math.abs(Math.abs(e.getBlock().getLocation().getBlockZ())-z);
						if (value_x>island.getSpace()/2 || value_z>island.getSpace()/2) {
							e.setCancelled(true);
							return;
						} 
				 }
			 }
			 } else {
				 if (!e.getPlayer().hasPermission("celestialmc.admin")) {
					 e.setCancelled(true);
				 }
			 }
	 }
	 
	 @EventHandler
	 public void onInteract(PlayerInteractEvent e) {
		 if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
		 if (e.getPlayer().getLocation().getWorld().getName().equalsIgnoreCase("skyblock")) {
			 Player p = e.getPlayer();		 
			 CelestialPlayer cplayer = SkyblockManager.getManager().getCelestialPlayer(p);
			 SkyblockIsland island = SkyblockManager.getManager().getIslandByLocation(e.getClickedBlock().getLocation());
			 if (island!=null) {	
					if (!island.getOwner().getUUID().toString().equalsIgnoreCase(cplayer.getUUID().toString()) && !island.hasPermission(cplayer, PlayerPermissions.CAN_OPEN_INVENTORIES) && e.getClickedBlock().getState() instanceof InventoryHolder) {
						 e.setCancelled(true);
						 p.sendMessage(ChatColor.RED+"You do not have permission to do this.");		
				 } else {
					 int x = island.getSpawnPoint().getBlockX();
						int z = island.getSpawnPoint().getBlockZ();
						int value_x = Math.abs(Math.abs(e.getClickedBlock().getLocation().getBlockX())-x);
						int value_z = Math.abs(Math.abs(e.getClickedBlock().getLocation().getBlockZ())-z);
						if (value_x>island.getSpace()/2 || value_z>island.getSpace()/2) {
							e.setCancelled(true);
						} 
				 }
			 }
			 } else {
				 if (!e.getPlayer().hasPermission("celestialmc.admin")) {
					 e.setCancelled(true);
				 }
			 }
	 }
	 }
	 
	 
	 @EventHandler
	 public void onGenerator(BlockFormEvent e) {
		 if (e.getBlock().getLocation().getWorld().getName().equalsIgnoreCase("skyblock")) {
		 if (e.getBlock().getType().equals(Material.LAVA)) {
			 SkyblockIsland i = SkyblockManager.getManager().getIslandByLocation(e.getBlock().getLocation());
			 if (i!=null) {
				 RandomCollection<Material> items = new RandomCollection<Material>();
				 double level = i.getGenerationLevel();
				 double diamondper = level*0.25;
				 items.add(diamondper, Material.DIAMOND_ORE);
				 double emeraldper = level*0.5;
				 items.add(emeraldper, Material.EMERALD_ORE);
				 double goldper = level*0.75;
				 items.add(goldper, Material.GOLD_ORE);
				 double lapisper = level*1;
				 items.add(lapisper, Material.LAPIS_ORE);
				 double redstoneper = level*1.25;
				 items.add(redstoneper, Material.REDSTONE_ORE);
				 double ironper = level*1.5;
				 items.add(ironper, Material.IRON_ORE);
				 double coalper = level*1.75;
				 items.add(coalper, Material.COAL_ORE);
				 double suma = diamondper-emeraldper-goldper-lapisper-redstoneper-ironper-coalper;
				 double cobblestoneper = 100 - suma;
				 items.add(cobblestoneper, Material.COBBLESTONE);
				 
				 e.getNewState().setType(items.next());
				 e.getNewState().update();
			 }
		 } 
	 }
	 }
	 
	 
	 @EventHandler
	 public void onPlayerExp(PlayerExpChangeEvent e) {
		 CelestialPlayer cplayer = SkyblockManager.getManager().getCelestialPlayer(e.getPlayer());
		 if (cplayer.getRank().toLevel()>=3) {
			 if (cplayer.isExpBoosted()) {
				 int boost =  (int) Math.round(e.getAmount()*0.25);
				 if (boost<1) {
					 boost=1;
				 }
				 Bukkit.broadcastMessage("boost:" + boost);
				 Bukkit.broadcastMessage("def:" + e.getAmount());
				 e.setAmount(e.getAmount()+e.getAmount()+boost);
				 Bukkit.broadcastMessage("new:"+e.getAmount());
			 }
		 }
	 }
	 
	  @EventHandler
	    public void onPlantBreak(BlockBreakEvent blockBreakEvent) {
		  CelestialPlayer cplayer = SkyblockManager.getManager().getCelestialPlayer(blockBreakEvent.getPlayer());
		  
		  if (cplayer.isFarmerKey()) {
			  SkyblockManager.getManager().giveCrateKey(cplayer);
		  }
	        if(cplayer.isAutoSeedsEnabled())
	        {
	            //Get our block
	            Block block = blockBreakEvent.getBlock();
	            Material farm = blockBreakEvent.getBlock().getType();
	            Material removeMat = null;            
	            //If the block is a crop on a farm
	            if (farm == Material.WHEAT || farm == Material.BEETROOTS || farm == Material.CARROTS || farm == Material.POTATOES)
	            {

	                Location farmPos = block.getLocation(); //The position of the block in the world
	                String stringWorld = block.getWorld().getName(); //The name of the world
	                Player player = blockBreakEvent.getPlayer(); //The player breaking the block

	                //Switch to correctly set the materials we want to remove from a players inventory
	                switch (farm)
	                {
	                    case WHEAT:
	                        removeMat = Material.WHEAT_SEEDS; //The material to remove from the players inventory
	                        break;

	                    case BEETROOTS:
	                        removeMat = Material.BEETROOT_SEEDS; //The material to remove from the players inventory
	                        break;

	                    case CARROTS:
	                        removeMat = Material.CARROT; //The material to remove from the players inventory
	                        break;

	                    case POTATOES:
	                        removeMat = Material.POTATO; //The material to remove from the players inventory
	                        break;
					default:
						break;
	                }

	                //Schedules the seeds to be replanted the next tick
	                BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
	            	final Material mat = removeMat;
	                scheduler.scheduleSyncDelayedTask(Main.get(), new Runnable()
	                {
	                
	                    @Override
	                    public void run()
	                    {
	                    	
	                        //If the player has seeds and the blockbreakevent has not been cancelled
	                        if (player.getInventory().contains(mat) && !blockBreakEvent.isCancelled())
	                        {
	                            //Gets the block location from the world from the server and sets it to the crop
	                            //This just puts the crop down on a farm at state 0
	                            try
	                            {
	                                Bukkit.getServer().getWorld(stringWorld).getBlockAt(farmPos).setType(farm);
	                                //Remove an appropriate crop material from the player
	                                removeItem(player, mat);
	                            } catch (NullPointerException e)
	                            {
	                            }
	                        }
	                        //else if a player does not have any seeds in their inventory
	                        else if (!(player.getInventory().contains(mat)))
	                        {
	                            player.sendMessage("Not enough seeds in inventory!");
	                        }

	                    }
	                }, 5);
	            }
	        }
	    }
	  
	  public void removeItem(Player player, Material mat)
	    {
	        for(int i = 0; i < player.getInventory().getContents().length; i++)
	        {
	            ItemStack item = player.getInventory().getItem(i);
	            try
	            {
	                if (item.getType() != mat)
	                {
	                    continue;
	                }
	            }
	            catch (NullPointerException e)
	            {
	                continue;
	            }
	            int itemAmount = item.getAmount();
	            item.setAmount(itemAmount - 1);
	            break; //Added break because otherwise it takes too many seeds
	        }
	    }
}

