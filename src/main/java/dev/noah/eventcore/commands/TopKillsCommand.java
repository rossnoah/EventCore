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
import dev.noah.eventcore.EventCore;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;


public class TopKillsCommand implements CommandExecutor{

    static private List<Map.Entry<UUID, Integer>> top10;


    public void update(){
       top10 = EventCore.kills.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(10)
                .collect(Collectors.toList());

    }


    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {


        SchedulerUtils.runAsync(() -> {
            update();
            final HashMap<UUID, String> names = (HashMap<UUID, String>) EventCore.names.clone();
            SchedulerUtils.runSync(() -> {
                if(top10.size()==0){
                    commandSender.sendMessage(MiniMessage.miniMessage().deserialize(EventCore.red+"No kills have been recorded yet!"));
                    return;
                }
                commandSender.sendMessage(MiniMessage.miniMessage().deserialize(EventCore.cfg.getString("accent")+"Top 10 Kills:"));
                for (int i = 0; i < top10.size(); i++) {
                    commandSender.sendMessage(MiniMessage.miniMessage().deserialize(EventCore.cfg.getString("accent")+(i+1)+". <white>"+names.get(top10.get(i).getKey())+" <gray>- <white>"+top10.get(i).getValue()+" kills"));
                }
            });
        });






        return true;
    }
}
