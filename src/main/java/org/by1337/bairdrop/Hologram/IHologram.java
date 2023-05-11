package org.by1337.bairdrop.Hologram;

import org.bukkit.Location;
import org.by1337.bairdrop.obfuscate.DontObfuscate;
import org.jetbrains.annotations.NotNull;

import java.util.List;
@DontObfuscate
public interface IHologram {
    void createOrUpdateHologram(@NotNull List<String> lines, @NotNull Location location, @NotNull String name);
    void remove(@NotNull String name);
}
