package org.by1337.bairdrop.airdrop.command.airdrop;

import org.bukkit.entity.Player;
import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.airdrop.Airdrop;
import org.by1337.bairdrop.lang.Resource;
import org.by1337.api.command.Command;
import org.by1337.api.command.CommandException;
import org.by1337.api.command.CommandSyntaxError;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

public interface CommandExecutor {
    Resource ASYNC_CATCHER_ERROR = new Resource("command.async"); //Asynchronous use of the %s command
    Resource AIRDROP_IS_NULL = new Resource("command.airdrop-is-null"); //Airdrop is null! command: %s
    Resource LOCATION_IS_NULL = new Resource("command.location-is-null"); //location is null! command: %s
    Resource USAGE = new Resource("command.usage"); //Usage: %s
    Resource PLAYER_IS_NULL = new Resource("command.player-is-null"); //Player is null! Command: %s

    String getCommandPrefix();
    void execute(@Nullable Airdrop airDrop, @Nullable Player player, @NotNull String command) throws CommandException;
    String usage();
    Command createCommand();

    default String[] parseCommand(@NotNull String command){
        String[] params = command.split(" ");
        return Arrays.copyOfRange(params, 1, params.length);
    }
    default void testCommand(@NotNull String command) throws CommandException {
        createCommand().process(null, parseCommand(command));
    }
}
