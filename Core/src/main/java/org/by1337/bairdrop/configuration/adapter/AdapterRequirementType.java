package org.by1337.bairdrop.configuration.adapter;

import org.by1337.api.configuration.YamlContext;
import org.by1337.api.configuration.adapter.AdapterRegistry;
import org.by1337.api.configuration.adapter.PrimitiveAdapter;
import org.by1337.bairdrop.observer.requirement.RequirementType;
import org.by1337.api.util.NameKey;

public class AdapterRequirementType implements PrimitiveAdapter<RequirementType> {
    @Override
    public Object serialize(RequirementType obj ) {
        return AdapterRegistry.serialize(obj.getName());
    }

    @Override
    public RequirementType deserialize(Object src) {
        return RequirementType.getByKey(AdapterRegistry.getAs(src, NameKey.class));
    }
}
