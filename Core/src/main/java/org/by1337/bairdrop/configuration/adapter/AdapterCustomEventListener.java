package org.by1337.bairdrop.configuration.adapter;

import org.bukkit.configuration.ConfigurationSection;
import org.by1337.api.configuration.YamlContext;
import org.by1337.api.configuration.adapter.ClassAdapter;
import org.by1337.bairdrop.observer.requirement.condition.Condition;
import org.by1337.bairdrop.observer.CustomEventListener;
import org.by1337.bairdrop.observer.CustomEventListenerBuilder;
import org.by1337.bairdrop.observer.event.CustomEvent;
import org.by1337.bairdrop.observer.requirement.Requirement;
import org.by1337.bairdrop.observer.requirement.Requirements;
import org.by1337.api.util.NameKey;

import java.util.ArrayList;
import java.util.List;

public class AdapterCustomEventListener implements ClassAdapter<CustomEventListener> {
    @Override
    public ConfigurationSection serialize(CustomEventListener obj, YamlContext context) {
        context.set("event", obj.customEvent());
        context.set("name", obj.getName());
        context.set("description", obj.description());
        context.set("requirements", obj.requirements().getRequirements());
        context.set("commands", obj.commands());
        context.set("deny-commands", obj.denyCommands());
        context.set("condition", obj.condition());

        return context.getHandle();
    }

    @Override
    public CustomEventListener deserialize(YamlContext context) {
        CustomEvent customEvent = context.getAs("event", CustomEvent.class);
        String description = context.getAsString("description");
        List<String> commands = context.getList("commands", String.class, new ArrayList<>());
        List<String> denyCommands = context.getList("deny-commands", String.class, new ArrayList<>());
        List<Requirement> requirements = context.getList("requirements", Requirement.class, new ArrayList<>());
        Condition condition = context.getAs("condition", Condition.class, null);
        NameKey key = context.getAsNameKey("name");

        return new CustomEventListenerBuilder()
                .customEvent(customEvent)
                .description(description)
                .commands(commands)
                .denyCommands(denyCommands)
                .requirements(new Requirements(requirements))
                .condition(condition)
                .nameKey(key)
                .build();
    }
}
