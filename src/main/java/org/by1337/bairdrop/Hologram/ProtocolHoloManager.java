package org.by1337.bairdrop.Hologram;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.by1337.bairdrop.Hologram.utils.IProtocolHolo;
import org.by1337.bairdrop.Hologram.utils.impl.*;
import org.by1337.bairdrop.util.Message;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;

public class ProtocolHoloManager implements IHologram{
    private static HashMap<String, IProtocolHolo> holograms = new HashMap<>();
    @Override
    public void createOrUpdateHologram(@NotNull List<String> lines, @NotNull Location location, @NotNull String name) {
        if(holograms.containsKey(name)){
            holograms.get(name).remove();
            holograms.remove(name);
        }
        IProtocolHolo holo;
        if(Bukkit.getServer().getBukkitVersion().contains("1.16")){
            holo = new ProtocolHoloV1_16(lines, location);
        }else if(Bukkit.getServer().getBukkitVersion().contains("1.18")){
            holo = new ProtocolHoloV1_18(lines, location);
        }else if(Bukkit.getServer().getBukkitVersion().contains("1.19.4")){//1.19.4-R0.1-SNAPSHOT
            holo = new ProtocolHoloV1_19_4(lines, location);
        }else if(Bukkit.getServer().getBukkitVersion().contains("1.19.2")){//1.19.4-R0.1-SNAPSHOT
            holo = new ProtocolHoloV1_19_2(lines, location);
        }else if(Bukkit.getServer().getBukkitVersion().contains("1.17")){// 1.17.1-R0.1-SNAPSHOT
            holo = new ProtocolHoloV1_17(lines, location);
        }else {

            Message.error("Unsupported version");
            return;
        }
        holo.spawn();
        holograms.put(name, holo);
    }

    @Override
    public void remove(@NotNull String name) {
        if(holograms.containsKey(name)){
            holograms.get(name).remove();
            holograms.remove(name);
        }
    }
}
