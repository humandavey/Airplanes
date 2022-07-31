package me.relend.airplanes.config;

import me.relend.airplanes.Airplanes;
import me.relend.airplanes.util.Util;
import org.bukkit.entity.Player;

public class Config {

    private final Airplanes plugin;

    public Config(Airplanes plugin) {
        this.plugin = plugin;
    }

    public String getPrefix() {
        return plugin.getConfig().getString("prefix").equals("") ? "" : plugin.getConfig().getString("prefix").endsWith(" ") ? plugin.getConfig().getString("prefix") : plugin.getConfig().getString("prefix") + " ";
    }

    public String getCallsign(Player player) {
        if (plugin.getConfig().getString("players." + player.getUniqueId() + ".callsign") != null) {
            return plugin.getConfig().getString("players." + player.getUniqueId() + ".callsign");
        } else {
            String callsign = Util.generateCallsign();
            plugin.getConfig().set("players." + player.getUniqueId() + ".callsign", callsign);
            plugin.saveDefaultConfig();
            return callsign;
        }
    }

    public int getModelData(String planeID) {
        return plugin.getConfig().getInt("planes." + planeID + ".model-data");
    }
}
