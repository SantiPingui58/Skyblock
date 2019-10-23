package me.santipingui58.celestialmc.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import me.santipingui58.celestialmc.gui.abilities.RankUpMenu;


public class RankUpCommand implements CommandExecutor {

	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, final String[] args) {
		if(!(sender instanceof Player)) {
			
			sender.sendMessage("Solo los jugadores pueden hacer esto!");
			return true;
			
		} else {
	
		if(cmd.getName().equalsIgnoreCase("rankup")) {
			Player p = (Player) sender;
			new RankUpMenu(p).o(p);
			}
		
			

}
		
		
		return false;
	}
	


	
}