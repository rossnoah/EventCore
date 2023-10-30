package dev.noah.eventcore.listener;

import dev.noah.eventcore.util.SchedulerUtils;
import dev.noah.eventcore.EventCore;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class AntiVoidDamage implements Listener {


    @EventHandler
    public void onDamage(EntityDamageEvent e){
        if(e.getEntity() instanceof Player){
            Player p = (Player) e.getEntity();
            if(e.getCause()== EntityDamageEvent.DamageCause.VOID){
                SchedulerUtils.runLater(()->{
                            e.getEntity().teleport(e.getEntity().getLocation().add(0,100,0));
                            p.sendActionBar(MiniMessage.miniMessage().deserialize(EventCore.red+"You have been saved from the void!"));

                        },1);


            }
        }






    }
}
