package dev.noah.eventcore.listener;

import dev.noah.eventcore.EventCore;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class HungerListener implements Listener {

    @EventHandler
    public void onHungerChange(FoodLevelChangeEvent e) {
        if(!EventCore.inGame){
            e.setCancelled(true);
        }
    }
}
