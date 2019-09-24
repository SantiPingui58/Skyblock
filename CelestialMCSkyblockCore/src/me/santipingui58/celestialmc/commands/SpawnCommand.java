package me.santipingui58.celestialmc.commands;


import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.santipingui58.celestialmc.Main;
import me.santipingui58.celestialmc.game.CelestialPlayer;
import me.santipingui58.celestialmc.game.PlayerLocation;
import me.santipingui58.celestialmc.game.skyblock.SkyblockManager;
import me.santipingui58.celestialmc.utils.Utils;






public class SpawnCommand implements CommandExecutor {

	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, final String[] args) {
		if(!(sender instanceof Player)) {
			
			sender.sendMessage("Solo los jugadores pueden hacer esto!");
			return true;
			
		} else {
	
		if(cmd.getName().equalsIgnoreCase("spawn")){
			 Player p = (Player) sender;
			 if (args.length>0) {
				 if (args[0].equalsIgnoreCase("setspawn")) {
					 if (p.hasPermission("celestialmc.admin")) {
						 Main.config.getConfig().set("spawn", Utils.setLoc(p.getLocation(), true));
						 p.sendMessage(Main.prefix+ " §aSpawn location was set.");
						 return true;
					 }
				 }			 
			 }
			
			 if (Main.config.getConfig().contains("spawn")) {
				 Location spawn = Utils.getLoc(Main.config.getConfig().getString("spawn"), true,true);
				 CelestialPlayer cplayer = SkyblockManager.getManager().getCelestialPlayer(p);
				 cplayer.setLocation(PlayerLocation.SPAWN);
				 p.teleport(spawn);
				 
			 }
			}
			

}
		
		
		return false;
	}
	


	
}