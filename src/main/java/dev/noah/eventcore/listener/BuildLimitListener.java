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
import dev.noah.eventcore.util.PlayerUtils;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BuildLimitListener implements Listener {

    @EventHandler
    public void onBlockPlace (BlockPlaceEvent e){
        if(!PlayerUtils.hasBypass(e.getPlayer())){
            if(e.getBlock().getLocation().getBlockY()> EventCore.buildLimit){
                e.setCancelled(true);
                e.getPlayer().sendMessage(MiniMessage.miniMessage().deserialize(EventCore.red+"You have reached build limit"));
                e.getPlayer().sendActionBar(MiniMessage.miniMessage().deserialize(EventCore.red+"You have reached build limit"));
            }
        }
    }
}
