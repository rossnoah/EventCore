package dev.noah.eventcore.commands;

import dev.noah.eventcore.EventCore;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class HelpCommand implements CommandExecutor {
    @Override
    public boolean onCommand( CommandSender sender,  Command command,  String label, String[] args) {


        MiniMessage miniMessage = MiniMessage.miniMessage();
        EventCore.cfg.getStringList("help").forEach(s -> {
            sender.sendMessage(miniMessage.deserialize(s));
        });


        return true;
    }
}
