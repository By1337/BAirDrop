package org.by1337.bairdrop.airdrop.command.impl;

import org.bukkit.entity.Player;
import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.airdrop.command.CommandExecutor;
import org.by1337.lib.AsyncCatcher;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class SchematicsRemoveCommand implements CommandExecutor {
    @Override
    public String getCommandPrefix() {
        return "[SCHEMATICS_REMOVE]";
    }

    @Override
    public void execute(@Nullable AirDrop airDrop, @Nullable Player player, @NotNull String command) {
        AsyncCatcher.catchOp("Asynchronous schematics remove!");
        Objects.requireNonNull(airDrop, "AirDrop is null!").schematicsUndo();
    }
}