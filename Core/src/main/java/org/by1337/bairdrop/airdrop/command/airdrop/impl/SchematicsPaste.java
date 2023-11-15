package org.by1337.bairdrop.airdrop.command.airdrop.impl;

import org.bukkit.entity.Player;
import org.by1337.api.BLib;
import org.by1337.bairdrop.airdrop.Airdrop;
import org.by1337.bairdrop.airdrop.command.airdrop.CommandExecutor;
import org.by1337.api.command.Command;
import org.by1337.api.command.CommandException;
import org.by1337.api.command.argument.ArgumentString;
import org.by1337.bairdrop.observer.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class SchematicsPaste implements CommandExecutor {
    @Override
    public String getCommandPrefix() { // old [SCHEMATICS_PASTE-<id>]
        return "[SCHEMATICS_PASTE]";
    }

    @Override // заглушка
    public void execute(Event event, @NotNull String command) throws CommandException {
        execute(event.getAirdrop(), event.getPlayer(), command);
    }

    public void execute(@Nullable Airdrop airDrop, @Nullable Player player, @NotNull String command) throws CommandException {
        BLib.catchOp(String.format(ASYNC_CATCHER_ERROR.getString(), getCommandPrefix()));
        Objects.requireNonNull(airDrop, AIRDROP_IS_NULL.getString());

        createCommand().executor(((sender, args) -> {
            String id = (String) args.getOrThrow("schem", USAGE.getString(), usage());
          //  airDrop.schematicsPaste(new CSchematicsManager(), id);
        })).process(null, parseCommand(command));
    }

    @Override
    public String usage() {
        return "[SCHEMATICS_PASTE] <id>";
    }

    @Override
    public Command createCommand() {
        return new Command(getCommandPrefix())
                .argument(new ArgumentString("schem"));
    }
}