package me.relend.airplanes.managers;

import me.relend.airplanes.Airplanes;
import me.relend.airplanes.util.Util;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;

public class Plane {

    private final Airplanes plugin;

    private final String callsign;
    private final String planeID;
    private ArmorStand stand;

    public Plane(Airplanes plugin, Player owner, String planeID, boolean spawn) {
        this.plugin = plugin;

        this.planeID = planeID;
        this.callsign = plugin.getConfiguration().getCallsign(owner);
        this.stand = null;

        if (spawn) {
            spawn(owner.getLocation());
        } else {
            Util.planes.add(this);
        }
    }

    public void spawn(Location location) {
        this.stand = location.getWorld().spawn(location, ArmorStand.class);
        this.stand.setMetadata("callsign", new FixedMetadataValue(plugin, this.callsign));

        ItemStack paper = new ItemStack(Material.PAPER);
        ItemMeta paperMeta = paper.getItemMeta();
        paperMeta.setCustomModelData(plugin.getConfiguration().getModelData(planeID));
        paper.setItemMeta(paperMeta);

        this.stand.setHelmet(paper);
        this.stand.addEquipmentLock(EquipmentSlot.HEAD, ArmorStand.LockType.REMOVING_OR_CHANGING);
        this.stand.setInvisible(true);
        this.stand.setInvulnerable(true);
        Util.planes.add(this);
    }

    public String getCallsign() {
        return callsign;
    }

    public ArmorStand getStand() {
        return stand;
    }
}
