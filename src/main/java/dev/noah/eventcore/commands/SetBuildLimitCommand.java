package dev.noah.eventcore.commands;

import dev.noah.eventcore.EventCore;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SetBuildLimitCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

    if(args.length!=1){

        sender.sendMessage(MiniMessage.miniMessage().deserialize(EventCore.red+"Usage: /"+label+" <number>"));
        return true;
    }

    int limit = Integer.parseInt(args[0]);

    EventCore.buildLimit = limit;


      sender.sendMessage(MiniMessage.miniMessage().deserialize(EventCore.green+"Build limit set to "+limit+"!"));

        return true;
    }
}
