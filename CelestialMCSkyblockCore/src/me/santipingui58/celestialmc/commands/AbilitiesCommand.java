package me.santipingui58.celestialmc.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import me.santipingui58.celestialmc.gui.abilities.AbilitiesMenu;


public class AbilitiesCommand implements CommandExecutor {

	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, final String[] args) {
		if(!(sender instanceof Player)) {
			
			sender.sendMessage("Solo los jugadores pueden hacer esto!");
			return true;
			
		} else {
	
		if(cmd.getName().equalsIgnoreCase("abilities")) {
			Player p = (Player) sender;		
			new AbilitiesMenu(p).o(p);
			}
		
			

}
		
		
		return false;
	}
	


	
}