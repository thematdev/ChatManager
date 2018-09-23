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
	
	private static Main instance;
	
	public static Main instance() {
		return instance;
	}
	
	public void onEnable() {
		instance = Main.this;
		
		logger = getServer().getLogger();
		logger.info("Cm by thematdev enabled!");
		
		config = getConfig();
		
		System.out.println(config.getStringList("auto.notifications.chat.main.messages"));
		
		new AutoMessage();
		
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
	
	public FileConfiguration getConfigFile() {
		return config;
	}

}
