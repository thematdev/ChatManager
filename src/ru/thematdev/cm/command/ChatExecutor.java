package ru.thematdev.cm.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import ru.thematdev.cm.main.Main;
import ru.thematdev.cm.util.Utils;

public class ChatExecutor implements CommandExecutor, Listener {
	
	private Main plugin;
	
	public ChatExecutor(Main plugin) {
		this.plugin = plugin;
	}
	

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) return false;
		if (args.length < 1) return false;
		CommandEnum command = CommandEnum.valueOf(args[0]);
		command.call((Player) sender, plugin);
		
		return true;
	}
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		if(Utils.muted) e.setCancelled(true);
	}

}
