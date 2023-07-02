package org.by1337.bairdrop.summoner;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.by1337.bairdrop.AirDrop;
import org.jetbrains.annotations.Nullable;

public interface SummonerItem {
    /**
     * @return будет ли аирдроп использовать локацию игрока
     */
    boolean isUsePlayerLocation();

    /**
     * @return предмет призыва аирдропа
     */
    ItemStack getItem();

    /**
     *
     * @return id аирдропа который необходимо призвать
     * также может вернуть "RANDOM"
     */
    String getSummonerAirDropId();

    /**
     * @return Будет ли призванный аирдрп клоном
     */
    boolean isClone();

    /**
     * @return будет ли аирдроп игнорировать проверку на регион
     */
    boolean isIgnoreRegion();

    /**
     * @return будет ли предмет призыва проверять ровность локации
     */
    boolean isFlatnessCheck();

    /**
     * @return ли предмет призыва проверять отсутствие блоков с верху
     */
    boolean isCheckUpBlocks();

    /**
     * @param location локация куда призывается аирдроп
     * @param pl игрок который призывает
     * @return если аирдроп успешно призывается то вернётся он иначе null
     */
    @Nullable
    AirDrop getAirDrop(Location location, Player pl);
}
