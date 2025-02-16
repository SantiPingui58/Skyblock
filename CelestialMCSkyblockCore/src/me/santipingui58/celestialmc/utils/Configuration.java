package me.santipingui58.celestialmc.utils;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import me.santipingui58.celestialmc.Main;


public class Configuration {
	
	private File file;
	private FileConfiguration fileConfig;
	
	
	/** Creates a new config at the path, with the fileName, and uses the Plugin */
	public Configuration(String fileName, Plugin plugin) {
		if (!fileName.contains(".yml")) {
			fileName = fileName + ".yml";
		}
		file = new File(Main.get().getDataFolder(), fileName);
		if(!file.exists()) {
			 file.getParentFile().mkdirs();
	            Main.get().saveResource(fileName, false);
		} 
		
		fileConfig = new YamlConfiguration();
		
		try {
			fileConfig.load(file);
		} catch (IOException | InvalidConfigurationException e) {
			e.printStackTrace();
		}
		
		if (!file.exists()) {
			
			try {
				fileConfig.save(file);
			} catch (IOException exception) {
				exception.printStackTrace();
			}
		}
	}
	
	/** Get the Configuration section */
	public FileConfiguration getConfig() {
		return fileConfig;
	}
	
	/** Save the config */
	public void saveConfig() {
		try {
			fileConfig.save(file);
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}
	
	/** Set a location in the config */
	public void setLocation(String path, Location location) {
		fileConfig.set(path + ".World", location.getWorld().getName());
		fileConfig.set(path + ".X", location.getX());
		fileConfig.set(path + ".Y", location.getY());
		fileConfig.set(path + ".Z", location.getZ());
		fileConfig.set(path + ".Pitch", location.getPitch());
		fileConfig.set(path + ".Yaw", location.getYaw());
		saveConfig();
	}
	
	/** Get a location in the config */
	public Location getLocation(String path) {
		if (fileConfig.getString(path + ".World") == null) {
			return null;
		}
		Location location = new Location(Bukkit.getWorld(fileConfig.getString(path + ".World")), fileConfig.getDouble(path + ".X"), fileConfig.getDouble(path + ".Y"), fileConfig.getDouble(path + ".Z"), (float) fileConfig.getDouble(path + ".Yaw"), (float) fileConfig.getDouble(path + ".Pitch"));
		return location;
	}
	

}