package ru.thematdev.cm.auto.notification;

import java.util.List;

import ru.thematdev.cm.main.Main;
import ru.thematdev.cm.refl.Reflection;
import ru.thematdev.cm.util.Utils;

public class ChatNotification extends Notification{
	
    private static final String PERMISSION_NODE = NOTIFICATION_NODE + "chat.%s";
    private final String name;
    private final List<String> messages;
    private final String prefix;
    
    private Main plugin;

    private int current = -228;

    public ChatNotification(String name, double delay, String prefix, List<String> messages, boolean permission) {
        super(delay, permission);

        this.name = name;
        this.prefix = prefix;
        this.messages = messages;
        this.plugin = Main.instance();
    }

	@Override
	public void run() {
		
		if (current < 0 || messages.size() <= ++current) current = 0;
		if (messages.isEmpty()) {
			plugin.getLogger().info("Empty");
			this.cancel();
			return;
		}
		
		String[] message = Utils.COLORIZE.apply(prefix + messages.get(current)).split("(?<!\\\\)\\\\n");
        Reflection.getOnlinePlayers().stream().filter(player -> permission || player.hasPermission(String.format(PERMISSION_NODE, name)))
        .forEach(player -> player.sendMessage(message));
        
        plugin.getLogger().info("Chat Notification " + name);
		
		
	}

}
