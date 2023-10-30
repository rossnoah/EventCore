package dev.noah.eventcore.hooks;

import dev.mrshawn.deathmessages.api.events.BroadcastDeathMessageEvent;
import dev.noah.eventcore.EventCore;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class DeathMessagesKillListener implements Listener {

        @EventHandler
    public void onKill(BroadcastDeathMessageEvent e) {
        if (e.getLivingEntity() != null) {
            if (e.getLivingEntity() instanceof Player) {
                Player killer = (Player) e.getLivingEntity();
                Player target = e.getPlayer();
                killer.sendMessage(MiniMessage.miniMessage().deserialize(EventCore.green+ "You killed<white> "+target.getName()+ EventCore.green +"!") );
            }
        }



    }


//    @EventHandler
//    public void onKill(PlayerDeathEvent e) {
////        for(Player p: Bukkit.getOnlinePlayers()){
////            p.sendMessage(MiniMessage.miniMessage().deserialize("debug: "+e.getLivingEntity().getName()+" killed: " +e.getPlayer().getName()) );
////        }
//        if (e.getEntity() != null) {
//            if (e.getEntity() instanceof Player) {
//                Player killer = (Player) e.getEntity();
//                Player target = e.getPlayer();
//                killer.sendMessage(MiniMessage.miniMessage().deserialize(EventCore.green+ "You killed<white> "+target.getName()+ EventCore.green +"a!") );
//            }
//        }
//
//
//
//    }

    @EventHandler
    public void onDeathMessageEvent(BroadcastDeathMessageEvent e){
        if(e.getLivingEntity()==null){
            return;
        }
        if(e.getLivingEntity().getType()== EntityType.PLAYER){

            Player killer = (Player)  e.getLivingEntity();

            EventCore.kills.compute(killer.getUniqueId(), (key, val)
                    -> (val == null)
                    ? 1
                    : val + 1);
        }
    }



//    @EventHandler
//    public void onDeathMessageEvent(PlayerDeathEvent e){
//        if(e.getEntity()==null){
//            return;
//        }
//        if(e.getEntity().getType()== EntityType.PLAYER){
//
//            Player killer = (Player)  e.getEntity();
//
//            EventCore.kills.compute(killer.getUniqueId(), (key, val)
//                    -> (val == null)
//                    ? 1
//                    : val + 1);
//        }
//    }
}
