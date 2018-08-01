package ru.thematdev.cm.main;

import java.util.logging.Logger;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import ru.thematdev.cm.auto.AutoMessage;
import ru.thematdev.cm.auto.join.AMJoinListener;
import ru.thematdev.cm.command.ChatExecutor;
import ru.thematdev.cm.prefix.MessageEditor;

public class Main extends JavaPlugin{
	
	public Logger logger;
	public FileConfiguration config;
	
	public void onEnable() {
		logger = getServer().getLogger();
		logger.info("Cm by thematdev enabled!");
		
		config = getConfig();
		
		new AutoMessage(this);
		
		registerCommands();
		registerEvents();
	}
	
	public void onDisable() {
		logger.info("Cm by thematdev disabled!");
	}
	
	public void registerEvents() {
		
		getServer().getPluginManager().registerEvents(new ChatExecutor(this), this);
		getServer().getPluginManager().registerEvents(new MessageEditor(), this);
		getServer().getPluginManager().registerEvents(new AMJoinListener(), this);
		
	}
	public void registerCommands() {
		
		getCommand("chat").setExecutor(new ChatExecutor(this));
		
	}

}
