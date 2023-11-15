package org.by1337.bairdrop.airdrop;

import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.by1337.api.chat.Placeholderable;
import org.by1337.api.property.StorageProperties;
import org.by1337.api.util.NameKey;
import org.by1337.bairdrop.location.generator.GeneratorSetting;
import org.by1337.bairdrop.observer.event.Event;
import org.by1337.bairdrop.observer.observer.Observable;
import org.by1337.bairdrop.observer.observer.Observer;
import org.by1337.api.property.property.Property;
import org.by1337.bairdrop.observer.event.CustomEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

public interface Airdrop extends Placeholderable, Observable {
    @Deprecated
        //(forRemoval = true)
    boolean canSpawn();

    void tick();

    @Deprecated
        //(forRemoval = true)
    int spawnChance();

    @Deprecated
        //(forRemoval = true)
    boolean isSpawned();

    Logger logger();

    @Deprecated
    Location getAnyLoc();

    @Deprecated
        //(forRemoval = true)
    void loadEffect(String name, String id);

    @Deprecated
        //(forRemoval = true)
    void startEffect(String id);

    @Deprecated
        //(forRemoval = true)
    boolean isEffectStarted(String id);

    @Deprecated
        //(forRemoval = true)
    void stopEffect(String id);

    @Deprecated
        //(forRemoval = true)
    void stopAllEffectsAndClear();


    GeneratorSetting getGeneratorSetting();

    Location getLocation();

    void setLocation(@Nullable Location location);

    StorageProperties getStorageProperties();

}
