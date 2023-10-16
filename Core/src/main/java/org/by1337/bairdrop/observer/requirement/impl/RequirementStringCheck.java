package org.by1337.bairdrop.observer.requirement.impl;

import org.bukkit.entity.Player;
import org.by1337.api.chat.Placeholderable;
import org.by1337.bairdrop.observer.requirement.*;

import org.by1337.bairdrop.util.Message;
import org.by1337.api.match.BMatch;
import org.jetbrains.annotations.Nullable;

public class RequirementStringCheck implements Requirement {
    private final String requirement;

    public RequirementStringCheck(String requirement) {
        this.requirement = requirement;
    }

    @Override
    public boolean check(@Nullable Placeholderable placeholderable, @Nullable Player player) {
        String req = requirement;
        if (placeholderable != null)
            req = placeholderable.replace(req);
        req = Message.setPlaceholders(player, req);
        req = BMatch.match(req);
        String[] args = req.split(" ");
        if (args.length < 3){
            Message.error("Not enough arguments to check!: %s", req);
            return false;
        }
        if (args[1].equals("=="))
            return args[0].equals(args[2]);
        if (args[1].equals("!="))
            return !args[0].equals(args[2]);
        if (args[1].equals("contains"))
            return args[0].contains(args[2]);
        if (args[1].equals("!contains"))
            return !args[0].contains(args[2]);
        else {
            Message.error("==, !=, contains or !contains, but not %s", req);
            return false;
        }
    }

    @Override
    public RequirementType getType() {
        return RequirementType.STRING_CHECK;
    }
}
