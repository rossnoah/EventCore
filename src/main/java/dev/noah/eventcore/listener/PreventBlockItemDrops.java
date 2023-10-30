package dev.noah.eventcore.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class PreventBlockItemDrops implements Listener {

    public static boolean enabled = false;

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        if (enabled) {
            e.setDropItems(false);
        }
    }
}
