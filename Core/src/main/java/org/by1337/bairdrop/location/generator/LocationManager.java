package org.by1337.bairdrop.location.generator;

import org.bukkit.Location;
import org.bukkit.World;
import org.by1337.bairdrop.location.generator.impl.OverworldLocationGenerator;
import org.jetbrains.annotations.Nullable;

public class LocationManager {
    private final GeneratorSetting setting;
    private final LocationGenerator overworld;
    private final World world;

    public LocationManager(GeneratorSetting setting, World world) {
        this.setting = setting;
        overworld = new OverworldLocationGenerator(setting, world);
        this.world = world;
    }

    @Nullable
    public Location generate() {
        return switch (world.getEnvironment()) {
            case NORMAL -> overworld.generate();
            case CUSTOM -> null;
            case NETHER -> null;
            case THE_END -> null;
        };
    }
}
