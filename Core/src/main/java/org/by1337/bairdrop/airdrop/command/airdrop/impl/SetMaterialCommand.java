package org.by1337.bairdrop.airdrop.command.airdrop.impl;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.block.data.Directional;
import org.bukkit.block.data.type.RespawnAnchor;
import org.bukkit.entity.Player;
import org.by1337.api.world.BlockPosition;
import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.BAirDrop;
import org.by1337.bairdrop.airdrop.command.airdrop.CommandExecutor;
import org.by1337.bairdrop.lang.Resource;
import org.by1337.bairdrop.location.GeneratorUtils;
import org.by1337.bairdrop.util.Message;
import org.by1337.lib.AsyncCatcher;
import org.by1337.lib.command.Command;
import org.by1337.lib.command.CommandException;
import org.by1337.lib.command.argument.ArgumentEnumValue;
import org.by1337.lib.command.argument.ArgumentSetList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

public class SetMaterialCommand implements CommandExecutor{
    private final Resource IS_NOT_BLOCK = new Resource("command.is-not-block"); // the material must be a block! Command %s
   @Override
    public String getCommandPrefix() { //old [SET_MATERIAL_<material>]-offsets
        return "[SET_MATERIAL]";
    }

    @Override
    public void execute(@Nullable AirDrop airDrop, @Nullable Player player, @NotNull String command) throws CommandException {
        AsyncCatcher.catchOp(String.format(ASYNC_CATCHER_ERROR.getString(), getCommandPrefix()));
        Objects.requireNonNull(airDrop, String.format(AIRDROP_IS_NULL.getString(), command));
        Objects.requireNonNull(airDrop.getAnyLoc(), String.format(LOCATION_IS_NULL.getString(), command));

        createCommand().executor(((sender, args) -> {
            Material material = (Material) args.getOrThrow("material", USAGE.getString(), usage());
            boolean offsets = args.containsKey("offsets");

            if (!material.isBlock()){
                throw new CommandException(IS_NOT_BLOCK.getString(), command);
            }

            Block block;

            if (offsets){
                BlockPosition blockPosition = airDrop.getGeneratorSetting().offsets;
                block = airDrop.getAnyLoc().add(blockPosition.x, blockPosition.y, blockPosition.z).getBlock();
            }else {
                block = airDrop.getAnyLoc().getBlock();
            }
            block.setType(material);

        })).process(null, parseCommand(command));

    }

    @Override
    public String usage() {
        return "[SET_MATERIAL] <material> <?offsets>";
    }

    @Override
    public Command createCommand() {
        return new Command(getCommandPrefix())
                .argument(new ArgumentEnumValue("material", Material.class))
                .argument(new ArgumentSetList("offsets", List.of("offsets", "true")));
    }
}