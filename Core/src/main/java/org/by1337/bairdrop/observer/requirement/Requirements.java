package org.by1337.bairdrop.observer.requirement;

import org.bukkit.entity.Player;
import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.airdrop.Airdrop;
import org.by1337.bairdrop.observer.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * A class representing a set of requirements for checking specific conditions.
 */
public class Requirements {
    private List<Requirement> requirements;

    public Requirements() {
    }

    public Requirements(List<Requirement> requirements) {
        this.requirements = requirements;
    }

    /**
     * Sets an immutable list of requirements if not already set.
     *
     * @param requirements An array of requirements to set.
     * @throws IllegalStateException If requirements are already set.
     */
    public void set(Requirement... requirements) {
        if (this.requirements == null) {
            this.requirements = Arrays.stream(requirements).toList();
        } else {
            throw new IllegalStateException("Requirements are already set and cannot be modified.");
        }
    }

    /**
     * Sets an immutable list of requirements if not already set.
     *
     * @param requirements An array of requirements to set.
     * @throws IllegalStateException If requirements are already set.
     */
    public void set(List<Requirement> requirements) {
        if (this.requirements == null) {
            this.requirements = Collections.unmodifiableList(requirements);
        } else {
            throw new IllegalStateException("Requirements are already set and cannot be modified.");
        }
    }


    public boolean check(@NotNull Event event) {
        for (Requirement requirement : requirements) {
            if (!requirement.check(event)) {
                return false;
            }
        }
        return true;
    }

    public List<Requirement> getRequirements() {
        return requirements;
    }

    @Override
    public String toString() {
        return "Requirements{" +
                "requirements=" + requirements +
                '}';
    }
}
