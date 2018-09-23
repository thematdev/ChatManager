package ru.thematdev.cm.auto.join;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import ru.thematdev.cm.util.Utils;

public class AMJoinListener implements Listener{
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 5, 5));
		p.setVelocity(new Vector(10, 3, 1));
		//Utils.sendMessage(p, "&a Приветствуем Вас, " + Utils.getPlayerPrefix(p) + " " + p.getName() + " " + Utils.getPlayerSuffix(p) + "&a на нашем проекте!");
	}

}
