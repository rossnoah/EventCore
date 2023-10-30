package dev.noah.eventcore.commands;

import dev.noah.eventcore.util.SchedulerUtils;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ClearSpawn implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(MiniMessage.miniMessage().deserialize("Only players can use this command"));
            return true;
        }

        Player p = (Player) sender;
        sender.sendMessage(MiniMessage.miniMessage().deserialize("<green>Clearing spawn arena!"));

        SchedulerUtils.runLater(() -> {
            String run = "rg select spawn";
            Bukkit.dispatchCommand(sender, run);
            sender.sendMessage(MiniMessage.miniMessage().deserialize("<green>Ran command #1"));
        }, 20);

        SchedulerUtils.runLater(() -> {
            String run = "/set air";
            Bukkit.dispatchCommand(sender, run);
            sender.sendMessage(MiniMessage.miniMessage().deserialize("<green>Ran command #2"));
        }, 40);

        SchedulerUtils.runLater(() -> {
            String run = "rg remove spawn";
            Bukkit.dispatchCommand(sender, run);
            sender.sendMessage(MiniMessage.miniMessage().deserialize("<green>Ran command #3"));
            sender.sendMessage(MiniMessage.miniMessage().deserialize("<green><bold>Spawn Blocks and Region Removed!"));
        }, 160);

        return true;
    }
}
