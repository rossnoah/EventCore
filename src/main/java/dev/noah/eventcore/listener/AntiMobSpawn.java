package dev.noah.eventcore.listener;

import org.bukkit.entity.Animals;
import org.bukkit.entity.Endermite;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Mob;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.event.world.EntitiesLoadEvent;

public class AntiMobSpawn implements Listener {

    @EventHandler
    public void onMobSpawn(EntitySpawnEvent e){
        if(e.getEntity() instanceof Mob ||e.getEntity() instanceof Animals)
        {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onChunkLoad(EntitiesLoadEvent e){

        for(Entity entity: e.getEntities()){
            if(entity instanceof Mob || entity instanceof Animals){
                entity.remove();
            }
        }
    }

}
