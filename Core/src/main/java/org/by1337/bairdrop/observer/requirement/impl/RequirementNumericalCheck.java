package org.by1337.bairdrop.observer.requirement.impl;

import org.bukkit.entity.Player;
import org.by1337.api.chat.Placeholderable;
import org.by1337.bairdrop.observer.requirement.*;
import org.by1337.api.match.BMatch;
import org.jetbrains.annotations.Nullable;

public class RequirementNumericalCheck implements Requirement {
    private final String requirement;

    public RequirementNumericalCheck(String requirement) {
        this.requirement = requirement;
    }
    @Override
    public boolean check(@Nullable Placeholderable placeholderable, @Nullable Player player) {
       String req = requirement;
        if (placeholderable != null)
            req = placeholderable.replace(req);
        return BMatch.match(String.format("match[%s]", req)).equals("1");
    }

    @Override
    public RequirementType getType() {
        return RequirementType.NUMERICAL_CHECK;
    }
}
