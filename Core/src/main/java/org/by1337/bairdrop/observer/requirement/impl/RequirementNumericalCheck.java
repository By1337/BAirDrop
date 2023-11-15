package org.by1337.bairdrop.observer.requirement.impl;

import org.bukkit.entity.Player;
import org.by1337.api.chat.Placeholderable;
import org.by1337.bairdrop.observer.event.Event;
import org.by1337.bairdrop.observer.requirement.*;
import org.by1337.api.match.BMatch;
import org.jetbrains.annotations.Nullable;

public class RequirementNumericalCheck implements Requirement {
    private final String requirement;

    public RequirementNumericalCheck(String requirement) {
        this.requirement = requirement;
    }
    @Override
    public boolean check(Event event) {
        return event.getPlaceholderable().replace(String.format("match[%s]", requirement)).equals("1");
    }

    @Override
    public RequirementType getType() {
        return RequirementType.NUMERICAL_CHECK;
    }

    @Override
    public String getRequirement() {
        return requirement;
    }

    @Override
    public String toString() {
        return "RequirementNumericalCheck{" +
                "requirement='" + requirement + '\'' +
                '}';
    }
}
