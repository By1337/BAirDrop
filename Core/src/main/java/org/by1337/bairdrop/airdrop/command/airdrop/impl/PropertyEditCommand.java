package org.by1337.bairdrop.airdrop.command.airdrop.impl;

import org.bukkit.entity.Player;
import org.by1337.bairdrop.airdrop.Airdrop;
import org.by1337.bairdrop.airdrop.command.airdrop.CommandExecutor;
import org.by1337.bairdrop.menu.property.property.Property;
import org.by1337.api.command.Command;
import org.by1337.api.command.CommandException;
import org.by1337.api.command.argument.ArgumentString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class PropertyEditCommand implements CommandExecutor {

    public String getCommandPrefix() {
        return "[PROPERTY]";
    }

    public void execute(@Nullable Airdrop airDrop, @Nullable Player player, @NotNull String command) throws CommandException {
        Objects.requireNonNull(airDrop, String.format(AIRDROP_IS_NULL.getString(), command));
        execute(airDrop, command);
    }

    public <T> void execute(@NotNull Airdrop airDrop, @NotNull String command) throws CommandException {

        new Command(getCommandPrefix())
                .addSubCommand(new Command("set")
                        .argument(new ArgumentString("property"))
                        .argument(new ArgumentString("value"))
                        .executor(((sender, args) -> {
                            Property<T> property = (Property<T>) airDrop.getProperty((String) args.getOrThrow("property", USAGE.getString(), usage()));
                            String val = (String) args.getOrThrow("value", USAGE.getString(), usage());
                            T value = property.parse(val);
                            property.setValue(value);
                        }))
                )
                .addSubCommand(new Command("reset")
                        .argument(new ArgumentString("property"))
                        .executor(((sender, args) -> {
                            Property<T> property = (Property<T>) airDrop.getProperty((String) args.getOrThrow("property", USAGE.getString(), usage()));
                            property.setValue(property.getDefValue());
                        }))
                )
                .process(null, parseCommand(command));
    }



    @Override
    public String usage() {
        return "[PROPERTY] set <property> <new value>";
    }

    @Override
    public Command createCommand() {
        return null;
    }

    @Override
    public void testCommand(@NotNull String command) throws CommandException {

    }
}