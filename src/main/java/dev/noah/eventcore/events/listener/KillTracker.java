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
package dev.noah.eventcore.events.listener;

import dev.noah.eventcore.EventCore;
import dev.noah.eventcore.events.CustomKillEvent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class KillTracker implements Listener {

    // Track bed/anchor explosions (block location string -> player UUID)
    private final Map<String, UUID> explosionCauses = new ConcurrentHashMap<>();

    // Track when explosions were registered (for cleanup)
    private final Map<String, Long> explosionTimestamps = new ConcurrentHashMap<>();

    // Cleanup old explosion entries after this time (30 seconds in milliseconds)
    private static final long EXPLOSION_TRACKING_TIMEOUT = 30000;

    private final EventCore plugin;

    public KillTracker(EventCore plugin) {
        this.plugin = plugin;
        // Schedule cleanup task to run every 30 seconds
        Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, this::cleanupOldExplosions, 600L, 600L);
    }

    private void cleanupOldExplosions() {
        long currentTime = System.currentTimeMillis();
        explosionTimestamps.entrySet().removeIf(entry -> {
            if (currentTime - entry.getValue() > EXPLOSION_TRACKING_TIMEOUT) {
                explosionCauses.remove(entry.getKey());
                return true;
            }
            return false;
        });
    }

    // Track who triggered a bed or respawn anchor
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) return;

        Block block = event.getClickedBlock();
        if (block == null) return;

        Material material = block.getType();

        // Check if it's a bed or respawn anchor
        boolean isBed = material.name().contains("_BED");
        boolean isAnchor = material == Material.RESPAWN_ANCHOR;

        if (isBed || isAnchor) {
            // Only track in dimensions where they explode
            boolean willExplode = false;

            if (isBed && (block.getWorld().getEnvironment().name().contains("NETHER") ||
                    block.getWorld().getEnvironment().name().contains("END"))) {
                willExplode = true;
            }

            if (isAnchor && !block.getWorld().getEnvironment().name().contains("NETHER")) {
                willExplode = true;
            }

            if (willExplode) {
                String blockKey = blockLocationToString(block);
                explosionCauses.put(blockKey, event.getPlayer().getUniqueId());
                explosionTimestamps.put(blockKey, System.currentTimeMillis());
            }
        }
    }

    // Handle player deaths directly
    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player victim = event.getEntity();

        // Check if it's a normal player kill
        Player killer = victim.getKiller();
        if (killer != null) {
            // Fire our custom event for normal kills
            CustomKillEvent customKillEvent = new CustomKillEvent(
                    victim,
                    killer,
                    victim.getLastDamageCause() != null ? victim.getLastDamageCause().getCause() : DamageCause.CUSTOM,
                    "NORMAL"
            );
            Bukkit.getPluginManager().callEvent(customKillEvent);
        }
        // Check if it's an explosion death
        else if (victim.getLastDamageCause() instanceof EntityDamageByBlockEvent) {
            EntityDamageByBlockEvent blockDamage = (EntityDamageByBlockEvent) victim.getLastDamageCause();

            if (blockDamage.getCause() == DamageCause.BLOCK_EXPLOSION && blockDamage.getDamager() != null) {
                Block block = blockDamage.getDamager();
                String blockKey = blockLocationToString(block);
                UUID killerUUID = explosionCauses.get(blockKey);

                if (killerUUID != null) {
                    Player explosionCauser = Bukkit.getPlayer(killerUUID);
                    if (explosionCauser != null) {
                        // Determine explosion source
                        String killType = "UNKNOWN";
                        Material blockType = block.getType();
                        if (blockType.name().contains("_BED")) {
                            killType = "BED";
                        } else if (blockType == Material.RESPAWN_ANCHOR) {
                            killType = "RESPAWN_ANCHOR";
                        }

                        // Fire our custom event
                        CustomKillEvent customKillEvent = new CustomKillEvent(
                                victim,
                                explosionCauser,
                                DamageCause.BLOCK_EXPLOSION,
                                killType
                        );
                        Bukkit.getPluginManager().callEvent(customKillEvent);
                    }
                }
            }
        }
    }

    private String blockLocationToString(Block block) {
        return block.getWorld().getName() + "," +
                block.getX() + "," +
                block.getY() + "," +
                block.getZ();
    }
}