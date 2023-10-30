package dev.noah.eventcore.commands;

import dev.noah.eventcore.EventCore;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import static dev.noah.eventcore.util.PlayerUtils.getAlive;

public class AliveCountCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        sender.sendMessage(MiniMessage.miniMessage().deserialize(EventCore.green+"There are <white>"+getAlive()+EventCore.green+" players left in the event!"));


        return true;
    }
}
