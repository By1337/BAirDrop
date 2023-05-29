package org.by1337.bairdrop.LocationGenerator;

import org.bukkit.Location;
import org.by1337.bairdrop.AirDrop;
import org.jetbrains.annotations.NotNull;

public interface Generator {
    /**
     * Вернёт заранее сгенерированную локацию
     * @param airDrop АирДроп для которого необходимо получить локацию
     * @return Заранее сгенерирована локация
     * @see GeneratorLoc
     * @see CGenLoc
     */
    Location getPreLocation(@NotNull AirDrop airDrop);

    /**
     * Основной метод генерации локаций
     * @param airDrop АирДроп которому необходимо сгенерировать локацию
     * @param isGenerator Если это генератор генерирует локации, то лога не будет
     * @return Сгенерированая локация
     */
    Location getLocation(@NotNull AirDrop airDrop, boolean isGenerator);

    /**
     * Проверяет ровность локаций
     * @param location локация где необходимо проверить ровность
     * @param airDrop аирдроп настройки которого будут использоваться
     * @return вернёт true если локация ровна иначе false
     */
    boolean checkForEvenness(@NotNull Location location, AirDrop airDrop);

}
