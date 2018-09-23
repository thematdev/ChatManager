package ru.thematdev.cm.auto.notification;

import java.util.List;
import java.util.Map;

import org.bukkit.plugin.java.JavaPlugin;

import ru.thematdev.cm.main.Main;
import ru.thematdev.cm.refl.Reflection;

public class AdvancementNotification extends Notification{
	
	private static final String PERMISSION_NODE = NOTIFICATION_NODE + "advancement.%s";
	private final List<Map<?, ?>> messages;
	private final String name;
	private JavaPlugin plugin;
	
	private int current = -228;
	
	
    public AdvancementNotification(String name, double delay, List<Map<?, ?>> messages, boolean permission) {
        super(delay, permission);

        this.name = name;
        this.plugin = Main.instance();
        this.messages = messages;
    }

	@SuppressWarnings("unchecked")
    @Override
    public void run() {
        if (current < 0 || messages.size() <= ++current) {
            current = 0;
        }
        if (messages.isEmpty()) {
			plugin.getLogger().info("Empty");
			this.cancel();
			return;
        }

        @SuppressWarnings("all")
        AdvancementMessage advancementMessage = new AdvancementMessage((Map<String, String>) messages.get(current));
        Reflection.getOnlinePlayers().stream().filter(player -> permission || player.hasPermission(String.format(PERMISSION_NODE, name))).forEach(advancementMessage::show);
        
        plugin.getLogger().info("Advancement Notification " + name);
        
    }
	
	

}
