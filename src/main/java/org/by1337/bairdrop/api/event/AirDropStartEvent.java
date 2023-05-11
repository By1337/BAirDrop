package org.by1337.bairdrop.api.event;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import org.by1337.bairdrop.AirDrop;

import org.by1337.bairdrop.obfuscate.DontObfuscate;
import org.jetbrains.annotations.NotNull;
@DontObfuscate
public class AirDropStartEvent extends Event implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled;
    private final AirDrop airDrop;

    public AirDropStartEvent(AirDrop airDrop) {
        this.airDrop = airDrop;
    }

    public AirDrop getAirDrop() {
        return airDrop;
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
