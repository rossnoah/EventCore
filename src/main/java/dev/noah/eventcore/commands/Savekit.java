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

import dev.noah.eventcore.util.KitUtil;
import dev.noah.eventcore.EventCore;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Savekit implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player){
            Player p= (Player) sender;
            KitUtil.saveKit(p.getInventory().getContents().clone());
            //EventCore.kit = p.getInventory().getContents();
            p.sendMessage(MiniMessage.miniMessage().deserialize(EventCore.green+"Kit Saved!"));
         }
        return true;
    }
}
