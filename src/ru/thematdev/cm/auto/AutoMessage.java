package ru.thematdev.cm.auto;

import java.util.ArrayList;
import java.util.List;

import ru.thematdev.cm.auto.notification.AdvancementNotification;
import ru.thematdev.cm.auto.notification.ChatNotification;
import ru.thematdev.cm.config.Configuration;
import ru.thematdev.cm.main.Main;

public class AutoMessage {
	
	Main plugin;
	private Configuration configuration;
    private List<ChatNotification> chatNotifications
    = new ArrayList<>();
    private List<AdvancementNotification> advancementNotifications
    = new ArrayList<>();
			
	public AutoMessage() {
		
		this.plugin = Main.instance();
		
		configuration = new Configuration();
		
		for (String node : configuration.getConfig().getConfigurationSection("auto.notifications.chat").getKeys(false)) {
			plugin.getLogger().info(node);
			
			ChatNotification notification = new ChatNotification(node, configuration.config.getDouble(configuration.getChatSection() + "." + node + ".delay"),
					configuration.config.getString(configuration.getChatSection() + "." + node + ".prefix"),
					configuration.config.getStringList(configuration.getChatSection() + "." + node + ".messages"),
					configuration.config.getBoolean(configuration.getChatSection() + "." + node + ".permission"));
			System.out.println(configuration.config.getStringList(configuration.getChatSection() + "." + node + ".messages"));
			//System.out.println(configuration.config. + "." + node + ".messages");
			chatNotifications.add(notification);
			
		}
		for (String node : configuration.config.getConfigurationSection("auto.notifications.advancement").getKeys(false)) {
			
			AdvancementNotification notification = new AdvancementNotification(node,
					configuration.config.getDouble(configuration.getAdvSection() + "." + node + ".delay"),
					configuration.config.getMapList(configuration.getAdvSection() + "." + node + ".messages"),
					configuration.config.getBoolean(configuration.getAdvSection() + "." + node + ".permission"));
			advancementNotifications.add(notification);
		
		}
		
	}

}
