package net.cloakmc.eventcore.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemDamageEvent;

public class DurabilityListener implements Listener {

    public static boolean enabled = false;

    @EventHandler
    public void onDurabilityChange(PlayerItemDamageEvent e){

        if(!enabled){
            e.setCancelled(true);
        }

    }
}
