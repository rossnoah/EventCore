package net.cloakmc.eventcore.listener;

import net.cloakmc.eventcore.EventCore;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class LeaveListener implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        if(EventCore.inGame){
            e.setQuitMessage("");
        }else{
            e.quitMessage(MiniMessage.miniMessage().deserialize("<gray>["+EventCore.red+"-"+"<gray>] "+e.getPlayer().getName()));
        }
    }

}
