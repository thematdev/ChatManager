package ru.thematdev.cm.command;

import java.util.function.BiFunction;

import org.bukkit.entity.Player;

import ru.thematdev.cm.main.Main;
import ru.thematdev.cm.util.Utils;

public enum CommandEnum {
	
	help(new BiFunction<Player, Main, Boolean>() {

			@Override
			public Boolean apply(Player p, Main instance) {
				Utils.sendMessage(p, "&2 Этот плагин рисует тебе префикс в чате.");
				return true;
			}

			
		}, "cm.help"),
	clear(new BiFunction<Player, Main, Boolean>() {

			@Override
			public Boolean apply(Player p, Main instance) {
				for (int i = 0; i<100; i++) Utils.broadcastMessage(" ");
				return true;
			}
			
		}, "cm.clear"),
	toggle(new BiFunction<Player, Main, Boolean>() {

		@Override
		public Boolean apply(Player p, Main instance) {
			Utils.muted = !(Utils.muted);
			return true;
		}
		
	}, "cm.toggle");
	
	public BiFunction<Player, Main, Boolean> function;
	public String node;
	
	CommandEnum(BiFunction<Player, Main, Boolean> function, String node) {
		this.function = function;
		this.node = node;
	}
	
	public void call(Player p, Main instance) {
		if(!(p.hasPermission(node))) {
			Utils.sendMessage(p, "&4У вас недостаточно прав!");
			return;
		}
		function.apply(p, instance);
		
	}

}
