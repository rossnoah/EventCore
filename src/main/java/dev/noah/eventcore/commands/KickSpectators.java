package dev.noah.eventcore.commands;

import dev.noah.eventcore.EventCore;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class KickSpectators implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        int count = 0;
        for(Player p: Bukkit.getOnlinePlayers())
        {


            if(p.getGameMode()== GameMode.SPECTATOR) {

                if (!(p.hasPermission("event.bypass") ||
                        (p.hasPermission("event.staff")) ||
                        (p.hasPermission("event.admin")))) {


                    p.kickPlayer("Spectators have been removed from the event to prevent lag!");
                    count++;
                }
            }
        }
        sender.sendMessage(MiniMessage.miniMessage().deserialize(EventCore.green+"Kicked "+count+" spectators!"));
        sender.sendMessage("Enable the whitelist to prevent rejoining");

        return true;
    }
}
