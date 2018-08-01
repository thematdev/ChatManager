package ru.thematdev.cm.util;

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class Utils {
	
	public static boolean muted = false;
	
	public static void sendMessage(Player p, String message) {
		p.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
	}
	
	public static void broadcastMessage(String message) {
		for (Player p: Bukkit.getServer().getOnlinePlayers()) p.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
	}
	
	public static void broadcastMessage(String message, String emptyPart) {
		for (Player p: Bukkit.getServer().getOnlinePlayers()) p.sendMessage(ChatColor.translateAlternateColorCodes('&', message) + emptyPart);
	}
	
	public static void log(Level level, String message) {
		System.out.println(message);
	}
	
	public static String getPlayerPrefix(Player p) {
		PermissionUser user = PermissionsEx.getUser(p);
		return user.getPrefix();
	}
	
	public static String getPlayerSuffix(Player p) {
		PermissionUser user = PermissionsEx.getUser(p);
		return user.getSuffix();
	}
	
	public static String /* Maybe not string */ getPlayerClass(Player p) {
		return null; // Only for now
	}
	


}
