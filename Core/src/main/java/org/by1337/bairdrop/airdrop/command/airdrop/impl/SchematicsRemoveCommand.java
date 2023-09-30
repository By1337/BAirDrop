package org.by1337.bairdrop.airdrop.command.airdrop.impl;

import org.bukkit.entity.Player;
import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.airdrop.command.airdrop.CommandExecutor;
import org.by1337.lib.AsyncCatcher;
import org.by1337.lib.command.Command;
import org.by1337.lib.command.CommandException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class SchematicsRemoveCommand implements CommandExecutor {
    @Override
    public String getCommandPrefix() {
        return "[SCHEMATICS_REMOVE]";
    }

    @Override
    public void execute(@Nullable AirDrop airDrop, @Nullable Player player, @NotNull String command) throws CommandException {
        AsyncCatcher.catchOp(String.format(ASYNC_CATCHER_ERROR.getString(), getCommandPrefix()));
        Objects.requireNonNull(airDrop, String.format(AIRDROP_IS_NULL.getString(), command));
        createCommand().executor(((sender, args) -> airDrop.schematicsUndo())).process(null, parseCommand(command));
    }

    @Override
    public String usage() {
        return "[SCHEMATICS_REMOVE]";
    }

    @Override
    public Command createCommand() {
        return new Command(getCommandPrefix());
    }
}