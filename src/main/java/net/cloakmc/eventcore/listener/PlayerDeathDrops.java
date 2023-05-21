package net.cloakmc.eventcore.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerDeathDrops implements Listener {

    public static boolean enabled = true;

    @EventHandler
    public void onDeath(org.bukkit.event.entity.PlayerDeathEvent e) {
        if (!enabled) {
            e.getDrops().clear();
        }
    }
}
