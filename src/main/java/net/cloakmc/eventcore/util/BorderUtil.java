package net.cloakmc.eventcore.util;

import org.bukkit.Location;
import org.bukkit.WorldBorder;
import org.bukkit.entity.Player;

import static net.cloakmc.eventcore.util.PlayerUtils.getAlive;

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
