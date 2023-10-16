package org.by1337.bairdrop.airdrop.command.airdrop.impl;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.by1337.api.BLib;
import org.by1337.bairdrop.airdrop.Airdrop;
import org.by1337.bairdrop.airdrop.command.airdrop.CommandExecutor;
import org.by1337.bairdrop.observer.CustomEvent;
import org.by1337.api.command.Command;
import org.by1337.api.command.CommandException;
import org.by1337.api.command.argument.ArgumentSetList;
import org.by1337.api.command.argument.ArgumentString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class CallEvent implements CommandExecutor {
    @Override
    public String getCommandPrefix() {
        return "[CALL_EVENT]";
    }

    @Override
    public void execute(@Nullable Airdrop airDrop, @Nullable Player player, @NotNull String command) throws CommandException {
        BLib.catchOp(String.format(ASYNC_CATCHER_ERROR.getString(), getCommandPrefix()));
        Objects.requireNonNull(airDrop, String.format(AIRDROP_IS_NULL.getString(), command));

        createCommand().executor(((sender, args) -> {
            String event = (String) args.getOrThrow("event", USAGE.getString(), usage());
            CustomEvent customEvent = CustomEvent.getByKey(NamespacedKey.fromString(event.toLowerCase()));
            airDrop.callEvent(player, customEvent);
        })).process(null, parseCommand(command));
    }

    @Override
    public String usage() {
        return "[CALL_EVENT] <event>";
    }

    @Override
    public Command createCommand() {
        return new Command(getCommandPrefix())
                .argument(new ArgumentString("event"));
    }
}
