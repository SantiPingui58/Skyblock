package me.santipingui58.celestialmc.listener;



import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.RegisteredServiceProvider;

import me.lucko.luckperms.api.Contexts;
import me.lucko.luckperms.api.LuckPermsApi;
import me.lucko.luckperms.api.User;
import me.lucko.luckperms.api.caching.MetaData;







public class PlayerChatEvent implements Listener {
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();

		 LuckPermsApi api = null;
		 RegisteredServiceProvider<LuckPermsApi> provider = Bukkit.getServicesManager().getRegistration(LuckPermsApi.class);
		 if (provider != null) {
		      api = provider.getProvider();	     
		 }	 
		 User user = api.getUser(p.getUniqueId());
		 MetaData metaData = user.getCachedData().getMetaData(Contexts.allowAll());
		 String prefix = "";
		 if (metaData.getPrefix()!=null) { 
		 prefix = ChatColor.translateAlternateColorCodes('&', metaData.getPrefix());
		 }
		String msg = e.getMessage();
		 msg = e.getMessage().replaceAll("%", "%%");
		if (p.hasPermission("celestialmc.staff")) {
			 msg = ChatColor.translateAlternateColorCodes('&', msg);
			e.setFormat(prefix +p.getName() +"§8: §b"+msg );
		} else if (p.hasPermission("celestialmc.donator")) {
			if (p.hasPermission("celestialmc.chatcolor")) {
			 msg = ChatColor.translateAlternateColorCodes('&', msg);
			}
			e.setFormat(prefix +p.getName() +"§8: §f"+msg);
		} else {
			e.setFormat(prefix +" "+p.getName() +"§8: §7"+ msg );
		}
		


	}
	
	

}
