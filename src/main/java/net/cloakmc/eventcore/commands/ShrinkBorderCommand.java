package net.cloakmc.eventcore.commands;

import net.cloakmc.eventcore.EventCore;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;

public class ShrinkBorderCommand implements CommandExecutor {
    @Override
    public boolean onCommand( CommandSender sender,  Command command,  String label,  String[] args) {
        if (sender instanceof Player) {

            if(args.length>2||args.length<1){
                sender.sendMessage(MiniMessage.miniMessage().deserialize(EventCore.red+"Error, usage:/shrink <distance> [delay]"));
                return true;
            }

                int time = 10;
                int distance = Integer.parseInt(args[0])*2;

                if(args.length>=2){
                    time = Integer.parseInt(args[1]);
                }

                if (Bukkit.getWorld("world") != null) {
                    World w = Bukkit.getWorld("world");
                    WorldBorder border = w.getWorldBorder();
                    if (border.getSize() > distance) {

                        DecimalFormat df = new DecimalFormat("#.#");

                        for (Player p : Bukkit.getOnlinePlayers()) {
                            p.sendMessage(MiniMessage.miniMessage().deserialize(EventCore.red+"<bold>Border is now shrinking! ["+df.format((double)distance/2/time)+" blocks/s]"));
                        }
                        sender.sendMessage(MiniMessage.miniMessage().deserialize("Old Radius: "+(int)border.getSize()/2));
                        border.setSize(border.getSize() - distance, time);
                        sender.sendMessage(MiniMessage.miniMessage().deserialize(EventCore.green+"New Radius: "+(int)(border.getSize()-distance)/2));

                    }else{
                        sender.sendMessage(MiniMessage.miniMessage().deserialize(EventCore.red+"Border too small to shrink"));
                    }
                }
        }
        return true;
    }
}

