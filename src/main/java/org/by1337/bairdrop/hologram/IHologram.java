package org.by1337.bairdrop.hologram;

import org.bukkit.Location;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface IHologram {
    void createOrUpdateHologram(@NotNull List<String> lines, @NotNull Location location, @NotNull String name);
    void remove(@NotNull String name);
}
