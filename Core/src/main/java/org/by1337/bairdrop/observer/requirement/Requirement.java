package org.by1337.bairdrop.observer.requirement;


import org.bukkit.entity.Player;
import org.by1337.api.chat.Placeholderable;
import org.by1337.bairdrop.observer.event.Event;
import org.jetbrains.annotations.Nullable;

public interface Requirement {
    boolean check(Event event);

    RequirementType getType();

    String getRequirement();

}
