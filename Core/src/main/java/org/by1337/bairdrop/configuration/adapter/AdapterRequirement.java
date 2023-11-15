package org.by1337.bairdrop.configuration.adapter;

import org.bukkit.configuration.ConfigurationSection;
import org.by1337.api.configuration.YamlContext;
import org.by1337.api.configuration.adapter.ClassAdapter;
import org.by1337.bairdrop.observer.requirement.Requirement;
import org.by1337.bairdrop.observer.requirement.RequirementType;

public class AdapterRequirement<T extends Requirement> implements ClassAdapter<T> {
    @Override
    public ConfigurationSection serialize(T obj, YamlContext context) {
        context.set("type", obj.getType());
        context.set("input", obj.getRequirement());
        return context.getHandle();
    }

    @Override
    public T deserialize(YamlContext context) {
        RequirementType type = context.getAs("type", RequirementType.class);
        String input = context.getAsString("input");
        return (T) type.supplier().get(input);
    }
}
