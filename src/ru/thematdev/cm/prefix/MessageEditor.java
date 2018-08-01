package ru.thematdev.cm.prefix;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

import ru.thematdev.cm.util.Utils;

@SuppressWarnings("deprecation")
public class MessageEditor implements Listener{
	
	@EventHandler
	public void onChat(PlayerChatEvent e) {
		Player p = e.getPlayer();
		e.setCancelled(true);
		if (Utils.muted && (!p.hasPermission("cm.antitoggle"))) {
			return;
		}
		if (!p.hasPermission("cm.colormessage")) {
			Utils.broadcastMessage(Utils.getPlayerPrefix(p) + e.getPlayer().getName() + " " + Utils.getPlayerSuffix(p) + "⇛",  e.getMessage());
			return;
		}
		Utils.broadcastMessage(Utils.getPlayerPrefix(p) + e.getPlayer().getName() + " " + Utils.getPlayerSuffix(p) + "⇛" + e.getMessage());
	}

}
