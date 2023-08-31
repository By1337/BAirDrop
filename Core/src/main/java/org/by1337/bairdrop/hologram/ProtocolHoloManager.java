package org.by1337.bairdrop.hologram;


import org.bukkit.Location;
import org.by1337.bairdrop.hologram.utils.HoloManager;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;

public class ProtocolHoloManager implements IHologram{
    private static final HashMap<String, HoloManager> holograms = new HashMap<>();
    @Override
    public void createOrUpdateHologram(@NotNull List<String> lines, @NotNull Location location, @NotNull String name) {
        if(holograms.containsKey(name)){
            holograms.get(name).setLines(lines);

        }else {
            HoloManager holo = new HoloManager(location, lines, name);
            holograms.put(name, holo);
        }
    }

    @Override
    public void remove(@NotNull String name) {
        if(holograms.containsKey(name)){
            holograms.get(name).remove();
            holograms.remove(name);
        }
    }
}
