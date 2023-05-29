package org.by1337.bairdrop.api.event;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.by1337.bairdrop.obfuscate.DontObfuscate;
import org.jetbrains.annotations.NotNull;

/**
 * Нужен для того чтобы аддоны могли вовремя реагировать на выключение плагина
 */
@DontObfuscate
public class DisableEvent extends Event {
    private static final HandlerList handlers = new HandlerList();

    @NotNull
    public static HandlerList getHandlerList() {
        return handlers;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
}
