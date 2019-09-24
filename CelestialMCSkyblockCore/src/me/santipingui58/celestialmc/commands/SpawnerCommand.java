package me.santipingui58.celestialmc.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.santipingui58.celestialmc.game.CelestialPlayer;
import me.santipingui58.celestialmc.game.skyblock.SkyblockManager;
import me.santipingui58.celestialmc.game.spawner.SpawnerManager;
import me.santipingui58.celestialmc.game.spawner.SpawnerType;







public class SpawnerCommand implements CommandExecutor {

	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, final String[] args) {
		if(!(sender instanceof Player)) {
			
			sender.sendMessage("Solo los jugadores pueden hacer esto!");
			return true;
			
		} else {
	
		if(cmd.getName().equalsIgnoreCase("spawner")) {
			 Player p = (Player) sender;
			 CelestialPlayer cplayer = SkyblockManager.getManager().getCelestialPlayer(p);
			 if (args.length==0) {
			SpawnerManager.getManager().createSpawner(cplayer,SpawnerType.COW);
			 } else if (args.length==1) {
				 SpawnerManager.getManager().createSpawner(cplayer,SpawnerType.PIG);
			 } else if (args.length==2) {
				 SpawnerManager.getManager().createSpawner(cplayer,SpawnerType.WITHER_SKELETON);
			 } else if (args.length==3) {
				 SpawnerManager.getManager().createSpawner(cplayer,SpawnerType.CREEPER);
			 }
			}
			

}
		
		
		return false;
	}
	


	
}