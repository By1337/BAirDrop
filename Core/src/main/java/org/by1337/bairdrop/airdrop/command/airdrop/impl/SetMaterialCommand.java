package org.by1337.bairdrop.airdrop.command.airdrop.impl;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.data.type.RespawnAnchor;
import org.bukkit.entity.Player;
import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.BAirDrop;
import org.by1337.bairdrop.airdrop.command.airdrop.CommandExecutor;
import org.by1337.bairdrop.locationGenerator.GeneratorUtils;
import org.by1337.bairdrop.util.Message;
import org.by1337.lib.AsyncCatcher;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class SetMaterialCommand implements CommandExecutor {
    @Override
    public String getCommandPrefix() {
        return "[SET_MATERIAL_";
    }

    @Override
    public void execute(@Nullable AirDrop airDrop, @Nullable Player player, @NotNull String command) {
        AsyncCatcher.catchOp("Asynchronous set material!");
        Objects.requireNonNull(airDrop, "AirDrop is null!");
        Location location = airDrop.getAnyLoc();
        Objects.requireNonNull(location, String.format(BAirDrop.getConfigMessage().getMessage("loc-is-null2"), command));

        boolean subtractOffsets = false;
        if (command.contains("-offsets")) {
            subtractOffsets = true;
            command = command.replace("-offsets", "");
        }
        location = location.clone();
        try {
            Material mat = Material.valueOf(command.replace("]", "").replace("[SET_MATERIAL_", "").replace(" ", ""));
            if (subtractOffsets)
                location.add(
                        -GeneratorUtils.getSettings(airDrop.getGeneratorSettings(), String.format("%s.offsets.x", GeneratorUtils.getWorldKeyByWorld(location.getWorld()))),
                        -GeneratorUtils.getSettings(airDrop.getGeneratorSettings(), String.format("%s.offsets.y", GeneratorUtils.getWorldKeyByWorld(location.getWorld()))),
                        -GeneratorUtils.getSettings(airDrop.getGeneratorSettings(), String.format("%s.offsets.z", GeneratorUtils.getWorldKeyByWorld(location.getWorld())))).add(0,
                        1
                        , 0);
            location.getBlock().setType(mat);
            if (mat == Material.RESPAWN_ANCHOR) {
                RespawnAnchor ra = (RespawnAnchor) location.getBlock().getBlockData();
                ra.setCharges(4);
                location.getBlock().setBlockData(ra);
            }
        } catch (IllegalArgumentException e) {
            Message.error(String.format(BAirDrop.getConfigMessage().getMessage("unknown-material"), "?", command));
            Message.warning(e.getLocalizedMessage());

        }
    }
}