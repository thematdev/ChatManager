package ru.thematdev.cm.auto.notification;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.advancement.Advancement;
import org.bukkit.advancement.AdvancementProgress;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.Player;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import com.google.gson.GsonBuilder;

import ru.thematdev.cm.main.Main;
import ru.thematdev.cm.util.Utils;

public class AdvancementMessage implements ConfigurationSerializable{
	
	private NamespacedKey id;
	private String icon;
	private String header, footer;
	private Main plugin;
	
    public AdvancementMessage(Map<String, String> list) {
        this(list.getOrDefault("header", "Header"),
                list.getOrDefault("footer", "Message #1"),
                list.getOrDefault("icon", "minecraft:apple"));
    }

    public AdvancementMessage(String header, String footer, String icon) {
        this.header = header;
        this.footer = footer;
        this.icon = icon;
        this.plugin = Main.instance();

        this.id = new NamespacedKey(plugin, "thematdev" + new Random().nextInt(2281337) + 1);
    }
	
	public String json() {
		JsonObject jsonObject = new JsonObject();
		JsonObject displayObject = new JsonObject();
		JsonObject iconObject = new JsonObject();
		
		iconObject.addProperty("item", this.icon);
		
		displayObject.add("icon", iconObject);
		displayObject.addProperty("title", Utils.COLORIZE.apply(this.header + "\n" + this.footer));
		displayObject.addProperty("description", "Cm announce!");
		displayObject.addProperty("background", "minecraft:textures/gui/advancements/backgrounds/stone.png");
		displayObject.addProperty("frame", "goal");
		displayObject.addProperty("announce_to_chat", false);
		displayObject.addProperty("show_toast", true);
		displayObject.addProperty("hidden", true);
		
		JsonObject trigger = new JsonObject();
		trigger.addProperty("trigger", "minecraft:impossible");
		
		JsonObject crit = new JsonObject();
		crit.add("impossible", trigger);
		
		jsonObject.add("criteria", crit);
		jsonObject.add("display", displayObject);
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		return gson.toJson(jsonObject);
	}
	
	public void show(Player p) {
		this.register();
		
		this.grant(p);
		
		Bukkit.getScheduler().runTaskLater(plugin, () -> {
			revoke(p);
			unregister();
		}, 20);
		
	}
	
	@SuppressWarnings("deprecation")
	public void register() {
		Bukkit.getUnsafe().loadAdvancement(id, this.json());
	}
	
	@SuppressWarnings("deprecation")
	public void unregister() {
		Bukkit.getUnsafe().removeAdvancement(id);
	}
	
	public void grant(Player player) {
        Advancement advancement = Bukkit.getAdvancement(id);
        AdvancementProgress progress = player.getAdvancementProgress(advancement);
        if (!progress.isDone()) {
            progress.getRemainingCriteria().forEach(progress::awardCriteria);
        }
	}
	
	
	public void revoke(Player player) {
		Advancement advancement = Bukkit.getAdvancement(id);
		AdvancementProgress progress = player.getAdvancementProgress(advancement);
		if (progress.isDone()) {
			progress.getAwardedCriteria().forEach(progress::revokeCriteria);
		}
		
		
	}

	@Override
	public Map<String, Object> serialize() {
		Map<String, Object> ret = new HashMap<>();
		
		ret.put("icon", icon);
		ret.put("header", header);
		ret.put("footer", footer);
		
		return ret;
	}

}
