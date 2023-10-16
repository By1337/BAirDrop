package org.by1337.bairdrop.airdrop.command.airdrop.impl;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.by1337.api.BLib;
import org.by1337.bairdrop.airdrop.Airdrop;
import org.by1337.bairdrop.airdrop.command.airdrop.CommandExecutor;
import org.by1337.bairdrop.lang.Resource;
import org.by1337.bairdrop.menu.property.property.PropertyLocation;
import org.by1337.api.command.Command;
import org.by1337.api.command.CommandException;
import org.by1337.api.command.argument.ArgumentEnumValue;
import org.by1337.api.command.argument.ArgumentSetList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

public class SetMaterialCommand implements CommandExecutor {
    private final Resource IS_NOT_BLOCK = new Resource("command.is-not-block"); // the material must be a block! Command %s

    @Override
    public String getCommandPrefix() { //old [SET_MATERIAL_<material>]-offsets
        return "[SET_MATERIAL]";
    }

    @Override
    public void execute(@Nullable Airdrop airDrop, @Nullable Player player, @NotNull String command) throws CommandException {
        BLib.catchOp(String.format(ASYNC_CATCHER_ERROR.getString(), getCommandPrefix()));
        Objects.requireNonNull(airDrop, String.format(AIRDROP_IS_NULL.getString(), command));
        Objects.requireNonNull(airDrop.getProperty("location").getValue(), String.format(LOCATION_IS_NULL.getString(), command));

        createCommand().executor(((sender, args) -> {
            Material material = (Material) args.getOrThrow("material", USAGE.getString(), usage());
            boolean offsets = args.containsKey("offsets");

            if (!material.isBlock()) {
                throw new CommandException(IS_NOT_BLOCK.getString(), command);
            }

            Block block;

//            if (offsets){// todo
//                BlockPosition blockPosition = airDrop.getGeneratorSetting().offsets;
//                block = airDrop.getAnyLoc().add(blockPosition.x, blockPosition.y, blockPosition.z).getBlock();
//            }else {
            PropertyLocation location = (PropertyLocation) airDrop.getProperty("location");
            block = location.getValue().getLocation().getBlock();
//            }
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