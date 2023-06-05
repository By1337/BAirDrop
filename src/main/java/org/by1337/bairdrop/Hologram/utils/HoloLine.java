package org.by1337.bairdrop.Hologram.utils;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public interface HoloLine extends Listener {
    void spawn();
    void spawn(Player player);
    void remove();
    void updateName(String name);
}
