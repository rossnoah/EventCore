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

public class SetBorderCommand implements CommandExecutor {
    @Override
    public boolean onCommand( CommandSender sender,  Command command,  String label,  String[] args) {
        if (sender instanceof Player) {

            if(args.length<1){
                sender.sendMessage(MiniMessage.miniMessage().deserialize(EventCore.red+"Error, usage:/setborder <size>"));
                return true;
            }

                int radius = Integer.parseInt(args[0])*2;



                if (Bukkit.getWorld("world") != null) {
                    World w = Bukkit.getWorld("world");
                    WorldBorder border = w.getWorldBorder();


                        for (Player p : Bukkit.getOnlinePlayers()) {
                            p.sendMessage(MiniMessage.miniMessage().deserialize("<white><bold>Setting the world border to "+(radius/2)+" blocks"));
                        }
                        sender.sendMessage(MiniMessage.miniMessage().deserialize("Old Radius: "+border.getSize()/2));
                        border.setSize(radius);
                        sender.sendMessage(MiniMessage.miniMessage().deserialize(EventCore.green+"New Radius: "+(radius)/2));

                    }

        }
        return true;
    }
}

