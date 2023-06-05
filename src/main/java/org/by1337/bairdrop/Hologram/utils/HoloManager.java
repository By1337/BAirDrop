package org.by1337.bairdrop.Hologram.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.by1337.bairdrop.Hologram.utils.impl.*;
import org.by1337.bairdrop.util.Message;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class HoloManager {
    private List<HoloLine> lines = new ArrayList<>();
    private final String name;
    private Location location;

    public HoloManager(Location location, List<String> lines, String name) {
        this.location = location.clone();
        this.location.add(0, -2, 0);
        this.name = name;
        double offset = 0D;
        for (String str : lines) {
            create(str, this.location.clone().add(0, -offset, 0));
            offset += 0.3D;
        }
    }

    public void remove() {
        lines.forEach(HoloLine::remove);
        lines.clear();
    }

    public void setLines(List<String> newLines) {
        Iterator<HoloLine> holos = lines.listIterator();

        double offset = 0D;
        for (String str : newLines) {
            if (holos.hasNext()) {
                HoloLine holoLine = holos.next();
                holoLine.updateName(str);
            } else {
                create(str, location.clone().add(0, -offset, 0));
            }
            offset += 0.3D;
        }
        while (holos.hasNext()) {
            HoloLine holoLine = holos.next();
            holoLine.remove();
            holos.remove();
        }
    }

    private void create(String line, Location location) {
        HoloLine holoLine;
        if (Bukkit.getServer().getBukkitVersion().contains("1.16")) {
            holoLine = new HoloLineV1_16(line, location);
        } else if (Bukkit.getServer().getBukkitVersion().contains("1.18")) {
            holoLine = new HoloLineV1_18(line, location);
        }else if (Bukkit.getServer().getBukkitVersion().contains("1.17")) {
            holoLine = new HoloLineV1_17(line, location);
        }else if (Bukkit.getServer().getBukkitVersion().contains("1.19.2")) {
            holoLine = new HoloLineV1_19_2(line, location);
        }else if (Bukkit.getServer().getBukkitVersion().contains("1.19.4")) {
            holoLine = new HoloLineV1_19_4(line, location);
        } else {
            Message.error("unsupported version");
            return;
        }
        holoLine.spawn();
        lines.add(holoLine);
    }
    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

}
