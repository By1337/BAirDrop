package org.by1337.bairdrop.Hologram;

import eu.decentsoftware.holograms.api.DHAPI;
import eu.decentsoftware.holograms.api.holograms.Hologram;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class DecentHologram implements IHologram{
//todo сделать по нормальному пжпжпжпжп
    @Override
    public void createOrUpdateHologram(@NotNull List<String> lines, @NotNull Location location, @NotNull String name){
        Hologram hologram = DHAPI.getHologram(name);
        if(hologram != null){
            if(!hologram.getLocation().equals(location)){
                hologram.delete();
                hologram = DHAPI.createHologram(name, location, lines);
            }
            DHAPI.setHologramLines(hologram, lines);
            DHAPI.moveHologram(hologram, location);
        }else {
            DHAPI.createHologram(name, location, lines);
        }
    }
    @Override
    public void remove(@NotNull String name){
        Hologram hologram = DHAPI.getHologram(name);
        if(hologram != null)
            DHAPI.removeHologram(name);
    }
}
