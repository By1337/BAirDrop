package org.by1337.bairdrop.hologram;

import org.bukkit.Location;
import org.by1337.bairdrop.BAirDrop;

import org.by1337.bairdrop.util.Message;
import org.jetbrains.annotations.NotNull;

import java.util.List;
/**
 * If the required libraries for working with holograms are missing, this class will be used instead
 **/

public class EmptyHologram implements IHologram{
    @Override
    public void createOrUpdateHologram(@NotNull List<String> lines, @NotNull Location location, @NotNull String name) {
        Message.error(BAirDrop.getConfigMessage().getMessage("holo-update-error"));
    }

    @Override
    public void remove(@NotNull String name) {
        Message.error(BAirDrop.getConfigMessage().getMessage("holo-remove-error"));
    }
}
