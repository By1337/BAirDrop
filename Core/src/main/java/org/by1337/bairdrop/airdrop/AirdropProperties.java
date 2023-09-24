package org.by1337.bairdrop.airdrop;

import org.by1337.bairdrop.lang.Resource;
import org.by1337.bairdrop.menu.property.EditableProperties;
import org.by1337.bairdrop.menu.property.property.*;

public abstract class AirdropProperties extends EditableProperties {
    private final String id;

    protected AirdropProperties(String id) {
        this.id = id;

        initDefaultProperties();
    }

    private void initDefaultProperties() {
        registerProperty(new PropertyString(
                id,
                "id",
                null,
                null,
                null,
                this
        )).editable(false);
        registerProperty(new PropertyString(
                id,
                "super-name",
                null,
                null,
                null,
                this
        )).editable(false);
        registerProperty(new PropertyBoolean(
                false,
                "use-pre-generated-locations",
                new Resource("airdrop.properties.use-pre-generated-locations.lore"),
                new Resource("airdrop.properties.use-pre-generated-locations.material"),
                new Resource("airdrop.properties.use-pre-generated-locations.name"),
                this
        ));

        registerProperty(new PropertyInteger(
                0,
                "min-players-to-start",
                new Resource("airdrop.properties.min-players-to-start.lore"),
                new Resource("airdrop.properties.min-players-to-start.material"),
                new Resource("airdrop.properties.min-players-to-start.name"),
                this
        ));

        registerProperty(new PropertyString(
                "new air",
                "inventory-title",
                new Resource("airdrop.properties.inventory-title.lore"),
                new Resource("airdrop.properties.inventory-title.material"),
                new Resource("airdrop.properties.inventory-title.name"),
                this
        ));

        registerProperty(new PropertyString(
                "&dnew airdrop",
                "display-name",
                new Resource("airdrop.properties.display-name.lore"),
                new Resource("airdrop.properties.display-name.material"),
                new Resource("airdrop.properties.display-name.name"),
                this
        ));

        registerProperty(new PropertyInteger(
                54,
                "inventory-size",
                new Resource("airdrop.inventory-size.lore"),
                new Resource("airdrop.inventory-size.material"),
                new Resource("airdrop.inventory-size.name"),
                this
        ));
    }
}
