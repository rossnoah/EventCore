package net.cloakmc.eventcore.listener;

import net.cloakmc.eventcore.EventCore;
import dev.mrshawn.deathmessages.api.events.BroadcastDeathMessageEvent;

import net.cloakmc.eventcore.util.SchedulerUtils;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
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