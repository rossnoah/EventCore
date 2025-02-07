/*
 * Copyright 2023 Noah Ross
 *
 * This file is part of EventCore.
 *
 * EventCore is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Affero General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * EventCore is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for
 * more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with EventCore. If not, see <https://www.gnu.org/licenses/>.
 */
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
