package org.by1337.bairdrop.airdrop.command.airdrop.impl;

import org.bukkit.entity.Player;
import org.by1337.api.BLib;
import org.by1337.bairdrop.airdrop.Airdrop;
import org.by1337.bairdrop.airdrop.command.airdrop.CommandExecutor;
import org.by1337.bairdrop.observer.event.CustomEvent;
import org.by1337.api.command.Command;
import org.by1337.api.command.CommandException;
import org.by1337.api.command.argument.ArgumentString;
import org.by1337.api.util.NameKey;
import org.by1337.bairdrop.observer.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class CallEvent implements CommandExecutor {
    @Override
    public String getCommandPrefix() {
        return "[CALL_EVENT]";
    }

    @Override
    public void execute(Event event, @NotNull String command) throws CommandException {
        Airdrop airdrop = event.getAirdrop();
        BLib.catchOp(String.format(ASYNC_CATCHER_ERROR.getString(), getCommandPrefix()));
        Objects.requireNonNull(airdrop, String.format(AIRDROP_IS_NULL.getString(), command));

        createCommand().executor(((sender, args) -> {
            String event1 = (String) args.getOrThrow("event", USAGE.getString(), usage());
            CustomEvent customEvent = CustomEvent.getByKey(new NameKey(event1.toLowerCase()));
            airdrop.callEvent(new Event(airdrop, event.getPlayer(), customEvent));
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
