package org.by1337.bairdrop.Hologram;

import eu.decentsoftware.holograms.api.DHAPI;
import eu.decentsoftware.holograms.api.holograms.Hologram;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class HologramManager {
//todo сделать по нормальному пжпжпжпжп
    public static void createHologram(@NotNull List<String> lines, @NotNull Location location, @NotNull String name){
     //   Message.logger("createHologram " + name);
        Hologram hologram = DHAPI.getHologram(name);
        if(hologram != null){
         //   Message.logger("(hologram != null " + name);
            if(!hologram.getLocation().equals(location)){
                hologram.delete();
                hologram = DHAPI.createHologram(name, location, lines);
            }
            DHAPI.setHologramLines(hologram, lines);
            DHAPI.moveHologram(hologram, location);
        }else {
          //  Message.logger("(hologram == null " + name);
            DHAPI.createHologram(name, location, lines);
        }
    }
    public static void remove(@NotNull String name){
        Hologram hologram = DHAPI.getHologram(name);
        if(hologram != null)
            DHAPI.removeHologram(name);
    }
}
