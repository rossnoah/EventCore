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
package dev.noah.eventcore.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;


// Custom kill event exists to track handle kill tracking for
// respawn anchor and bed kills. KillTracker.java handles the tracking of kills.
public class CustomKillEvent extends Event {
    private static final HandlerList HANDLERS = new HandlerList();
    private final Player victim;
    private final Player killer;
    private final DamageCause cause;
    private final String killType; // "NORMAL", "BED", "RESPAWN_ANCHOR", etc.

    public CustomKillEvent(Player victim, Player killer, DamageCause cause, String killType) {
        this.victim = victim;
        this.killer = killer;
        this.cause = cause;
        this.killType = killType;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    public Player getVictim() {
        return victim;
    }

    public Player getKiller() {
        return killer;
    }

    public DamageCause getCause() {
        return cause;
    }

    public String getKillType() {
        return killType;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }
}
