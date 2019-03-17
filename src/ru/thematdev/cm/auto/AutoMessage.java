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
		
		configuration.getConfig().getConfigurationSection("auto.notifications.chat").getKeys(false).forEach(node -> chatNotifications.add(configuration.getChatNotificationByNode(node)));
		configuration.getConfig().getConfigurationSection("auto.notifications.advancement").getKeys(false).forEach(node -> advancementNotifications.add(configuration.getAdvancementNotificationByNode(node)));
		
	}
	
	public void cancelAll() {
		chatNotifications.forEach(ChatNotification::cancel);
		advancementNotifications.forEach(AdvancementNotification::cancel);
	}
	
	public void startAll() {
		chatNotifications.forEach(ChatNotification::start);
		advancementNotifications.forEach(AdvancementNotification::start);
	}
	
	public void restartAll() {
		cancelAll();
		startAll();
	}

}
