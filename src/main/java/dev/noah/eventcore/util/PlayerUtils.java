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
