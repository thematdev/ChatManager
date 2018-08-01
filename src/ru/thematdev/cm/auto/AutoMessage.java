package ru.thematdev.cm.auto;

import java.util.List;
import java.util.Random;

import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import ru.thematdev.cm.auto.command.AutoMessageExecutor;
import ru.thematdev.cm.main.Main;
import ru.thematdev.cm.util.Utils;

public class AutoMessage {
	
	Main plugin;
	public int time;
	public List<String> messageList;
	
	public BukkitTask task;
			
	public AutoMessage(Main plugin) {
		this.plugin = plugin;
		
		time = plugin.config.getInt("auto.time");
		messageList = plugin.config.getStringList("auto.messages");
		
		plugin.getCommand("automessage").setExecutor(new AutoMessageExecutor(this));
		
		task = new BukkitRunnable() {
			
			@Override
			public void run() {
				Random random = new Random();
				Utils.broadcastMessage(messageList.get(random.nextInt(messageList.size())));
				plugin.logger.info("AutoMessage by Cm!");
			}
			
			
		}.runTaskTimer(plugin, 0L, 20L * (time));
		
	}

}
