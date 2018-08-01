package ru.thematdev.cm.command;

import java.util.function.Function;

import org.bukkit.entity.Player;

import ru.thematdev.cm.util.Utils;

public class ChatCommand {
	
	Function<Player, Boolean> function;
	String node;
	
	public ChatCommand(Function<Player, Boolean> function, String node) {
		this.function = function;
		this.node = node;
	}
	
	public void call(Player p) {
		if (p.hasPermission(node)) {
			function.apply(p);
			return;
		}
		Utils.sendMessage(p, "&4 У Вас недостаточно праы!");
	}
	
	public static ChatCommand getByName(String name) {
		switch(name) {
		case "help": return new ChatCommand(new Function<Player, Boolean>() {

			@Override
			public Boolean apply(Player p) {
				Utils.sendMessage(p, "&2 Этот плагин рисует тебе префикс в чате.");
				return true;
			}
			
		}, "cm.help");
		case "clear": return new ChatCommand(new Function<Player, Boolean>() {

			@Override
			public Boolean apply(Player p) {
				for (int i = 0; i<100; i++) Utils.broadcastMessage(" ");
				return true;
			}
			
		}, "cm.clear");
		case "toggle": return new ChatCommand(new Function<Player, Boolean>() {

			@Override
			public Boolean apply(Player p) {
				Utils.muted = !(Utils.muted);
				return true;
			}
			
		}, "cm.toggle");
		}
		
		return null;
	}

}
