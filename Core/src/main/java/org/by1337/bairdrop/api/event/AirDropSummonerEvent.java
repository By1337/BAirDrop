package org.by1337.bairdrop.api.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import org.by1337.bairdrop.AirDrop;


import org.jetbrains.annotations.NotNull;


public class AirDropSummonerEvent extends Event implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled;
    private final AirDrop airDrop;
    private final Player player;

    public AirDropSummonerEvent(AirDrop airDrop, Player player) {
        this.airDrop = airDrop;
        this.player = player;
    }

    public AirDrop getAirDrop() {
        return airDrop;
    }

    public Player getPlayer() {
        return player;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return handlers;
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
    public static HandlerList getHandlerList() {
        return handlers;
    }
}
