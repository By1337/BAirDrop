package org.by1337.bairdrop.hologram.utils;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public interface HoloLine extends Listener {
    void spawn();
    void spawn(Player player);
    void remove();
    void updateName(String name);
}
