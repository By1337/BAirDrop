package org.by1337.bairdrop.airdrop;

import org.bukkit.command.ConsoleCommandSender;
import org.by1337.bairdrop.Placeholderable;

import java.util.logging.Logger;

public interface Airdrop extends Placeholderable {
    boolean canSpawn();
    void tick();
    int spawnChance();
    boolean isSpawned();
    Logger logger();
}
