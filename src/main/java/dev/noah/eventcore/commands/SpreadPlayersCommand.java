package dev.noah.eventcore.commands;

import dev.noah.eventcore.util.SchedulerUtils;
import dev.noah.eventcore.EventCore;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;

public class SpreadPlayersCommand implements CommandExecutor {
    public static HashMap<UUID,Boolean> teleported = new HashMap<>();
    public static int tpcount = 0;



    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player){
            Player executor = (Player) sender;
            World w = executor.getWorld();
            WorldBorder worldBorder = w.getWorldBorder();
            Location center = worldBorder.getCenter();

                Teleport(center,w,executor);


        }else{
            sender.sendMessage("Players must run this command!");
        }

        return true;
    }

    public void executeTP(Location center,World w,Player executor){
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (tpcount < 20) {
                if (teleported.get(p.getUniqueId() == null || teleported.get(p.getUniqueId()) == false)) {
                    tpcount++;
                    teleported.put(p.getUniqueId(), true);
                    Location tp = center.add((Math.random() * 100) + 100, 100, (Math.random() * 100) + 100);
                    tp = w.getHighestBlockAt(tp).getLocation().add(0, 2, 0);
                    p.teleport(tp);

                }

            }
        }
        tpcount = 0;
    }



    public void Teleport(Location center,World w,Player executor) {
        Collection<? extends Player> onlinePlayers = Bukkit.getOnlinePlayers();

        SchedulerUtils.runAsync(() -> {
            double i = 0;

            for (Player player : onlinePlayers) {

                int negx = 1;
                int negz = 1;
                if(Math.random()>0.5){
                     negx = -1;
                }
                if(Math.random()>0.5){
                    negz = -1;
                }
         //       if(!EventCore.hasBypass(player)){

                int seed = Math.abs(player.getLocation().getBlockX());
                   /* double x = ((player.getName().hashCode()*(74+seed))%100);
                    int y = 100;
                    double z = ((player.getName().hashCode()*(33+seed))%100);
                    z+=100;
                    z*=negz;
                    x+=100;
                    x*=negx;
                    double finalx = Math.abs(x);
                    double finalz = Math.abs(z);


                    */
                int y = 100;

                int finalx = negx* (int)(100+(Math.random()*100));
                int finalz = negz* (int)(100+(Math.random()*100));






                    SchedulerUtils.runLater(() -> {
                       // Location tp = w.getHighestBlockAt(center.add(new Location(Bukkit.getWorld("world"), finalx, y, finalz))).getLocation().add(0,2,0);
                        Location tp = w.getHighestBlockAt(finalx+center.getBlockX(),finalz+center.getBlockZ()).getLocation().add(0,2,0);
                        player.teleport(tp);
                     //   executor.sendMessage("x:"+finalx+ " z:"+finalz);
                    }, 1 + (int)i);

                //}
                i=i+0.5;
            }
            SchedulerUtils.runSync(()->{
                executor.sendMessage(MiniMessage.miniMessage().deserialize(EventCore.green+"All players have been teleported!"));
            });
        });
    }


}



