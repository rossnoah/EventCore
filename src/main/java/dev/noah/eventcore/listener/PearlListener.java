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

import dev.noah.eventcore.EventCore;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.WorldBorder;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

public class PearlListener implements Listener {

    @EventHandler
    public void onPearl(PlayerTeleportEvent e) {
        if (e.getCause() == PlayerTeleportEvent.TeleportCause.ENDER_PEARL) {
            if(isOutsideOfBorder(e.getTo())){
                WorldBorder border = Bukkit.getWorld("world").getWorldBorder();
                double size = border.getSize() / 2;
                Location center = border.getCenter();
                if((e.getTo().distance(center)>e.getFrom().distance(center))){
                    e.setCancelled(true);
                    e.getPlayer().sendMessage(MiniMessage.miniMessage().deserialize(EventCore.red+"Please don't leave the playing area!"));
                }

            }
            if((!isOutsideOfBorder(e.getFrom())&&isOutsideOfBorder(e.getTo()))){
                e.setCancelled(true);
                e.getPlayer().sendMessage(MiniMessage.miniMessage().deserialize(EventCore.red+"Please don't leave the playing area!"));
            }
        }
    }


    public boolean isOutsideOfBorder(Location loc) {
        if (Bukkit.getWorld("world") != null) {
            WorldBorder border = Bukkit.getWorld("world").getWorldBorder();
            double size = border.getSize() / 2;
            Location center = border.getCenter();
            double x = loc.getX() - center.getX(), z = loc.getZ() - center.getZ();
            return ((x > size-0.5  || (-x) > size-0.5 ) || (z > size-0.5  || (-z) > size-0.5));
        }
        return false;
    }
}

