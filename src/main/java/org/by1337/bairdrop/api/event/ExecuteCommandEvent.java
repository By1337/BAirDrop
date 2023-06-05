package org.by1337.bairdrop.api.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.customListeners.CustomEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ExecuteCommandEvent extends Event implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled;
    private final AirDrop airDrop;
    private final String[] commands;
    private final Player player;
    private final CustomEvent customEvent;

    public ExecuteCommandEvent(AirDrop airDrop, String[] commands, Player player, CustomEvent customEvent) {
        this.airDrop = airDrop;
        this.commands = commands;
        this.player = player;
        this.customEvent = customEvent;
    }

    public String[] getCommands() {
        return commands;
    }
    @Nullable
    public Player getPlayer() {
        return player;
    }

    public CustomEvent getCustomEvent() {
        return customEvent;
    }
    @Nullable
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