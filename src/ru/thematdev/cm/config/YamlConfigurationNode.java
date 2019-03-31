package ru.thematdev.cm.config;

import java.util.List;
import java.util.Map;

public interface YamlConfigurationNode {
	
	/*
	 * It is just all YamlConfiguration methods. If i had used Jackson i would have used generic method instead all of this.
	 */
	
	public String getName();

    public Object get(Object def);

    boolean getAsBoolean(boolean def);

    String getAsString(String def);

    long getAsLong(long def);

    int getAsInt(int def);

    List getAsList(List def);

    List<Map<?, ?>> getAsMapList();

    List<String> getAsStringList();

    YamlConfigurationNode getNode(String path);

    List<YamlConfigurationNode> getChildNodes();

    void set(Object value);

    boolean isEmpty();
	
}
