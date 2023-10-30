package dev.noah.eventcore.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;

public class PreventExplosionsListener implements Listener {


    public static boolean enabled = false;

    @EventHandler
    public void onBlockExplode(BlockExplodeEvent e) {
        if (enabled) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onEntityExplode(EntityExplodeEvent e) {
        if (enabled) {
            e.setCancelled(true);
        }
    }


    @EventHandler
    public void onExplosionDamage(EntityDamageEvent e) {
        if (enabled) {
            if(e.getCause()== EntityDamageEvent.DamageCause.BLOCK_EXPLOSION|| e.getCause()== EntityDamageEvent.DamageCause.ENTITY_EXPLOSION){
                e.setCancelled(true);
                e.setDamage(0);
            }
        }
    }


}