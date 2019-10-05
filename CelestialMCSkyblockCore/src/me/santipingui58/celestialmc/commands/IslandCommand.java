package me.santipingui58.celestialmc.commands;


import java.text.SimpleDateFormat;
import java.util.Date;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import me.santipingui58.celestialmc.Main;
import me.santipingui58.celestialmc.game.CelestialPlayer;
import me.santipingui58.celestialmc.game.DataManager;
import me.santipingui58.celestialmc.game.PlayerLocation;
import me.santipingui58.celestialmc.game.skyblock.PlayerPermissions;
import me.santipingui58.celestialmc.game.skyblock.SkyblockIsland;
import me.santipingui58.celestialmc.game.skyblock.SkyblockManager;
import me.santipingui58.celestialmc.gui.island.IslandMainMenu;
import me.santipingui58.celestialmc.gui.island.PlayerPermissionsMenu;
import me.santipingui58.celestialmc.utils.Utils;





public class IslandCommand implements CommandExecutor {

	
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, final String[] args) {
		if(!(sender instanceof Player)) {
			
			sender.sendMessage("Solo los jugadores pueden hacer esto!");
			return true;
			
		} else {
	
		if(cmd.getName().equalsIgnoreCase("island")){
			 Player p = (Player) sender;
			 CelestialPlayer cplayer = SkyblockManager.getManager().getCelestialPlayer(p);
			 if (args.length==0) {
				 if (cplayer.hasIsland()) {
				 new IslandMainMenu(p).o(p);
				 } else {
					 sendHelpCommands(p);
				 }
			 } else if (args[0].equalsIgnoreCase("create")) {
				 if (!cplayer.hasIsland()) {
				 SkyblockManager.getManager().generateNewIsland(cplayer);
				 p.sendMessage( Main.skyblock_prefix+ " §aIsland created.");
				 cplayer.setLocation(PlayerLocation.OWN_ISLAND);
				 } else {
					 p.sendMessage( Main.skyblock_prefix+ " §cYou already have an island!");
				 }
			 } else if (args[0].equalsIgnoreCase("home")) {
				 if (args.length==1) {
				 if (cplayer.hasIsland()) {
					
					 SkyblockManager.getManager().homeTeleport(cplayer,null);
					 cplayer.setLocation(PlayerLocation.OWN_ISLAND);
				 p.sendMessage( Main.skyblock_prefix+ " §aTeleporting to your home");
				 } else {
					 p.sendMessage( Main.skyblock_prefix+ " §cYou need to have an island to perfom this command.");
				 }
				 } else {
					 OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);
					 if (target.hasPlayedBefore() || target.isOnline()) {
					 CelestialPlayer ctarget = SkyblockManager.getManager().getCelestialPlayer(target.getUniqueId());
					 if (ctarget.hasIsland() && ctarget.getIsland().getMembers().contains(cplayer)) {
						 if (ctarget.getIsland().hasPermission(cplayer, PlayerPermissions.CAN_DO_HOME)) {
							 SkyblockManager.getManager().homeTeleport(cplayer, ctarget.getIsland());
							 p.sendMessage( Main.skyblock_prefix+ " §aTeleporting to §b" + target.getName() + "§a Skyblock Island.");
						 } else {
							 p.sendMessage( Main.skyblock_prefix+ " §cYou do not have permission to do this.");
						 }
					 } else {
						 p.sendMessage( Main.skyblock_prefix+ " §cYou do not have permission to do this.");
					 }
					 } else {
						 p.sendMessage( Main.skyblock_prefix+ " §cThe player " + target.getName() + " does not exist.");
					 }
				 }
			 } else if (args[0].equalsIgnoreCase("sethome")) {
				 if (cplayer.hasIsland()) {
					 if (cplayer.getLocation().equals(PlayerLocation.OWN_ISLAND) && cplayer.getPlayer().getWorld().getName().equalsIgnoreCase("skyblock")) {
						 if (p.isOnGround()) {
							 cplayer.getIsland().setHome(p.getLocation());
						 p.sendMessage( Main.skyblock_prefix+ " §aYou have changed the Home of your Skyblock Island succesfully!");
						 } else {
							 p.sendMessage( Main.skyblock_prefix+ " §cYou need to be on ground to perfom this command.");
						 }
					 } else {
						 p.sendMessage( Main.skyblock_prefix+ " §cYou need to be on your own Skyblock Island to perfom this command.");
					 }
				 } else {
					 p.sendMessage( Main.skyblock_prefix+ " §cYou need to have an island to perfom this command.");
				 }
			 } else if (args[0].equalsIgnoreCase("delete")) {
				 if (cplayer.hasIsland()) {
					 if (cplayer.getLocation().equals(PlayerLocation.OWN_ISLAND) && cplayer.getPlayer().getWorld().getName().equalsIgnoreCase("skyblock")) {
						 Location spawn = Utils.getUtils().getLoc(Main.config.getConfig().getString("spawn"), true,true); 
						 cplayer.getPlayer().teleport(spawn);
					 } 
					 DataManager.getManager().deleteIsland(cplayer.getIsland());
					 p.sendMessage( Main.skyblock_prefix+ " §cYour Skyblock Island has been deleted.");
				 } else {
					 p.sendMessage( Main.skyblock_prefix+ " §cYou need to have an island to perfom this command.");
				 }
			 } else if (args[0].equalsIgnoreCase("setwarp")) {
				 if (cplayer.hasIsland()) {
					 if (cplayer.getLocation().equals(PlayerLocation.OWN_ISLAND) && cplayer.getPlayer().getWorld().getName().equalsIgnoreCase("skyblock")) {
						 if (p.isOnGround()) {
							 cplayer.getIsland().setWarp(p.getLocation());
						 p.sendMessage( Main.skyblock_prefix+ " §aYou have changed the Warp of your Skyblock Island succesfully!");
						 } else {
							 p.sendMessage( Main.skyblock_prefix+ " §cYou need to be on ground to perfom this command.");
						 }
					 } else {
						 p.sendMessage( Main.skyblock_prefix+ " §cYou need to be on your own Skyblock Island to perfom this command.");
					 }
				 } else {
					 p.sendMessage( Main.skyblock_prefix+ " §cYou need to have an island to perfom this command.");
				 }
			 } else if (args[0].equalsIgnoreCase("warp")) {
				 OfflinePlayer target = null;
				 if (args.length==1) {
					 target = p;
				 } else {
					 target = Bukkit.getOfflinePlayer(args[1]);
				 }
				 
					 if (target.hasPlayedBefore()) {
						 CelestialPlayer ctarget = null;
							 ctarget = SkyblockManager.getManager().getCelestialPlayer(target.getUniqueId());					 
						 
						 if (ctarget.hasIsland()) {
							 SkyblockIsland island = ctarget.getIsland();
							 if (island.getOwner().getUUID().toString().equalsIgnoreCase(cplayer.getUUID().toString())) {
								SkyblockManager.getManager().warpTeleport(ctarget,cplayer);
								 p.sendMessage( Main.skyblock_prefix+ " §aTeleporting to §b" + target.getName() + "§a Skyblock Island.");
							 } else {
								 if (island.isWarpEnabled()) {
									 SkyblockManager.getManager().warpTeleport(ctarget, cplayer);
									 p.sendMessage( Main.skyblock_prefix+ " §aTeleporting to §b" + target.getName() + "§a Skyblock Island.");
								 } else {
									 p.sendMessage( Main.skyblock_prefix+ " §cThe player §b" + target.getName() + " §cdoes not have the Skyblock Island Warp enabled.");
								 }
							 }
							
						 } else {
							 p.sendMessage( Main.skyblock_prefix+ " §cThe player " + target.getName() + " does not have an Skyblock Island.");
						 }
					 } else {
						 p.sendMessage( Main.skyblock_prefix+ " §cThe player " + target.getName() + " does not exist.");
					 }
				 
			 } else if (args[0].equalsIgnoreCase("enablewarp")) {
				 if (cplayer.hasIsland()) {
					 cplayer.getIsland().enableWarp();
					 p.sendMessage( Main.skyblock_prefix+ " §aYou have enabled the Warp of your Skyblock Island!");
				 } else {
					 p.sendMessage( Main.skyblock_prefix+ " §cYou need to have an island to perfom this command.");
				 }
			 } else if (args[0].equalsIgnoreCase("disablewarp")) {
				 if (cplayer.hasIsland()) {
					 cplayer.getIsland().disableWarp();
					 p.sendMessage( Main.skyblock_prefix+ " §aYou have disabled the Warp of your Skyblock Island!");
				 } else {
					 p.sendMessage( Main.skyblock_prefix+ " §cYou need to have an island to perfom this command.");
				 }
			 }else if (args[0].equalsIgnoreCase("perms")) {
				 if (cplayer.hasIsland()) {
				 if (args.length==2) {
					 OfflinePlayer off = Bukkit.getOfflinePlayer(args[1]);
					 if (off.hasPlayedBefore() || off.isOnline()) {
						 if (!off.getUniqueId().toString().equalsIgnoreCase(p.getUniqueId().toString())) {
						 CelestialPlayer ctarget = SkyblockManager.getManager().getCelestialPlayer(off.getUniqueId());
						 if (cplayer.getIsland().getMembers().contains(ctarget)) {
						 new PlayerPermissionsMenu(p,ctarget).o(p);
						 } else {
							 p.sendMessage( Main.skyblock_prefix+ " §cThe player " + off.getName() + " is not a member of your Skyblock Island.");
						 }
						 } else {
							 p.sendMessage( Main.skyblock_prefix+ " §cYou cannot edit your own permissions...");
						 }
					 } else {
						 p.sendMessage( Main.skyblock_prefix+ " §cThe player " + off.getName() + " does not exist.");
					 }
				 } else {
					 sendHelpCommands(p);
				 }
				 } else {
					 p.sendMessage( Main.skyblock_prefix+ " §cYou need to have an island to perfom this command.");
				 }
			 } else if (args[0].equalsIgnoreCase("help")) {
				 sendHelpCommands(p);
			 } else if (args[0].equalsIgnoreCase("add"))  {
				 if (cplayer.hasIsland()) {
				 if (args.length==2) {
					 Player target = Bukkit.getPlayer(args[1]);
					 if (Bukkit.getOnlinePlayers().contains(target)) {
						 if (target!=p) {
						 CelestialPlayer ctarget = SkyblockManager.getManager().getCelestialPlayer(target);
						 if (!cplayer.getIsland().getMembers().contains(ctarget)) {
							 if (cplayer.getIsland().getMaxPlayers()>cplayer.getIsland().getMembers().size()+1) {
							 if (!cplayer.getIsland().getInvitations().contains(ctarget)) {
								 cplayer.getIsland().getInvitations().add(ctarget);
								 p.sendMessage(Main.skyblock_prefix + " §aYou have invited §b" +args[1] + " §ato your Skyblock Island succesfully!");
								 
								 target.sendMessage(Main.skyblock_prefix + " §aThe player " + p.getName() + "§a has invited you to be a member of his Skyblock Island. ");
								 target.sendMessage(Main.skyblock_prefix +" §aDo §6/is accept " + p.getName() +" §ato join it. (The request will expire in 5 minutes.)");
								 new BukkitRunnable() {
									 public void run() {
										 cplayer.getIsland().getInvitations().remove(ctarget);
									 }
								 }.runTaskLater(Main.get(), 20*60*5L);
							 } else {
								 p.sendMessage(Main.skyblock_prefix +" §cYou have already invited this player to your Skyblock Island.");
							 }
						 } else {
							 p.sendMessage( Main.skyblock_prefix+ " §cYou have reached the limit of memebers allowed on your Skyblock Island. Upgrade at the §5§lIsland Upgrades§c.");
						 }
						 } else {
							 p.sendMessage( Main.skyblock_prefix+ " §cThe player " + args[1] + " is already a member of your Skyblock Island.");
						 }
					 } else {
						 p.sendMessage( Main.skyblock_prefix+ " §cYou cannot invite yourself to your own Skyblock Island...");
					 }
					 } else {
						 p.sendMessage( Main.skyblock_prefix+ " §cThe player " + args[1] + " does not exist or is not online.");
					 }
				 } else {
					 p.sendMessage( Main.skyblock_prefix+ " §cYou need to have an island to perfom this command.");
				 }
				 } else {
					 sendHelpCommands(p);
				 }
			 }else if (args[0].equalsIgnoreCase("accept")) {
				 if (args.length==2) {
					 Player target = Bukkit.getPlayer(args[1]);
					 if (Bukkit.getOnlinePlayers().contains(target)) {
						  CelestialPlayer ctarget = SkyblockManager.getManager().getCelestialPlayer(target);
						  if (ctarget.hasIsland() && ctarget.getIsland().getInvitations().contains(cplayer)) {
							  ctarget.getIsland().getInvitations().remove(cplayer);
							  ctarget.getIsland().getMembers().add(cplayer);
							  p.sendMessage(Main.skyblock_prefix +" §aYou have succesfully joined §b" + target.getName() + "§a's Skyblock Island!");
							  target.sendMessage(Main.skyblock_prefix + " §b" + p.getName() + " §ahas accepted your request!");
						  } else {
							  p.sendMessage( Main.skyblock_prefix+ " §cYou do not have an invitation from the player §b" + args[1]+"§c.");
						  }
					 } else {
						 p.sendMessage( Main.skyblock_prefix+ " §cThe player " + args[1] + " does not exist or is not online.");
					 }
				 } else {
					 sendHelpCommands(p);
				 }
			 } else if(args[0].equalsIgnoreCase("leave")) {
				 if (args.length==2) {
					if (cplayer.getIslandsWhereIsMember().size()>0) {
						OfflinePlayer off = Bukkit.getOfflinePlayer(args[1]);
						if (off.hasPlayedBefore() || off.isOnline()) {
							CelestialPlayer target = SkyblockManager.getManager().getCelestialPlayer(off.getUniqueId());
							if (target.hasIsland()) {
								if (target.getIsland().getMembers().contains(cplayer)) {
									SkyblockManager.getManager().removeFromIsland(cplayer, target.getIsland(), false);
									p.sendMessage( Main.skyblock_prefix+ " §cYou are not a member of §b" + off.getName() + "§c's Skyblock Island.");
								}
							} else {
								p.sendMessage( Main.skyblock_prefix+ " §cThe player " + args[1] + " does not have an Skyblock Island.");
							}
						} else {
							p.sendMessage( Main.skyblock_prefix+ " §cThe player " + args[1] + " does not exist.");
						}
					} else {
						 p.sendMessage( Main.skyblock_prefix+ " §cYou need to be a member of a Skyblock Island to perfom this command.");
					}
				 } else {
					 sendHelpCommands(p);
				 }
				 
				 
				 
			 }   else if (args[0].equalsIgnoreCase("gui")) {
				 if (cplayer.hasIsland()) {
					 new IslandMainMenu(p).o(p);
				 } else {
					 p.sendMessage( Main.skyblock_prefix+ " §cYou need to have an island to perfom this command.");
				 }
			 } else if (args[0].equalsIgnoreCase("deposit")) {
				 if (args.length==3) {
					 OfflinePlayer off = Bukkit.getOfflinePlayer(args[1]);
						if (off.hasPlayedBefore() || off.isOnline()) {
							CelestialPlayer target = SkyblockManager.getManager().getCelestialPlayer(off.getUniqueId());
							if (target.hasIsland()) {
									int amount = 0;
									try {
										amount = Integer.parseInt(args[2]);
									} catch (Exception e) {
										p.sendMessage(Main.skyblock_prefix +" §b" + args[2] + " §cis not a valid number.");
										return true;
									}
									if (cplayer.hasMoney(amount)) {
										cplayer.takeMoney(amount);
										Date now = new Date();
										SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy");	
										target.getIsland().depositMoney(amount, p.getName(), format.format(now));
										p.sendMessage(Main.skyblock_prefix +" §aYou have succesfully deposited §6$"+ amount + "§a to §b" +target.getOfflinePlayer().getName() + " §a's Skyblock Island Bank!");
									} else {
										p.sendMessage(Main.skyblock_prefix +" §cYou do not have enough money to do this.");
									}
								
							} else {
								p.sendMessage( Main.skyblock_prefix+ " §cThe player " + args[1] + " does not have an Skyblock Island.");
							}
						} else {
							p.sendMessage( Main.skyblock_prefix+ " §cThe player " + args[1] + " does not exist.");
						}
				 } else if (args.length==2) {
					 SkyblockIsland island = SkyblockManager.getManager().getIslandByLocation(p.getLocation());
					 if (island!=null) {
						int amount = 0;
						try {
							amount = Integer.parseInt(args[1]);
						} catch (Exception e) {
							p.sendMessage(Main.skyblock_prefix +" §b" + args[1] + " §cis not a valid number.");
							return true;
						}
						if (cplayer.hasMoney(amount)) {
							cplayer.takeMoney(amount);
							Date now = new Date();
							SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy");	
							island.depositMoney(amount, p.getName(), format.format(now));
							p.sendMessage(Main.skyblock_prefix +" §aYou have succesfully deposited §6$"+ amount + "§a to §b" +island.getOwner().getOfflinePlayer().getName()+ " §a's Skyblock Island Bank!");
						} else {
							p.sendMessage(Main.skyblock_prefix +" §cYou do not have enough money to do this.");
						}
					 } else {
						 p.sendMessage(Main.skyblock_prefix +" §cYou are not in a Skyblock Island.");
					 }
				 } else {
					 sendHelpCommands(p);
				 }
			 } else if (args[0].equalsIgnoreCase("withdraw")) {
				 if (args.length==2) {
				 if (cplayer.hasIsland()) {
					 int amount = 0;
						try {
							amount = Integer.parseInt(args[1]);
						} catch (Exception e) {
							p.sendMessage(Main.skyblock_prefix +" §b" + args[1] + " §cis not a valid number.");
							return true;
						}
						
						if (cplayer.getIsland().hasMoney(amount)) {
							cplayer.addMoney(amount);
							Date now = new Date();
							SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy");						
							cplayer.getIsland().withdrawMoney(amount, p.getName(), format.format(now));
							p.sendMessage(Main.skyblock_prefix +" §aYou have succesfully withdrawn §6$"+ amount + "§a from your Skyblock Island Bank!");
						} else {
							p.sendMessage(Main.skyblock_prefix +" §cYour Skyblock Island Bank  does not have enough money to do this.");
						}
				 } else {
					 p.sendMessage( Main.skyblock_prefix+ " §cYou need to have an island to perfom this command.");
				 }
				 } else {
					 sendHelpCommands(p);
				 }
			 }else {
				sendHelpCommands(p);
			 }
			}
			

}
		
		
		return false;
	}
	
	private void sendHelpCommands(Player p) {
		 p.sendMessage(Main.skyblock_prefix+" §6List of commands:");
		 p.sendMessage(Main.skyblock_prefix+" §6/island help §f- §7shows this list of commands.");
		 p.sendMessage(Main.skyblock_prefix+" §6/island gui §f- §7opens the island gui menu.");
		 p.sendMessage(Main.skyblock_prefix+" §6/island create §f- §7creates a new Skyblock Island.");
		 p.sendMessage(Main.skyblock_prefix+" §6/island add <Player> §f- §7add the selected player to your Skyblock Island.");
		 p.sendMessage(Main.skyblock_prefix+" §6/island accept <Player> §f- §7Accept the request of the selected player.");
		 p.sendMessage(Main.skyblock_prefix+" §6/island leave <Player> §f- §7leave the Skyblock Island of the selected player.");
		 p.sendMessage(Main.skyblock_prefix+" §6/island deposit <Player> <Amount> §f- §7deposite the desired amount to the player Skyblock Island Bank.");
		 p.sendMessage(Main.skyblock_prefix+" §6/island deposit <Amount> §f- §7deposite the desired amount to the Skyblock Island Bank you are at.");
		 p.sendMessage(Main.skyblock_prefix+" §6/island withdraw <Amount> §f- §7withdraw the desired amount from your Skyblock Island Bank.");
		 p.sendMessage(Main.skyblock_prefix+" §6/island home §f- §7teleports you to your Skyblock Island home.");
		 p.sendMessage(Main.skyblock_prefix+" §6/island sethome §f- §7change the home of your Skyblock Island.");
		 p.sendMessage(Main.skyblock_prefix+" §6/island delete §f- §7deletes your Skyblock Island (cannot be undone).");
		 p.sendMessage(Main.skyblock_prefix+" §6/island enablewarp §f- §7let players teleport to your warp to visit your Skyblock Island.");
		 p.sendMessage(Main.skyblock_prefix+" §6/island disablewarp §f- §7don´t let players teleport to your warp to visit your Skyblock Island.");
		 p.sendMessage(Main.skyblock_prefix+" §6/island perms <Player> §f- §7opens a GUI to edit Skyblock Island permissions of the Player.");
		 p.sendMessage(Main.skyblock_prefix+" §6/island worth §f- §7opens a GUI to show your Skyblock Island worth.");
		 p.sendMessage(Main.skyblock_prefix+" §6/island top §f- §7shows top Skyblock Islands.");
		 p.sendMessage(Main.skyblock_prefix+" §6/island setwarp §f- §7Set the warp of your Skyblock Island."); 
		 p.sendMessage(Main.skyblock_prefix+" §6/island warp <Player> §f- §7Teleports you to the warp of the selected player.");
		 
	}

	
}