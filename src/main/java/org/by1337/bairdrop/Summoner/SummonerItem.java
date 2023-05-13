package org.by1337.bairdrop.Summoner;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.by1337.bairdrop.AirDrop;
import org.jetbrains.annotations.Nullable;

public interface SummonerItem {
    boolean isUsePlayerLocation();
    ItemStack getItem();
    String getSummonerAirDropId();
    public boolean isClone();
    boolean isIgnoreRegion();
    boolean isFlatnessCheck();
    boolean isCheckUpBlocks();
    @Nullable
    AirDrop getAirDrop(Location location, Player pl);
}
