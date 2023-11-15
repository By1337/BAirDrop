package org.by1337.bairdrop.observer.requirement.impl;

import org.bukkit.entity.Player;
import org.by1337.api.chat.Placeholderable;
import org.by1337.bairdrop.BAirDrop;
import org.by1337.bairdrop.observer.event.Event;
import org.by1337.bairdrop.observer.requirement.*;

import org.by1337.bairdrop.util.OLDMessage;
import org.by1337.api.match.BMatch;
import org.jetbrains.annotations.Nullable;

public class RequirementStringCheck implements Requirement {
    private final String requirement;

    public RequirementStringCheck(String requirement) {
        this.requirement = requirement;
    }


    @Override
    public boolean check(Event event) {
        String req = event.getPlaceholderable().replace(requirement);

        String[] args = req.split(" ");
        if (args.length < 3){
            BAirDrop.MESSAGE.error("Not enough arguments to check!: %s", req);
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
            BAirDrop.MESSAGE.error("==, !=, contains or !contains, but not %s", req);
            return false;
        }
    }

    @Override
    public RequirementType getType() {
        return RequirementType.STRING_CHECK;
    }

    @Override
    public String getRequirement() {
        return requirement;
    }

    @Override
    public String toString() {
        return "RequirementStringCheck{" +
                "requirement='" + requirement + '\'' +
                '}';
    }
}
