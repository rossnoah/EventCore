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