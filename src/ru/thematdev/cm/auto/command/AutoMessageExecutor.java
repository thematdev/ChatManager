package ru.thematdev.cm.auto.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import ru.thematdev.cm.auto.AutoMessage;

public class AutoMessageExecutor implements CommandExecutor{
	
	AutoMessage autoMessageInstance;
	
	public AutoMessageExecutor(AutoMessage autoMessageInstance) {
		this.autoMessageInstance = autoMessageInstance;
	}
	/*
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length != 1) return false;
		if (!(sender instanceof Player)) return false;
		Player p = (Player) sender;
		switch(args[0]) {
		case "info": Utils.sendMessage(p, "&3 Плагин AutoMessage от thematdev'а ");
		return true;
		case "off": 
			if (!p.hasPermission("cm.auto.off")) {
				Utils.sendMessage(p, "&4 У вас недостаточно прав!");
				return true;
			}
			autoMessageInstance.task.cancel();
		}
		return true;
	} */

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		// TODO Auto-generated method stub
		return false;
	}
	
	

}
