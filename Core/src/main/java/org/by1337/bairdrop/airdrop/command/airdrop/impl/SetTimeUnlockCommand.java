package org.by1337.bairdrop.airdrop.command.airdrop.impl;

import org.bukkit.entity.Player;
import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.airdrop.Airdrop;
import org.by1337.bairdrop.airdrop.command.airdrop.CommandExecutor;
import org.by1337.bairdrop.util.Message;
import org.by1337.api.command.Command;
import org.by1337.api.command.CommandException;
import org.by1337.api.command.argument.ArgumentInteger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class SetTimeUnlockCommand implements CommandExecutor{
    @Override
    public String getCommandPrefix() {// old [SET_TIME_UNLOCK-90]
        return "[SET_TIME_UNLOCK]";
    }

    @Override // todo
    public void execute(@Nullable Airdrop airDrop, @Nullable Player player, @NotNull String command) throws CommandException {
        Objects.requireNonNull(airDrop, String.format(AIRDROP_IS_NULL.getString(), command));
        createCommand().executor(((sender, args) -> {
            int time = (int) args.getOrThrow("time", USAGE.getString(), usage());
          //  airDrop.setTimeToOpen(time);
        })).process(null, parseCommand(command));
    }

    @Override
    public String usage() {
        return "[SET_TIME_UNLOCK] <time>";
    }

    @Override
    public Command createCommand() {
        return new Command(getCommandPrefix())
                .argument(new ArgumentInteger("time"));
    }

}