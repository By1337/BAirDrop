package org.by1337.bairdrop.configuration.adapter;

import org.by1337.api.configuration.adapter.PrimitiveAdapter;
import org.by1337.bairdrop.observer.requirement.condition.Condition;
import org.by1337.bairdrop.observer.requirement.condition.ConditionParse;

public class AdapterCondition implements PrimitiveAdapter<Condition> {
    @Override
    public Object serialize(Condition obj) {
        return obj.toString();
    }

    @Override
    public Condition deserialize(Object src) {
        return ConditionParse.parse(String.valueOf(src));
    }
}
