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


import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;


public class SchedulerUtils {
    private static Plugin plugin = null;

    public static void runAsync(Runnable runnable) {
        Bukkit.getScheduler().runTaskAsynchronously(plugin, runnable);
    }

    public static void runRepeating(Runnable runnable, long l) {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, runnable, 0, l);
    }

    public static void runSync(Runnable runnable) {
        Bukkit.getScheduler().runTask(plugin, runnable);
    }

    public static void runLater(Runnable runnable, int x) {
        Bukkit.getScheduler().runTaskLater(plugin, runnable, x);
    }

    public static void setPlugin(Plugin plugin) {
        SchedulerUtils.plugin = plugin;
    }

}
