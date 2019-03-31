package ru.thematdev.cm.config;

public abstract class ConfigurationYaml {
	
	public abstract YamlConfigurationNode getNode(String path);

    public abstract void save();

    public abstract void onReload();
    
    public void reload() {
    	onReload();
    }

}
