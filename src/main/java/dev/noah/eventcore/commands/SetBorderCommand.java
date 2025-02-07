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

