package me.relend.airplanes.util;

import me.relend.airplanes.Airplanes;
import me.relend.airplanes.managers.Plane;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

public class Util {

    public static ArrayList<Plane> planes = new ArrayList<>();

    public static String colorize(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static String generateCallsign() {
        StringBuilder name = new StringBuilder("N");
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWYZ";
        String numbers = "0123456789";
        int length = new Random().nextInt(2, 5);
        while (length > 0) {
            int numberIndex = new Random().nextInt(0, numbers.length());
            name.append(numbers.charAt(numberIndex));
            length--;
        }
        int letter1Index = new Random().nextInt(0, letters.length());
        int letter2Index = new Random().nextInt(0, letters.length());

        name.append(letters.charAt(letter1Index));
        name.append(letters.charAt(letter2Index));
        return name.toString();
    }

    public static String getCallSign(Player player) {
        return JavaPlugin.getPlugin(Airplanes.class).getConfiguration().getCallsign(player);
    }

    public static boolean hasPlaneSpawned(Player player) {
        for (Entity entity : player.getWorld().getEntities()) {
            if (entity instanceof ArmorStand) {
                if (entity.getMetadata("callsign").get(0).asString().equals(getCallSign(player))) {
                    return true;
                }
            }
        }
        return false;
    }

    public static Plane getPlaneOf(Player player) {
        for (Plane plane : planes) {
            if (plane.getCallsign().equals(getCallSign(player))) {
                return plane;
            }
        }
        return null;
    }

    public static Player getPlayer(String callsign) {
        for (String section : JavaPlugin.getPlugin(Airplanes.class).getConfig().getConfigurationSection("players").getKeys(false)) {
            if (JavaPlugin.getPlugin(Airplanes.class).getConfig().getString("players." + section + ".callsign").equals(callsign)) {
                return Bukkit.getPlayer(UUID.fromString(section));
            }
        }
        return null;
    }

    public static String modelDataToPlaneID(int modelData) {
        for (String section : JavaPlugin.getPlugin(Airplanes.class).getConfig().getConfigurationSection("planes").getKeys(false)) {
            if (JavaPlugin.getPlugin(Airplanes.class).getConfig().getInt("planes." + section + ".mode-data") == modelData) {
                return section;
            }
        }
        return null;
    }
}
