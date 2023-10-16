package org.by1337.bairdrop.airdrop;

import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.by1337.api.chat.Placeholderable;
import org.by1337.bairdrop.location.generator.GeneratorSetting;
import org.by1337.bairdrop.menu.property.property.Property;
import org.by1337.bairdrop.observer.CustomEvent;
import org.by1337.bairdrop.observer.observer.Observable;
import org.jetbrains.annotations.Nullable;

import java.util.logging.Logger;

public interface Airdrop extends Placeholderable, Observable {
    @Deprecated //(forRemoval = true)
    boolean canSpawn();
    void tick();
    @Deprecated //(forRemoval = true)
    int spawnChance();
    @Deprecated //(forRemoval = true)
    boolean isSpawned();
    Logger logger();
    void notifyObservers(CustomEvent customEvent, @Nullable Player pl);
    Property<?> getProperty(String name);
    @Deprecated
    Location getAnyLoc();
    @Deprecated //(forRemoval = true)
    void loadEffect(String name, String id);
    @Deprecated //(forRemoval = true)
    void startEffect(String id);
    @Deprecated //(forRemoval = true)
    boolean isEffectStarted(String id);
    @Deprecated //(forRemoval = true)
    void stopEffect(String id);
    @Deprecated //(forRemoval = true)
    void stopAllEffectsAndClear();
    void invokeListener(NamespacedKey listener, @Nullable Player player, CustomEvent customEvent);
    void callEvent(@Nullable Player player, CustomEvent customEvent);
    GeneratorSetting getGeneratorSetting();

}
