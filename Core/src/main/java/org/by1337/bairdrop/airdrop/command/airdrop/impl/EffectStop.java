package org.by1337.bairdrop.airdrop.command.airdrop.impl;

import org.bukkit.entity.Player;
import org.by1337.bairdrop.airdrop.Airdrop;
import org.by1337.bairdrop.airdrop.command.airdrop.CommandExecutor;
import org.by1337.api.command.Command;
import org.by1337.api.command.CommandException;
import org.by1337.api.command.argument.ArgumentString;
import org.by1337.bairdrop.observer.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class EffectStop implements CommandExecutor {
    @Override
    public String getCommandPrefix() { //old [EFFECT_STOP-<id>]
        return "[EFFECT_STOP]";
    }


    public void execute(Event event, @NotNull String command) throws CommandException {
        Airdrop airdrop = event.getAirdrop();
        Objects.requireNonNull(airdrop, String.format(AIRDROP_IS_NULL.getString(), command));
        createCommand().executor(((sender, args) -> {
            String id = (String) args.getOrThrow("id", USAGE.getString(), usage());
            airdrop.stopEffect(id);
        })).process(null, parseCommand(command));
    }


    @Override
    public String usage() {
        return "[EFFECT_STOP] <id>";
    }

    @Override
    public Command createCommand() {
        return new Command(getCommandPrefix())
                .argument(new ArgumentString("id"));
    }
}
