package ru.thematdev.cm.config;

import org.bukkit.configuration.file.FileConfiguration;

import lombok.Getter;
import lombok.Setter;
import ru.thematdev.cm.auto.notification.AdvancementNotification;
import ru.thematdev.cm.auto.notification.ChatNotification;
import ru.thematdev.cm.main.Main;

public class Configuration {
	
	@Getter @Setter public FileConfiguration config;
	@Getter private String notificationSection;
	@Getter private String chatSection;
	@Getter private String advSection;
	
	private Main plugin;
	
	public Configuration() {
		
		this.plugin = Main.instance();
		
		this.config = plugin.getConfigFile();
		
		this.notificationSection = "auto.notifications";
		this.chatSection = notificationSection + ".chat";
		this.advSection = notificationSection + ".advancement";
		
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
