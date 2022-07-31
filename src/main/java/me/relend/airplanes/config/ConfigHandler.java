package me.relend.airplanes.config;

import me.relend.airplanes.Airplanes;

public class ConfigHandler {

    private final Airplanes plugin;
    private final Config config;

    public ConfigHandler(Airplanes plugin) {
        this.plugin = plugin;
        this.config = new Config(plugin);
    }

    public void loadConfig() {
        plugin.getConfig().options().copyDefaults();
        plugin.saveDefaultConfig();
    }

    public Config getConfig() {
        return config;
    }
}
