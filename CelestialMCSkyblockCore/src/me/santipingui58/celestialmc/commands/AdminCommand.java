package me.santipingui58.celestialmc.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;



public class AdminCommand implements CommandExecutor {

	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, final String[] args) {
		if(!(sender instanceof Player)) {
			
			sender.sendMessage("Solo los jugadores pueden hacer esto!");
			return true;
			
		} else {
	
		if(cmd.getName().equalsIgnoreCase("admin")){
			 Player p = (Player) sender;
			 if (p.hasPermission("celestialmc.admin")) {
			if (args.length>0) {
				if (args[0].equalsIgnoreCase("tp")) {
					if (args[1].equalsIgnoreCase("dragonspawn")) {
						p.teleport(Bukkit.getWorld("dragonspawn").getSpawnLocation());
					}
				} 
			}
			}
		}
}
		
		
		return false;
	}
	


	
}