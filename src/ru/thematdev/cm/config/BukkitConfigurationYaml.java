package ru.thematdev.cm.config;

import java.io.File;

import org.bukkit.configuration.file.YamlConfiguration;

import lombok.Getter;
import ru.thematdev.cm.main.Main;

public class BukkitConfigurationYaml extends ConfigurationYaml {
	
	private final String filename;
	private YamlConfiguration config;
	private Main plugin;
	
	public BukkitConfigurationYaml(String filename) {
		this.filename = filename;
		this.plugin = Main.instance();
		File file = new File(plugin.getDataFolder(), filename);
		this.config = YamlConfiguration.loadConfiguration(file);
	}

	@Override
	public YamlConfigurationNode getNode(String path) {
        if (config.contains(path)
                || config.getConfigurationSection(path) != null)
            return new BukkitYamlConfigurationNode(config, path);

        return new EmptyYamlConfigurationNode(path);
	}

	@Override
	public void save() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onReload() {
		// TODO Auto-generated method stub
		
	}

}
