package dev.noah.eventcore.listener;

import dev.noah.eventcore.EventCore;
import dev.noah.eventcore.util.PlayerUtils;
import org.bukkit.GameMode;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class PreGameListener implements Listener {

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        if (!EventCore.inGame) {
            if (e.getEntityType() == EntityType.PLAYER) {
                Player p = (Player) e.getEntity();
                if (p.getGameMode() == GameMode.ADVENTURE) {

                    if (!PlayerUtils.hasBypass(p)) {
                        e.setCancelled(true);
                    }
                }
            }
        }
    }


    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (!EventCore.inGame) {
            Player p = e.getPlayer();
            if (p.getGameMode() == GameMode.ADVENTURE) {

                if (!PlayerUtils.hasBypass(p)) {
                    e.setCancelled(true);
                }
            }
        }
    }


}
