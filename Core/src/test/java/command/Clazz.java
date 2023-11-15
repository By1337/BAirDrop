package command;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.by1337.api.configuration.YamlContext;
import org.by1337.api.match.BMatch;
import org.by1337.bairdrop.observer.CustomEventListener;
import org.by1337.bairdrop.observer.CustomEventListenerBuilder;
import org.by1337.bairdrop.observer.event.CustomEvent;
import org.by1337.bairdrop.observer.event.Event;
import org.by1337.bairdrop.observer.requirement.Requirements;
import org.by1337.bairdrop.observer.requirement.impl.RequirementNumericalCheck;
import org.by1337.bairdrop.observer.requirement.impl.RequirementStringCheck;
import org.by1337.api.util.NameKey;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.*;

public class Clazz {

    @Test
    public void run1() throws InvalidConfigurationException, IOException {
     //   System.out.println(BMatch.match(String.format("match[%s]", "100 + 100")));

//        YamlContext context = new YamlContext(new YamlConfiguration());
//
//        context.set("test", Material.ACACIA_BOAT);
//
//        String ser = ((YamlConfiguration) context.getHandle()).saveToString();
//
//        YamlConfiguration yaml = new YamlConfiguration();
//        yaml.loadFromString(ser);
//
//        YamlContext context1 = new YamlContext(yaml);
//
//        Material material = context1.getAsMaterial("test");
//
//        System.out.println(material);
//
//        System.out.println(((YamlConfiguration) context1.getHandle()).saveToString());
    }

    @Test
    public void run() throws InvalidConfigurationException {

        CustomEventListener listener = new CustomEventListenerBuilder()
                .commands(List.of("cmd 1"))
                .condition(null)
                .customEvent(CustomEvent.START_EVENT)
                .denyCommands(List.of("cmd 2"))
                .description("description")
                .nameKey(new NameKey("listener"))
                .requirements(new Requirements(List.of(new RequirementStringCheck("qwerty == qwerty"), new RequirementNumericalCheck("100 == 100"))))
                .build();
        Assert.assertEquals(serialize(listener).toString(), listener.toString());
    }

    public Object serialize(Object o) throws InvalidConfigurationException {
        YamlConfiguration yml = new YamlConfiguration();
        YamlContext context = new YamlContext(yml);
        context.set("test", o);
        String ser = ((YamlConfiguration) context.getHandle()).saveToString();

        YamlConfiguration yml1 = new YamlConfiguration();
        yml1.loadFromString(ser);
        YamlContext context1 = new YamlContext(yml1);
        return context1.getAs("test", o.getClass());
    }


}

