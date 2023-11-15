package org.by1337.bairdrop.configuration.adapter;

import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.configuration.ConfigurationSection;
import org.by1337.api.world.BlockPosition;
import org.by1337.api.world.Vector2D;
import org.by1337.api.configuration.YamlContext;
import org.by1337.api.configuration.adapter.ClassAdapter;
import org.by1337.bairdrop.location.generator.GeneratorSetting;

import java.util.ArrayList;

public class AdapterGeneratorSetting implements ClassAdapter<GeneratorSetting> {
    @Override
    public ConfigurationSection serialize(GeneratorSetting obj, YamlContext context) {
        context.set("has-block", obj.hasBlock);
        context.set("has-no-block", obj.hasNoBlock);
        context.set("ignore-blocks", obj.ignoreBlocks);
        context.set("white-list-blocks", obj.whiteListBlocks);
        context.set("white-list-biomes", obj.whiteListBiomes);
        context.set("offsets", obj.offsets);
        context.set("center", obj.center);
        context.set("radius", obj.radius);
        context.set("max-y", obj.maxY);
        context.set("min-y", obj.minY);
        context.set("region-radius", obj.regionRadius);
        return context.getHandle();
    }

    @Override
    public GeneratorSetting deserialize(YamlContext context) {
        GeneratorSetting generatorSetting = new GeneratorSetting();
        generatorSetting.hasBlock = context.getList("has-block", BlockPosition.class, new ArrayList<>());
        generatorSetting.hasNoBlock = context.getList("has-no-block", BlockPosition.class, new ArrayList<>());
        generatorSetting.ignoreBlocks = context.getList("ignore-blocks", Material.class, new ArrayList<>());
        generatorSetting.whiteListBlocks = context.getList("white-list-blocks", Material.class, new ArrayList<>());
        generatorSetting.whiteListBiomes = context.getList("white-list-biomes", Biome.class, new ArrayList<>());
        generatorSetting.offsets = context.getAs("offsets", BlockPosition.class);
        generatorSetting.center = context.getAs("center", Vector2D.class);
        generatorSetting.radius = context.getAsInteger("radius");
        generatorSetting.maxY = context.getAsInteger("max-y");
        generatorSetting.minY = context.getAsInteger("min-y");
        generatorSetting.regionRadius = context.getAs("region-radius", BlockPosition.class);
        return generatorSetting;
    }
}
