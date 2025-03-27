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
package dev.noah.eventcore.hooks;

import dev.noah.eventcore.EventCore;
import dev.noah.eventcore.events.CustomKillEvent;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class CustomKillListener implements Listener {

        @EventHandler
    public void onKill(CustomKillEvent e) {

            Player killer = e.getKiller();
            Player victim = e.getVictim();
            if(killer==null){
                return;
            }
            EventCore.kills.compute(killer.getUniqueId(), (key, val)//increments the killer's kill count
                    -> (val == null)
                    ? 1
                    : val + 1);

            killer.sendMessage(MiniMessage.miniMessage().deserialize(EventCore.green+ "You killed<white> "+victim.getName()+ EventCore.green +"!") );
    }


}
