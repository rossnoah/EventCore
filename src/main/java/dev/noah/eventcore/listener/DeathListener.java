package dev.noah.eventcore.listener;

import dev.noah.eventcore.util.SchedulerUtils;
import dev.noah.eventcore.EventCore;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathListener implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        if (!e.getEntity().hasPermission("event.bypass")) {
            e.getEntity().setGameMode(GameMode.SPECTATOR);

        }
        final Player p = e.getEntity();
        final Location l = e.getPlayer().getLocation().clone();
        SchedulerUtils.runLater(() -> {
            p.teleport(l);
        }, 5);
    }



}