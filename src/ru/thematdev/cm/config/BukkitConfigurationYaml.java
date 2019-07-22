package ru.thematdev.cm.config;

import java.io.File;

import org.bukkit.configuration.file.YamlConfiguration;

import ru.thematdev.cm.main.Main;

public class BukkitConfigurationYaml extends ConfigurationYaml {
	
	@SuppressWarnings("unused")
	private final String filename;
	private YamlConfiguration config;
	private Main plugin;
	
	public BukkitConfigurationYaml(String filename) {
		this.filename = filename;
		this.plugin = Main.instance();
		File file = new File(plugin.getDataFolder(), filename);
		this.config = YamlConfiguration.loadConfiguration(file);
	}
	
	public BukkitConfigurationYaml() {
		this.filename = "plugin.yml";
		this.plugin = Main.instance();
		this.config = (YamlConfiguration) plugin.getConfig();
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
