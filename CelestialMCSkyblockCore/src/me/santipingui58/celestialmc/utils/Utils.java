package me.santipingui58.celestialmc.utils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.RegisteredServiceProvider;

import me.lucko.luckperms.api.LuckPermsApi;
import me.lucko.luckperms.api.Node;
import me.lucko.luckperms.api.User;
import me.santipingui58.celestialmc.game.CelestialPlayer;
import me.santipingui58.celestialmc.game.skyblock.SkyblockIsland;


public class Utils {
	
	private static Utils manager;	
	 public static Utils getUtils() {
	        if (manager == null)
	        	manager = new Utils();
	        return manager;
	    }
	  public String setLoc(Location loc, boolean pitch)
	  {
	    if (pitch) {
	      return loc.getWorld().getName() + "," + loc.getBlockX() + "," + loc.getBlockY() + "," + loc.getBlockZ() + "," + loc.getYaw() + "," + loc.getPitch();
	    }
	    return loc.getWorld().getName() + "," + loc.getBlockX() + "," + loc.getBlockY() + "," + loc.getBlockZ();
	  }
	  
	  public List<String> setLocs(List<Location> locs) {
		  List<String> list = new ArrayList<String>();
			  for (Location loc : locs) {
		      list.add(loc.getWorld().getName() + "," + loc.getBlockX() + "," + loc.getBlockY() + "," + loc.getBlockZ());
		    }
			  return list;
	  }

	  public Location getLoc(String path, boolean pitch,boolean add)
	  {
	    Location loc = null;
	    if (!pitch) {
	      String[] locs = path.split(",");
	      loc = new Location(Bukkit.getWorld(locs[0]), Integer.parseInt(locs[1]), Integer.parseInt(locs[2]), Integer.parseInt(locs[3]));
	      if (add) {
	      loc.add(0.5D, 0.0D, 0.5D);
	      }
	      return loc;
	    }
	    String[] locs = path.split(",");
	    loc = new Location(Bukkit.getWorld(locs[0]), Integer.parseInt(locs[1]), Integer.parseInt(locs[2]), Integer.parseInt(locs[3]), Float.valueOf(locs[4]).floatValue(), Float.valueOf(locs[5]).floatValue());
	    if (add) {
	    loc.add(0.5D, 0.0D, 0.5D);
	    }
	    return loc;
	  }

	  public Location getLoc(String path)
	  {
	    String[] locs = path.split(",");

	    Location loc = new Location(Bukkit.getWorld(locs[0]), Integer.parseInt(locs[1]), Integer.parseInt(locs[2]), Integer.parseInt(locs[3]));
	    return loc;
	  }
	  
	  
	  public void debug(String s) {
		  Player p = Bukkit.getPlayer("SantiPingui58");
		  if (Bukkit.getOnlinePlayers().contains(p))
		  p.sendMessage(s);
	  }
	  
	  
	  
	  public Location getCenter(Location loc) {
		    return new Location(loc.getWorld(),
		        getRelativeCoord(loc.getBlockX()),
		        getRelativeCoord(loc.getBlockY()),
		        getRelativeCoord(loc.getBlockZ()));
		}
		 
		private double getRelativeCoord(int i) {
		    double d = i;
		    d = d < 0 ? d - .5 : d + .5;
		    return d;
		}
	  
		
		  @SuppressWarnings("deprecation")
		public boolean isSafeLocation(Location location) {
		        Block feet = location.getBlock();
		        if (!feet.getType().isTransparent() && !feet.getLocation().add(0, 1, 0).getBlock().getType().isTransparent()) {
		            return false; // not transparent (will suffocate)
		        }
		        Block head = feet.getRelative(BlockFace.UP);
		        if (!head.getType().isTransparent()) {
		            return false; // not transparent (will suffocate)
		        }
		        Block ground = feet.getRelative(BlockFace.DOWN);
		        if (!ground.getType().isSolid()) {
		            return false; // not solid
		        }
		        
		        if (location.getBlock().getType().equals(Material.LAVA)) {
		        	return false;
		        }
		        return true;
		    }
		  
			
		  public int getEmptySlots(Inventory inventory) {
		        ItemStack[] cont = inventory.getContents();
		        int i = 0;
		        for (ItemStack item : cont)
		          if (item != null && item.getType() != Material.AIR) {
		            i++;
		          }
		        return 36 - i;
		    }
		 
		  
		  public boolean hasEmptySpace(Inventory inventory,ItemStack item) {
			  HashMap<Integer,ItemStack> map = inventory.addItem(item);
			  if (map.isEmpty()) {
				  inventory.removeItem(item);
				  return true;
			  }
			  inventory.removeItem(item);
			  return false;
		  }
		  
		  
			public TreeMap<SkyblockIsland, Integer> sortMapByValue(TreeMap<SkyblockIsland, Integer> map2){
				Comparator<SkyblockIsland> comparator = new ValueComparator(map2);
				TreeMap<SkyblockIsland, Integer> result = new TreeMap<SkyblockIsland, Integer>(comparator);
				result.putAll(map2);
				return result;
			}
			
			
			public void addPermission(CelestialPlayer cplayer,String permission) {
				LuckPermsApi api = null;
				 RegisteredServiceProvider<LuckPermsApi> provider = Bukkit.getServicesManager().getRegistration(LuckPermsApi.class);
				 if (provider != null) {
				      api = provider.getProvider();
				     
				 }			 
				 User user = api.getUser(cplayer.getUUID());
				 Node node = api.getNodeFactory().newBuilder(permission).build();
				 user.setPermission(node);
				 
				
				 
			}
			
			public void removePermission(CelestialPlayer cplayer,String permission) {
				LuckPermsApi api = null;
				 RegisteredServiceProvider<LuckPermsApi> provider = Bukkit.getServicesManager().getRegistration(LuckPermsApi.class);
				 if (provider != null) {
				      api = provider.getProvider();
				     
				 }			 
				 User user = api.getUser(cplayer.getUUID());		
				 Node node = null;
				 for (Node n : user.getAllNodes()) {
					 if (n.getPermission().equalsIgnoreCase(permission)) {
						 node = n;
						 break;
					 }
				 }
				 
				 user.unsetPermission(node);
			}
			
			public ItemStack getItemByMaterial(Player p, Material material) {
		        for (ItemStack item : p.getInventory()) {
		        	if (item.getType().equals(material)) {
		        		return item;
		        	}
		        }
		        return null;
		    }
}

