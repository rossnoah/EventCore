package dev.noah.eventcore.listener;

import dev.noah.eventcore.EventCore;
import dev.noah.eventcore.util.PlayerUtils;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BuildLimitListener implements Listener {

    @EventHandler
    public void onBlockPlace (BlockPlaceEvent e){
        if(!PlayerUtils.hasBypass(e.getPlayer())){
            if(e.getBlock().getLocation().getBlockY()> EventCore.buildLimit){
                e.setCancelled(true);
                e.getPlayer().sendMessage(MiniMessage.miniMessage().deserialize(EventCore.red+"You have reached build limit"));
                e.getPlayer().sendActionBar(MiniMessage.miniMessage().deserialize(EventCore.red+"You have reached build limit"));
            }
        }
    }
}
