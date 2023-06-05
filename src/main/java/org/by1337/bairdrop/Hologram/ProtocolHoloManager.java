package org.by1337.bairdrop.Hologram;

import org.bukkit.Location;
import org.by1337.bairdrop.Hologram.utils.ProtocolHolo;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;

public class ProtocolHoloManager implements IHologram{
    private static HashMap<String, ProtocolHolo> holograms = new HashMap<>();
    @Override
    public void createOrUpdateHologram(@NotNull List<String> lines, @NotNull Location location, @NotNull String name) {
        if(holograms.containsKey(name)){
            holograms.get(name).remove();
        }
        ProtocolHolo protocolHolo = new ProtocolHolo(lines, location);
        protocolHolo.spawn();
        holograms.put(name, protocolHolo);
    }

    @Override
    public void remove(@NotNull String name) {
        if(holograms.containsKey(name)){
            holograms.get(name).remove();
        }
    }
}
