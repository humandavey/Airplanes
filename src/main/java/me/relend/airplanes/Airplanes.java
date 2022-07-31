package me.relend.airplanes;

import me.relend.airplanes.commands.SpawnPlaneCommand;
import me.relend.airplanes.config.Config;
import me.relend.airplanes.config.ConfigHandler;
import me.relend.airplanes.listeners.JoinListener;
import me.relend.airplanes.listeners.PlayerMountPlaneListener;
import me.relend.airplanes.managers.Plane;
import me.relend.airplanes.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.java.JavaPlugin;

public class Airplanes extends JavaPlugin {

    private Config config;

    @Override
    public void onEnable() {
        ConfigHandler configHandler = new ConfigHandler(this);
        configHandler.loadConfig();
        config = configHandler.getConfig();

        registerCommands();
        registerListeners();

        registerPlanes();
    }

    public Config getConfiguration() {
        return config;
    }

    private void registerCommands() {
        new SpawnPlaneCommand();
    }

    private void registerListeners() {
        getServer().getPluginManager().registerEvents(new JoinListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerMountPlaneListener(), this);
    }

    private void registerPlanes() {
        for (World world : Bukkit.getWorlds()) {
            for (Entity entity : world.getEntities()) {
                if (entity instanceof ArmorStand) {
                    if (entity.getMetadata("callsign").get(0).asString() != null) {
                        new Plane(this, Util.getPlayer(entity.getMetadata("callsign").get(0).asString()), Util.modelDataToPlaneID(((ArmorStand) entity).getHelmet().getItemMeta().getCustomModelData()), false);
                        System.out.println("loaded plane" + entity.getMetadata("callsign").get(0).asString());
                    }
                }
            }
        }
    }
}
