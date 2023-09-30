package org.by1337.bairdrop.airdrop.command.airdrop.impl;

import org.bukkit.entity.Player;
import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.airdrop.command.airdrop.CommandExecutor;
import org.by1337.lib.command.Command;
import org.by1337.lib.command.CommandException;
import org.by1337.lib.command.argument.ArgumentString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class EffectStartCommand implements CommandExecutor{
    @Override
    public String getCommandPrefix() { // old [EFFECT_START-<NAME>-<id>]
        return "[EFFECT_START]";
    }

    public void execute(@Nullable AirDrop airDrop, @Nullable Player player, @NotNull String command) throws CommandException {
        Objects.requireNonNull(airDrop, String.format(AIRDROP_IS_NULL.getString(), command));
        createCommand().executor(((sender, args) -> {
            String name = (String) args.getOrThrow("name", USAGE.getString(), usage());
            String id = (String) args.getOrThrow("id", USAGE.getString(), usage());
            airDrop.loadEffect(name, id);
            airDrop.startEffect(id);
        })).process(null, parseCommand(command));
    }


    @Override
    public String usage() {
        return "[EFFECT_START] <name in config> <id>";
    }

    @Override
    public Command createCommand() {
        return new Command(getCommandPrefix())
                .argument(new ArgumentString("name"))
                .argument(new ArgumentString("id"));
    }
}
