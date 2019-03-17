package ru.thematdev.cm.config;

import org.bukkit.configuration.file.FileConfiguration;

import ru.thematdev.cm.auto.notification.AdvancementNotification;
import ru.thematdev.cm.auto.notification.ChatNotification;
import ru.thematdev.cm.main.Main;

public class Configuration {
	
	public FileConfiguration config;
	private String notificationSection;
	private String chatSection;
	private String advSection;
	
	private Main plugin;
	
	public Configuration() {
		
		this.plugin = Main.instance();
		
		this.config = plugin.getConfigFile();
		
		this.notificationSection = "auto.notifications";
		this.chatSection = notificationSection + ".chat";
		this.advSection = notificationSection + ".advancement";
		
	}

	public FileConfiguration getConfig() {
		return config;
	}

	public void setConfig(FileConfiguration config) {
		this.config = config;
	}

	public String getNotificationSection() {
		return notificationSection;
	}
	public String getChatSection() {
		return chatSection;
	}
	public String getAdvSection() {
		return advSection;
	}
	
	public ChatNotification getChatNotificationByNode(String node) {
		return new ChatNotification(node, this.config.getDouble(chatSection + "." + node + ".delay"),
				this.config.getString(chatSection + "." + node + ".prefix"),
				this.config.getStringList(chatSection + "." + node + ".messages"),
				this.config.getBoolean(chatSection + "." + node + ".permission"));
	}
	
	public AdvancementNotification getAdvancementNotificationByNode(String node) {
		return new AdvancementNotification(node, this.config.getDouble(advSection + "." + node + ".delay"),
				this.config.getMapList(advSection + "." + node + ".messages"),
				this.config.getBoolean(advSection + "." + node + ".permission"));
	}
	
}
