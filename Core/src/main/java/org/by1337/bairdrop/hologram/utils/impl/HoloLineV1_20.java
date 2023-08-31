package org.by1337.bairdrop.hologram.utils.impl;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import org.by1337.bairdrop.hologram.utils.HoloLine;

public class HoloLineV1_20 extends HoloLineV1_19_4 implements HoloLine {
    public HoloLineV1_20(String name, Location location) {
        super(name, location);
    }
    @Override
    public void spawn() {
        super.spawn();
    }

    @Override
    public void spawn(Player player) {
        super.spawn(player);
    }

    @Override
    public void remove() {
        super.remove();
    }

    @Override
    public void updateName(String name) {
        super.updateName(name);
    }
}
