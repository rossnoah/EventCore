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
package dev.noah.eventcore.util;

import dev.noah.eventcore.EventCore;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.WorldBorder;
import org.bukkit.entity.Player;

public class PlayerUtils {


    public static int getAlive() {

        if(EventCore.simulatePlayers > 0){
            return EventCore.simulatePlayers;
        }

        int count = 0;
        for (Player p : Bukkit.getOnlinePlayers()) {
            if(hasBypass(p)) continue;

            if (p.getGameMode() == GameMode.SURVIVAL||p.getGameMode() == GameMode.ADVENTURE) {
                count++;
            }
        }
        return count;
    }

    public static boolean hasBypass(Player p){
        if (p.hasPermission("event.bypass") ||
                p.hasPermission("event.admin")) {
            return true;
        }
        return false;
    }





}
