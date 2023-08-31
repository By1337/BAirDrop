package org.by1337.bairdrop.hologram;

import eu.decentsoftware.holograms.api.DHAPI;
import eu.decentsoftware.holograms.api.holograms.Hologram;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class DecentHologram implements IHologram{
    private static boolean dhIsEnable = false;
    @Override
    public void createOrUpdateHologram(@NotNull List<String> lines, @NotNull Location location, @NotNull String name){
        if (!dhIsEnable){
            if (Bukkit.getPluginManager().getPlugin("DecentHolograms").isEnabled()){
                dhIsEnable = true;
            }else {
                return;
            }
        }
        Hologram hologram = DHAPI.getHologram(name);
        if(hologram != null){
            if(!hologram.getLocation().equals(location)){
                hologram.setLocation(location);
                hologram.realignLines();
            }
            DHAPI.setHologramLines(hologram, lines);
        }else {
            DHAPI.createHologram(name, location, lines);
        }
    }
    @Override
    public void remove(@NotNull String name){
        if (!dhIsEnable){
            if (Bukkit.getPluginManager().getPlugin("DecentHolograms").isEnabled()){
                dhIsEnable = true;
            }else {
                return;
            }
        }
        Hologram hologram = DHAPI.getHologram(name);
        if(hologram != null)
            DHAPI.removeHologram(name);
    }
}
