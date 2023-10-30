package dev.noah.eventcore.listener;

import dev.noah.eventcore.util.KitUtil;
import dev.noah.eventcore.EventCore;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class    JoinListener implements Listener {
    @EventHandler
    public void onJoin (PlayerJoinEvent e){

        EventCore.names.put(e.getPlayer().getUniqueId(),e.getPlayer().getName());


        if(EventCore.kitOnJoin&&!EventCore.inGame){
            KitUtil.givekit(e.getPlayer());
            e.getPlayer().sendMessage(MiniMessage.miniMessage().deserialize(EventCore.green+"Welcome to the event! You have been given a kit."));
        }
        if(!EventCore.inGame){
            e.joinMessage(MiniMessage.miniMessage().deserialize("<gray>["+EventCore.green+"+"+"<gray>] "+e.getPlayer().getName()));


            e.getPlayer().setGameMode(GameMode.ADVENTURE);
        }
        if(EventCore.inGame){
            e.joinMessage(Component.text(""));
            e.getPlayer().setGameMode(GameMode.SPECTATOR);
            e.getPlayer().sendMessage(MiniMessage.miniMessage().deserialize(EventCore.red+"<bold>The event has already started, but you can still spectate!"));
        }
    }
}
