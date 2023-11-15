package org.by1337.bairdrop.observer.requirement.impl;

import org.bukkit.entity.Player;
import org.by1337.api.chat.Placeholderable;
import org.by1337.bairdrop.observer.event.Event;
import org.by1337.bairdrop.observer.requirement.Requirement;
import org.by1337.bairdrop.observer.requirement.RequirementType;
import org.jetbrains.annotations.Nullable;

public class RequirementHasPermission implements Requirement {
    private final String requirement;

    public RequirementHasPermission(String requirement) {
        this.requirement = requirement;
    }

    @Override
    public boolean check(Event event) {
        return event.getPlayer() == null || event.getPlayer().hasPermission(event.getPlaceholderable().replace(requirement));
    }

    @Override
    public RequirementType getType() {
        return RequirementType.HAS_PERMISSION;
    }

    @Override
    public String getRequirement() {
        return requirement;
    }

    @Override
    public String toString() {
        return "RequirementHasPermission{" +
                "requirement='" + requirement + '\'' +
                '}';
    }
}
