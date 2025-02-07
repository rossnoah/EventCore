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
