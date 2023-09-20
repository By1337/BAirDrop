package org.by1337.bairdrop.airdrop.command.airdrop.impl;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.airdrop.command.airdrop.CommandExecutor;
import org.by1337.bairdrop.observer.CustomEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class CallListenerCommand implements CommandExecutor {
    @Override
    public String getCommandPrefix() {
        return "[CALL-";
    }

    @Override
    public void execute(@Nullable AirDrop airDrop, @Nullable Player player, @NotNull String command) {
        String str = command.replace("[CALL-", "").replace("]", "");
        Objects.requireNonNull(airDrop, "airdrop is null! " + command).invokeListener(NamespacedKey.fromString(str), player, CustomEvent.NONE);
    }
}