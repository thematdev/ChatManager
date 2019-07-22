package ru.thematdev.cm.command.send;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SendCommandExecutor implements CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		// sendnfc type player isanonymous
		if (args.length < 3) {
			return false;
		}
		String type = args[0];
		switch (type) {
		case ("chat"):
			System.out.println("chat");
			break;
		case ("advancement"):
			System.out.println("adv");
			break;
		default:
			System.out.println("err");
			break;
		}
		return true;
	}
	

}
