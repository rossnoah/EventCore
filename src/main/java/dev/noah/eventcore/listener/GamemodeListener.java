package dev.noah.eventcore.listener;

import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerGameModeChangeEvent;

public class GamemodeListener implements Listener {

    @EventHandler
    public void onGamemode(PlayerGameModeChangeEvent e){
        if(!(e.getNewGameMode()== GameMode.SURVIVAL&&e.getPlayer().getGameMode()==GameMode.ADVENTURE)){
        }
    }
}
