package dev.noah.eventcore.listener;

import dev.noah.eventcore.EventCore;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.WorldBorder;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class SpectatorListener implements Listener {

    @EventHandler
    public void onBorderExit(PlayerMoveEvent e) {
        if (e.getPlayer().getGameMode() == GameMode.SPECTATOR) {
            if (!e.getPlayer().hasPermission("event.bypass")) {
                int playerX = e.getPlayer().getLocation().getBlockX();
                int playerZ = e.getPlayer().getLocation().getBlockX();
                int borderX = e.getPlayer().getWorld().getWorldBorder().getCenter().getBlockX();
                int borderZ = e.getPlayer().getWorld().getWorldBorder().getCenter().getBlockZ();

                if (isOutsideOfBorder(e.getPlayer())) {


                    Location l = new Location(e.getPlayer().getWorld(), e.getPlayer().getWorld().getWorldBorder().getCenter().getBlockX(), 85, e.getPlayer().getWorld().getWorldBorder().getCenter().getBlockZ());

                    e.getPlayer().teleport(l);
                    e.getPlayer().sendMessage(MiniMessage.miniMessage().deserialize(EventCore.red+"Please don't leave the playing area!"));
                }
            }
        }

    }

    public boolean isOutsideOfBorder(Player p) {
        Location loc = p.getLocation();
        WorldBorder border = p.getWorld().getWorldBorder();
        double size = border.getSize()/2;
        Location center = border.getCenter();
        double x = loc.getX() - center.getX(), z = loc.getZ() - center.getZ();
        return ((x > size+25 || (-x) > size+25) || (z > size+25 || (-z) > size+25));
    }
}