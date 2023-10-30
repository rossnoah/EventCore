package dev.noah.eventcore.commands;

import dev.noah.eventcore.util.KitUtil;
import dev.noah.eventcore.EventCore;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Loadkit implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player){
            if (args.length>0) {
                if (args[0].equalsIgnoreCase("*")) {
                    KitUtil.kitAll();
                    sender.sendMessage(MiniMessage.miniMessage().deserialize(EventCore.green+"Loading a kit for each player!"));
                    return true;
                }else if(Bukkit.getPlayer(args[0])!=null){
                    KitUtil.givekit(Bukkit.getPlayer(args[0]));
                    Bukkit.getPlayer(args[0]).sendMessage(MiniMessage.miniMessage().deserialize(EventCore.green+"Kit loaded!"));
                    sender.sendMessage(MiniMessage.miniMessage().deserialize(EventCore.green+"Kit loaded for "+args[0]+""));
                    return true;
                }
            }
            Player p= (Player) sender;
            KitUtil.givekit(p);
            p.sendMessage(MiniMessage.miniMessage().deserialize(EventCore.green+"Kit loaded!"));
        }else{
            sender.sendMessage(MiniMessage.miniMessage().deserialize(EventCore.red+"Error, you must be a player to use this command!"));
        }
        return true;
    }
}
