package org.by1337.bairdrop.airdrop;

public interface Airdrop {
    boolean canSpawn();
    void tick();
    int spawnChance();
    boolean isSpawned();
}
