package me.relend.airplanes.commands;

import me.relend.airplanes.Airplanes;
import me.relend.airplanes.managers.Command;
import me.relend.airplanes.managers.Plane;
import me.relend.airplanes.util.Util;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class SpawnPlaneCommand extends Command {

    public SpawnPlaneCommand() {
        super("spawn", null, null);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof Player player) {
            for (String section : JavaPlugin.getPlugin(Airplanes.class).getConfig().getConfigurationSection("planes").getKeys(false)) {
                if (section.equals(args[0])) {
                    if (!Util.hasPlaneSpawned(player)) {
                        new Plane(JavaPlugin.getPlugin(Airplanes.class), player, args[0], true);
                        player.sendMessage("spawned a plane");
                    } else {
                        player.sendMessage("you already have a plane");
                    }
                }
            }
        }
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        return null;
    }
}
