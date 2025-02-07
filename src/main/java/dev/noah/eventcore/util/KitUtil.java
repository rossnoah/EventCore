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
package dev.noah.eventcore.util;

import dev.noah.eventcore.EventCore;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class KitUtil {

    public static void givekit (Player p){

        p.getInventory().setContents(EventCore.kit);
    }

    public static void saveKit(ItemStack[] i){

        EventCore.kit = i;
        EventCore.saveData();

    }

    public static void kitAll() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            givekit(p);
            p.sendMessage(MiniMessage.miniMessage().deserialize(EventCore.green + "Kit loaded!"));
        }
    }

}
