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

import dev.noah.eventcore.util.KitUtil;
import dev.noah.eventcore.EventCore;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class    JoinListener implements Listener {
    @EventHandler
    public void onJoin (PlayerJoinEvent e){

        EventCore.names.put(e.getPlayer().getUniqueId(),e.getPlayer().getName());


        if(EventCore.kitOnJoin&&!EventCore.inGame){
            KitUtil.givekit(e.getPlayer());
            e.getPlayer().sendMessage(MiniMessage.miniMessage().deserialize(EventCore.green+"Welcome to the event! You have been given a kit."));
        }
        if(!EventCore.inGame){
            e.joinMessage(MiniMessage.miniMessage().deserialize("<gray>["+EventCore.green+"+"+"<gray>] "+e.getPlayer().getName()));


            e.getPlayer().setGameMode(GameMode.ADVENTURE);
        }
        if(EventCore.inGame){
            e.joinMessage(Component.text(""));
            e.getPlayer().setGameMode(GameMode.SPECTATOR);
            e.getPlayer().sendMessage(MiniMessage.miniMessage().deserialize(EventCore.red+"<bold>The event has already started, but you can still spectate!"));
        }
    }
}
