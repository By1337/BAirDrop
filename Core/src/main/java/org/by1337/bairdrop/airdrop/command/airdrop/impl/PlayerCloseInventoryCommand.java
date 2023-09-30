package org.by1337.bairdrop.airdrop.command.airdrop.impl;

import org.bukkit.entity.Player;
import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.airdrop.command.airdrop.CommandExecutor;
import org.by1337.lib.command.Command;
import org.by1337.lib.command.CommandException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class PlayerCloseInventoryCommand implements CommandExecutor {
    @Override
    public String getCommandPrefix() { // old [PLAYER-CLOSE-INVENTORY]
        return "[PLAYER_CLOSE_INVENTORY]";
    }

    @Override
    public void execute(@Nullable AirDrop airDrop, @Nullable Player player, @NotNull String command) throws CommandException {
        createCommand().executor(((sender, args) -> Objects.requireNonNull(player, PLAYER_IS_NULL.getString()).closeInventory())).process(null, parseCommand(command));
    }

    @Override
    public String usage() {
        return "[PLAYER_CLOSE_INVENTORY]";
    }

    @Override
    public Command createCommand() {
        return new Command(getCommandPrefix());
    }
}