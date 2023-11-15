package org.by1337.bairdrop.configuration.adapter;

import org.by1337.api.configuration.YamlContext;
import org.by1337.api.configuration.adapter.AdapterRegistry;
import org.by1337.api.configuration.adapter.PrimitiveAdapter;
import org.by1337.bairdrop.observer.event.CustomEvent;
import org.by1337.api.util.NameKey;

public class AdapterCustomEvent implements PrimitiveAdapter<CustomEvent> {
    @Override
    public Object serialize(CustomEvent obj ) {
        return AdapterRegistry.serialize(obj.getName());
    }

    @Override
    public CustomEvent deserialize(Object src) {
        return CustomEvent.getByKey(AdapterRegistry.getAs(src, NameKey.class));
    }
}
