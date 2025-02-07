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

import org.bukkit.Location;
import org.bukkit.WorldBorder;
import org.bukkit.entity.Player;

import static dev.noah.eventcore.util.PlayerUtils.getAlive;

public class BorderUtil {

    public boolean isOutsideOfBorder(Player p) {
        return isOutsideOfBorder(p.getLocation());
    }




    public static boolean isOutsideOfBorder(Location loc) {
        WorldBorder border = loc.getWorld().getWorldBorder();
        double size = border.getSize()/2;
        Location center = border.getCenter();
        double x = loc.getX() - center.getX(), z = loc.getZ() - center.getZ();
        return ((x > size || (-x) > size) || (z > size || (-z) > size));
    }


    public static int getOptimalBorderDiameter(){

        int size = (int)(((Math.pow(getAlive(),2))/60+4+0.6*getAlive())*2);

        return size;


    }
}
