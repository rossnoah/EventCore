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
package dev.noah.eventcore.hooks;

import me.clip.placeholderapi.PlaceholderAPI;
import dev.noah.eventcore.EventCore;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static dev.noah.eventcore.util.PlayerUtils.getAlive;

public class PlaceholderAPIExpansion extends PlaceholderExpansion {

    /**
     * This method should always return true unless we
     * have a dependency we need to make sure is on the server
     * for our placeholders to work!
     * This expansion does not require a dependency, so we will always return true
     */
    @Override
    public boolean canRegister() {
        return true;
    }

    /**
     * The name of the person who created this expansion should go here
     */
    @Override
    public String getAuthor() {
        return "CloakMC";
    }

    /**
     * The placeholder identifier should go here
     * This is what tells PlaceholderAPI to call our onPlaceholderRequest method to obtain
     * a value if a placeholder starts with our identifier.
     * This must be unique and can not contain % or _
     */
    @Override
    public String getIdentifier() {
        return "event";
    }


    /**
     * This is the version of this expansion
     */
    @Override
    public String getVersion() {
        return "1.0.0";
    }

    /**
     * This is the method called when a placeholder with our identifier is found and needs a value
     * We specify the value identifier in this method
     */
    @Override
    public String onPlaceholderRequest(Player p, String identifier) {

        // %example_placeholder1%
        if (identifier.equals("alive")) {
            return "" + getAlive();
        }
        if (identifier.equals("kills")) {
            if (EventCore.kills.get(p.getUniqueId()) == null) {
                return "0";
            } else {
                return "" + EventCore.kills.get(p.getUniqueId());
            }
        }

        if (identifier.equals("status")) {
            if (p.hasPermission("event.admin")) {
                return EventCore.red + "&lAdmin";
            }
            if (p.hasPermission("event.staff")) {

                return EventCore.cfg.getString("accent") + "&lStaff";

            }

            if (!EventCore.inGame) {
                return "&7Waiting";
            }

            if (p.getGameMode() == GameMode.SURVIVAL) {
                return EventCore.green + "&lAlive";
            }

            if (p.getGameMode() == GameMode.SPECTATOR) {
                return "&7Spectating";
            }

            return null;
        }

        if (identifier.equals("border")) {
            return "" + ((int) (p.getWorld().getWorldBorder().getSize() / 2));
        }

        if (identifier.equals("centerX")) {
            return "" + p.getWorld().getWorldBorder().getCenter().getBlockX();
        }
        if (identifier.equals("centerZ")) {
            return "" + p.getWorld().getWorldBorder().getCenter().getBlockZ();
        }

        if (identifier.equals("totems")) {
            int count = 0;
            for (ItemStack i : p.getInventory().getContents()) {
                if (i != null) {
                    if (i.getType() == Material.TOTEM_OF_UNDYING) {
                        count++;
                    }
                }
            }
            return "" + count;
        }

        if (identifier.equals("time")) {

            if (!EventCore.inGame) {
                return "Not Yet Started";
            } else if (EventCore.timeStarted <= 0) {
                return "Not Yet Started";
            } else {
                int minutes = (int) ((System.currentTimeMillis() - EventCore.timeStarted) / 1000) / 60;
                int hours = minutes / 60;
                minutes = minutes % 60;

                if (hours > 0) {
                    return hours + "h " + minutes + "m";
                } else {
                    return minutes + "m";
                }

            }

        }

        if (identifier.equalsIgnoreCase("ping")) {

            if (!p.isOnline()) {
                return "0";
            }
            return "" + (int) (p.getPlayer().getPing() * 0.8);
        }

        if (identifier.equalsIgnoreCase("tps")) {
//            parse %spark_tps_5m%
            String tps = "%spark_tps_5m%";
            tps = PlaceholderAPI.setPlaceholders(null, tps);

            tps = ChatColor.stripColor(tps);

            return tps;
        }

        if (identifier.equalsIgnoreCase("topkiller")) {

//        get highest value from EventCore.kills
            Optional<Map.Entry<UUID, Integer>> maxEntry = EventCore.kills.entrySet()
                    .stream()
                    .max(Map.Entry.comparingByValue());

            if (!maxEntry.isPresent()) {
                return "None";
            }

            Map.Entry<UUID, Integer> entry = maxEntry.get();
            String top = EventCore.names.get(entry.getKey());
            int highest = entry.getValue();

            return top + " (" + highest + ")";

        }


            return null;




    }

}




