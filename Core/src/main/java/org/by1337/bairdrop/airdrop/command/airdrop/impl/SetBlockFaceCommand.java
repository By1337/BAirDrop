package org.by1337.bairdrop.airdrop.command.airdrop.impl;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.block.data.Directional;
import org.bukkit.entity.Player;
import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.BAirDrop;
import org.by1337.bairdrop.airdrop.command.airdrop.CommandExecutor;
import org.by1337.bairdrop.lang.Resource;
import org.by1337.bairdrop.util.Message;
import org.by1337.lib.AsyncCatcher;
import org.by1337.lib.command.Command;
import org.by1337.lib.command.CommandException;
import org.by1337.lib.command.argument.ArgumentEnumValue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SetBlockFaceCommand implements CommandExecutor{
    private final Resource ROTATE_ERR = new Resource("command.rotate-err");// This block cannot be rotated. Block %s
    @Override
    public String getCommandPrefix() { // old [SET_BLOCK_FACE_<enum>]
        return "[SET_BLOCK_FACE]";
    }

    @Override
    public void execute(@Nullable AirDrop airDrop, @Nullable Player player, @NotNull String command) throws CommandException {
        AsyncCatcher.catchOp(String.format(ASYNC_CATCHER_ERROR.getString(), getCommandPrefix()));
        Objects.requireNonNull(airDrop, String.format(AIRDROP_IS_NULL.getString(), command));
        Objects.requireNonNull(airDrop.getAnyLoc(), String.format(LOCATION_IS_NULL.getString(), command));

        createCommand().executor(((sender, args) -> {
            BlockFace blockFace = (BlockFace) args.getOrThrow("val", USAGE.getString(), usage());

            Block block = airDrop.getAnyLoc().getBlock();
            if (block.getBlockData() instanceof Directional directional) {
                BlockState state = block.getState();
                directional.setFacing(blockFace);
                state.setBlockData(directional);
            } else {
                Message.error(ROTATE_ERR.getString(), block.getType().name());
            }

        })).process(null, parseCommand(command));

    }

    @Override
    public String usage() {
        return "[SET_BLOCK_FACE] <value>";
    }

    @Override
    public Command createCommand() {
        return new Command(getCommandPrefix())
                .argument(new ArgumentEnumValue("val", BlockFace.class));
    }

}