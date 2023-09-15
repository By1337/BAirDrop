package org.by1337.api.network.protocol.game;

import org.bukkit.entity.Player;
import org.by1337.api.world.BLocation;

public interface Packet {
    void send(Player player);
    void sendAll(BLocation location, int radius);
}
