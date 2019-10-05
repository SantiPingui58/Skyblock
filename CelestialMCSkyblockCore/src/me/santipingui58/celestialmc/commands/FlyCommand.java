package me.santipingui58.celestialmc.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.santipingui58.celestialmc.game.CelestialPlayer;
import me.santipingui58.celestialmc.game.Result;
import me.santipingui58.celestialmc.game.skyblock.SkyblockManager;



public class FlyCommand implements CommandExecutor {

	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, final String[] args) {
		if(!(sender instanceof Player)) {
			
			sender.sendMessage("Solo los jugadores pueden hacer esto!");
			return true;
			
		} else {
	
		if(cmd.getName().equalsIgnoreCase("fly")){
			 Player p = (Player) sender;
			 CelestialPlayer cplayer = SkyblockManager.getManager().getCelestialPlayer(p);
			 if (p.hasPermission("celestialmc.fly")) {
				 if (p.hasPermission("celestialmc.staff")) {
					 if (cplayer.isFlying()) {
						 cplayer.stopFly();			
						 cplayer.sendMessage("Fly desactivated", Result.ALLOW);
					 } else {
					 cplayer.fly();			
					 cplayer.sendMessage("Fly activated", Result.ALLOW);
					 }
				 } else if (p.getWorld().getName().equalsIgnoreCase("skyblock")) {
					 if (cplayer.isFlying()) {
					 cplayer.fly();	
					 cplayer.sendMessage("Fly activated", Result.ALLOW);
					 } else {
						 cplayer.stopFly();			
						 cplayer.sendMessage("Fly desactivated", Result.ALLOW);
					 }
				 } else {
					 cplayer.sendMessage("You do not have permission to do this.",Result.DENY);
				 }
			 } else {
				 cplayer.sendMessage("You do not have permission to do this.",Result.DENY);
			 }
		}
}
		
		
		return false;
	}
	


	
}