package me.relend.airplanes.listeners;

import me.relend.airplanes.util.Util;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

public class PlayerMountPlaneListener implements Listener {

    @EventHandler
    public void onPlaneMount(PlayerInteractAtEntityEvent event) {
        if (event.getRightClicked().getMetadata("callsign").get(0).asString().equals(Util.getCallSign(event.getPlayer()))) {
            event.getRightClicked().addPassenger(event.getPlayer());
        }
    }

}
