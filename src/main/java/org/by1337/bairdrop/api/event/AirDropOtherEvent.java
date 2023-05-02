package org.by1337.bairdrop.api.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import org.by1337.bairdrop.AirDrop;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class AirDropOtherEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private final org.by1337.bairdrop.util.Event event;
    private final AirDrop airDrop;
    private final Player player;

    public AirDropOtherEvent(org.by1337.bairdrop.util.Event event, @Nullable AirDrop airDrop, @Nullable Player player) {
        this.event = event;
        this.airDrop = airDrop;
        this.player = player;
    }

    public org.by1337.bairdrop.util.Event getEvent() {
        return event;
    }

    @Nullable()
    public AirDrop getAirDrop() {
        return airDrop;
    }

    @Nullable
    public Player getPlayer() {
        return player;
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
