/*
 * Copyright 2023 Noah Ross
 *
 * This file is part of EventCore.
 *
 * EventCore is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Affero General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * EventCore is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for
 * more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with EventCore. If not, see <https://www.gnu.org/licenses/>.
 */
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
