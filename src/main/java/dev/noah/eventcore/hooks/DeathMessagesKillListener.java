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

import dev.mrshawn.deathmessages.api.events.BroadcastDeathMessageEvent;
import dev.noah.eventcore.EventCore;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class DeathMessagesKillListener implements Listener {

        @EventHandler
    public void onKill(BroadcastDeathMessageEvent e) {
        if (e.getLivingEntity() != null) {
            if (e.getLivingEntity() instanceof Player) {
                Player killer = (Player) e.getLivingEntity();
                Player target = e.getPlayer();
                killer.sendMessage(MiniMessage.miniMessage().deserialize(EventCore.green+ "You killed<white> "+target.getName()+ EventCore.green +"!") );
            }
        }



    }


//    @EventHandler
//    public void onKill(PlayerDeathEvent e) {
////        for(Player p: Bukkit.getOnlinePlayers()){
////            p.sendMessage(MiniMessage.miniMessage().deserialize("debug: "+e.getLivingEntity().getName()+" killed: " +e.getPlayer().getName()) );
////        }
//        if (e.getEntity() != null) {
//            if (e.getEntity() instanceof Player) {
//                Player killer = (Player) e.getEntity();
//                Player target = e.getPlayer();
//                killer.sendMessage(MiniMessage.miniMessage().deserialize(EventCore.green+ "You killed<white> "+target.getName()+ EventCore.green +"a!") );
//            }
//        }
//
//
//
//    }

    @EventHandler
    public void onDeathMessageEvent(BroadcastDeathMessageEvent e){
        if(e.getLivingEntity()==null){
            return;
        }
        if(e.getLivingEntity().getType()== EntityType.PLAYER){

            Player killer = (Player)  e.getLivingEntity();

            EventCore.kills.compute(killer.getUniqueId(), (key, val)
                    -> (val == null)
                    ? 1
                    : val + 1);
        }
    }



//    @EventHandler
//    public void onDeathMessageEvent(PlayerDeathEvent e){
//        if(e.getEntity()==null){
//            return;
//        }
//        if(e.getEntity().getType()== EntityType.PLAYER){
//
//            Player killer = (Player)  e.getEntity();
//
//            EventCore.kills.compute(killer.getUniqueId(), (key, val)
//                    -> (val == null)
//                    ? 1
//                    : val + 1);
//        }
//    }
}
