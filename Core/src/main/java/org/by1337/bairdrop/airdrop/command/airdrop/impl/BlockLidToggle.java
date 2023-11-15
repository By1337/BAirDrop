package org.by1337.bairdrop.airdrop.command.airdrop.impl;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Lidded;
import org.bukkit.entity.Player;
import org.by1337.api.BLib;
import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.airdrop.Airdrop;
import org.by1337.bairdrop.airdrop.command.airdrop.CommandExecutor;
import org.by1337.bairdrop.lang.Resource;

import org.by1337.api.command.Command;
import org.by1337.api.command.CommandException;
import org.by1337.api.command.argument.ArgumentSetList;
import org.by1337.bairdrop.observer.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class BlockLidToggle implements CommandExecutor {
    private final Resource LID_ERROR = new Resource("command.lid.cannot-be-opened-or-closed");// This block cannot be opened or closed! command: %s
    @Override
    public String getCommandPrefix() {
        return "[BLOCK_LID_TOGGLE]";
    }
    @Override
    public void execute(Event event, @NotNull String command)  throws CommandException {
        Airdrop airdrop = event.getAirdrop();
        BLib.catchOp(String.format(ASYNC_CATCHER_ERROR.getString(), getCommandPrefix()));
        Objects.requireNonNull(airdrop, String.format(AIRDROP_IS_NULL.getString(), command));
        Location location = airdrop.getAnyLoc();
        Objects.requireNonNull(location, String.format(LOCATION_IS_NULL.getString(), command));

        String[] params = command.split(" ");
        String[] args = Arrays.copyOfRange(params, 1, params.length);
        createCommand().executor(((sender, args1) -> {

            Block block = location.getBlock();
            String state = (String) args1.getOrThrow("state", USAGE.getString(), usage());

            BlockState blockState = block.getState();
            if (blockState instanceof Lidded lidded) {
                if (state.equals("CLOSE"))
                    lidded.close();
                else
                    lidded.open();
            } else {
                throw new CommandException(LID_ERROR.getString(), command);
            }

        })).process(null, args);
    }

    @Override
    public String usage() {
        return "[BLOCK_LID_TOGGLE] <CLOSE/OPEN>";
    }

    @Override
    public Command createCommand(){
        return new Command("[BLOCK_LID_TOGGLE]")
                .argument(new ArgumentSetList("state",List.of("CLOSE", "OPEN")));
    }
}