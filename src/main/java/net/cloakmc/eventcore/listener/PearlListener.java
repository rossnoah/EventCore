package net.cloakmc.eventcore.listener;

import net.cloakmc.eventcore.EventCore;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.WorldBorder;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

public class PearlListener implements Listener {

    @EventHandler
    public void onPearl(PlayerTeleportEvent e) {
        if (e.getCause() == PlayerTeleportEvent.TeleportCause.ENDER_PEARL) {
            if(isOutsideOfBorder(e.getTo())){
                WorldBorder border = Bukkit.getWorld("world").getWorldBorder();
                double size = border.getSize() / 2;
                Location center = border.getCenter();
                if((e.getTo().distance(center)>e.getFrom().distance(center))){
                    e.setCancelled(true);
                    e.getPlayer().sendMessage(MiniMessage.miniMessage().deserialize(EventCore.red+"Please don't leave the playing area!"));
                }

            }
            if((!isOutsideOfBorder(e.getFrom())&&isOutsideOfBorder(e.getTo()))){
                e.setCancelled(true);
                e.getPlayer().sendMessage(MiniMessage.miniMessage().deserialize(EventCore.red+"Please don't leave the playing area!"));
            }
        }
    }


    public boolean isOutsideOfBorder(Location loc) {
        if (Bukkit.getWorld("world") != null) {
            WorldBorder border = Bukkit.getWorld("world").getWorldBorder();
            double size = border.getSize() / 2;
            Location center = border.getCenter();
            double x = loc.getX() - center.getX(), z = loc.getZ() - center.getZ();
            return ((x > size-0.5  || (-x) > size-0.5 ) || (z > size-0.5  || (-z) > size-0.5));
        }
        return false;
    }
}

