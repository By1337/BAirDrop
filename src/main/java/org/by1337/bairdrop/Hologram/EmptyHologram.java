package org.by1337.bairdrop.Hologram;

import org.bukkit.Location;
import org.by1337.bairdrop.ConfigManager.Config;
import org.by1337.bairdrop.util.Message;
import org.jetbrains.annotations.NotNull;

import java.util.List;
/**
 * Если отсутствуют нужные библиотеки для работы с голограммами, то вместо них будет этот класс
**/
public class EmptyHologram implements IHologram{
    @Override
    public void createOrUpdateHologram(@NotNull List<String> lines, @NotNull Location location, @NotNull String name) {
        Message.error(Config.getMessage("holo-update-error"));
    }

    @Override
    public void remove(@NotNull String name) {
        Message.error(Config.getMessage("holo-remove-error"));
    }
}
