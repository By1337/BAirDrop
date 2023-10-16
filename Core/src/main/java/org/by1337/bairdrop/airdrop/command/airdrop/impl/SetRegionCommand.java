package org.by1337.bairdrop.airdrop.command.airdrop.impl;

import org.bukkit.entity.Player;
import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.BAirDrop;
import org.by1337.bairdrop.airdrop.Airdrop;
import org.by1337.bairdrop.airdrop.command.airdrop.CommandExecutor;
import org.by1337.bairdrop.worldGuardHook.RegionManager;
import org.by1337.api.command.Command;
import org.by1337.api.command.CommandException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class SetRegionCommand implements CommandExecutor{
    @Override
    public String getCommandPrefix() {
        return "[SET_REGION]";
    }

    @Override // todo
    public void execute(@Nullable Airdrop airDrop, @Nullable Player player, @NotNull String command) throws CommandException {
        Objects.requireNonNull(airDrop, String.format(AIRDROP_IS_NULL.getString(), command));
        Objects.requireNonNull(airDrop.getAnyLoc(), String.format(LOCATION_IS_NULL.getString(), command));

      //  createCommand().executor(((sender, args) -> airDrop.setRegion())).process(null, parseCommand(command));
    }
    @Override
    public String usage() {
        return "[SET_REGION]";
    }

    @Override
    public Command createCommand() {
        return new Command(getCommandPrefix());
    }
}