package ru.thematdev.cm.auto;

import java.util.ArrayList;
import java.util.List;

import ru.thematdev.cm.auto.notification.AdvancementNotification;
import ru.thematdev.cm.auto.notification.ChatNotification;
import ru.thematdev.cm.config.BukkitConfigurationYaml;
import ru.thematdev.cm.config.YamlConfigurationNode;
import ru.thematdev.cm.main.Main;

public class AutoMessage {
	
	Main plugin;
	private BukkitConfigurationYaml configuration;
    private List<ChatNotification> chatNotifications
    = new ArrayList<>();
    private List<AdvancementNotification> advancementNotifications
    = new ArrayList<>();
			
	public AutoMessage(BukkitConfigurationYaml configuration) {
		this.plugin = Main.instance();
		
		this.configuration = configuration;
		initNotifications();
	}
	
	private void initNotifications() {
		YamlConfigurationNode notificationNode = configuration.getNode("auto").getNode("notifications");
		
		YamlConfigurationNode chatNode = notificationNode.getNode("chat");
		YamlConfigurationNode advNode = notificationNode.getNode("advancement");
		
		chatNode.getChildNodes().stream().map(node -> new ChatNotification(node.getName(),
				node.getNode("delay").getAsInt(20),
				node.getNode("prefix").getAsString("abc"),
				node.getNode("messages").getAsStringList(),
				node.getNode("permission").getAsBoolean(true))).forEach(chatNotifications::add);
		advNode.getChildNodes().stream().map(node -> new AdvancementNotification(node.getName(),
				node.getNode("delay").getAsInt(20),
				node.getNode("messages").getAsMapList(),
				node.getNode("permission").getAsBoolean(true))).forEach(advancementNotifications::add);
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
