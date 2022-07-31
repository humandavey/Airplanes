package me.relend.airplanes.listeners;

import me.relend.airplanes.util.Util;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        event.getPlayer().sendMessage(Util.colorize("&eYour callsign is: &6" + Util.getCallSign(event.getPlayer())));
    }
}
