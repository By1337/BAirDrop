package org.by1337.bairdrop.api.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import org.by1337.bairdrop.AirDrop;

import org.jetbrains.annotations.NotNull;

public class AirDropOpenEvent extends Event implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled;
    private final AirDrop airDrop;
    private final Player player;

    public AirDropOpenEvent(AirDrop airDrop, Player player) {
        this.airDrop = airDrop;
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public AirDrop getAirDrop() {
        return airDrop;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        cancelled = cancel;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    @NotNull
    public static HandlerList getHandlerList() {
        return handlers;
    }
}
