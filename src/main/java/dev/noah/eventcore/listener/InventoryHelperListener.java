package dev.noah.eventcore.listener;

import dev.noah.eventcore.EventCore;
import dev.noah.eventcore.util.PlayerUtils;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryHelperListener implements Listener {

    @EventHandler
    public void onDrop(PlayerDropItemEvent e){
        if(e.getItemDrop().getItemStack().getType().name().contains("NETHERITE")) {

            if(!(PlayerUtils.hasBypass(e.getPlayer()))){
                e.setCancelled(true);
                e.getPlayer().sendMessage(MiniMessage.miniMessage().deserialize(EventCore.red+"You cant drop that item!"));
            }
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e){
        for(ItemStack i: e.getDrops()){
            if((i.getType().name().contains("NETHERITE"))||i.getType().name().contains("CROSSBOW")){
                i.setAmount(0);
            }
        }
    }



}
