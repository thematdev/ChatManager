package ru.thematdev.cm.main;


import java.io.File;

import org.bukkit.plugin.java.JavaPlugin;

import ru.thematdev.cm.auto.AutoMessage;
import ru.thematdev.cm.auto.join.AMJoinListener;
import ru.thematdev.cm.command.ChatExecutor;
import ru.thematdev.cm.command.send.SendCommandExecutor;
import ru.thematdev.cm.config.BukkitConfigurationYaml;
import ru.thematdev.cm.prefix.MessageEditor;

public class Main extends JavaPlugin{
	
	private static Main instance;
	
	public static Main instance() {
		return instance;
	}
	
	public void onEnable() {
		instance = Main.this;
		
		getLogger().info("ChatManager by thematdev enabled!");
		
		registerConfig();
		registerCommands();
		registerEvents();
		
		new AutoMessage(new BukkitConfigurationYaml());
	}
	
	public void onDisable() {
		getLogger().info("ChatManager by thematdev disabled!");
	}
	
	public void registerEvents() {
		
		getServer().getPluginManager().registerEvents(new ChatExecutor(this), this);
		getServer().getPluginManager().registerEvents(new MessageEditor(), this);
		getServer().getPluginManager().registerEvents(new AMJoinListener(), this);
		
	}
	public void registerCommands() {
		
		getCommand("chat").setExecutor(new ChatExecutor(this));
		getCommand("sendnfc").setExecutor(new SendCommandExecutor());
		
	}
	
	public void registerConfig() {
		
		File configFile = new File(getDataFolder(), "config.yml");
		if (!configFile.exists()) {
			getLogger().warning("Config file was not found!");
			getLogger().warning("Loading default config...");
			saveDefaultConfig();
			reloadConfig();
			getLogger().info("Default config was loaded successful!");
		}
	}

}
