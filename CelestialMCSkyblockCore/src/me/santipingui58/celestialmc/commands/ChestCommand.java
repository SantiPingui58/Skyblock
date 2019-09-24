package me.santipingui58.celestialmc.commands;


import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.santipingui58.celestialmc.game.CelestialPlayer;
import me.santipingui58.celestialmc.game.chest.ChestManager;
import me.santipingui58.celestialmc.game.skyblock.SkyblockManager;







public class ChestCommand implements CommandExecutor {

	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, final String[] args) {
		if(!(sender instanceof Player)) {
			
			sender.sendMessage("Solo los jugadores pueden hacer esto!");
			return true;
			
		} else {
	
		if(cmd.getName().equalsIgnoreCase("chest")){
			if (args.length==1) {
			 Player p = (Player) sender;
			 
			 CelestialPlayer cplayer = SkyblockManager.getManager().getCelestialPlayer(p);
			 if (args[0].equalsIgnoreCase("autoblock")) {
			ChestManager.getManager().createAutoBlockChest(cplayer);
			p.sendMessage("Gave you an auto block chest");
			 } else if (args[0].equalsIgnoreCase("autosell")) {
				 ChestManager.getManager().createAutosSellChest(cplayer);;
					p.sendMessage("Gave you an auto sell chest");
			 }
			}
		}
			

}
		
		
		return false;
	}
	


	
}