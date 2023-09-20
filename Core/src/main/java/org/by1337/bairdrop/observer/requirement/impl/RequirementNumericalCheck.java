package org.by1337.bairdrop.observer.requirement.impl;

import org.bukkit.entity.Player;
import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.observer.requirement.*;
import org.by1337.bairdrop.util.Message;
import org.by1337.lib.match.BMatch;
import org.jetbrains.annotations.Nullable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RequirementNumericalCheck implements Requirement {
    private final String requirement;

    public RequirementNumericalCheck(String requirement) {
        this.requirement = requirement;
    }
    @Override
    public boolean check(@Nullable AirDrop airDrop, @Nullable Player player) {
       String req = requirement;
        if (airDrop != null)
            req = airDrop.replaceInternalPlaceholder(req);
        req = Message.setPlaceholders(player, req);
        req = BMatch.match(req);
        return BMatch.match(String.format("match[%s]", req)).equals("1");
    }

    @Override
    public RequirementType getType() {
        return RequirementType.NUMERICAL_CHECK;
    }
}
