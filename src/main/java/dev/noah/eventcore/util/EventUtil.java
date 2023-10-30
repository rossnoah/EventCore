package dev.noah.eventcore.util;

import org.bukkit.*;

public class EventUtil {

    public static void startGame(){
        World w = Bukkit.getWorld("world");
        w.setDifficulty(Difficulty.HARD);
        w.setGameRule(GameRule.ANNOUNCE_ADVANCEMENTS,false);
    GamemodeUtil.gamemodeAll(GameMode.SURVIVAL);



    }

}
