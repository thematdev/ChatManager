package ru.thematdev.cm.auto.notification;


import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;

import ru.thematdev.cm.main.Main;

public abstract class Notification {
	
	public static final String NOTIFICATION_NODE = "cm.auto.notification.";

    
    public BukkitTask task;
    public final boolean permission;
    
    private double delay;
    private Main plugin;
    
    public Notification(double delay, boolean permission) {
    	
    	plugin = Main.instance();
    	
    	task = Bukkit.getScheduler().runTaskTimer(plugin, this::run, (long) delay * 20, (long) delay * 20);
    	this.permission = permission;
    	
    }
    
    public void cancel() {
    	if (task != null) {
    		task.cancel();
    	}
    }
    
    public void start() {
    	task = Bukkit.getScheduler().runTaskTimer(plugin, this::run, (long) delay * 20, (long) delay * 20);
    }
    
    public abstract void run();

}
