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

import dev.noah.eventcore.util.SchedulerUtils;
import dev.noah.eventcore.EventCore;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class AntiVoidDamage implements Listener {


    @EventHandler
    public void onDamage(EntityDamageEvent e){
        if(e.getEntity() instanceof Player){
            Player p = (Player) e.getEntity();
            if(e.getCause()== EntityDamageEvent.DamageCause.VOID){
                SchedulerUtils.runLater(()->{
                            e.getEntity().teleport(e.getEntity().getLocation().add(0,100,0));
                            p.sendActionBar(MiniMessage.miniMessage().deserialize(EventCore.red+"You have been saved from the void!"));

                        },1);


            }
        }






    }
}
