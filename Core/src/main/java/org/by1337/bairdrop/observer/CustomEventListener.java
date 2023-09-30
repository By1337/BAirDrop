package org.by1337.bairdrop.observer;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.BAirDrop;
import org.by1337.bairdrop.airdrop.registry.AirDropCommandRegistry;
import org.by1337.bairdrop.observer.observer.Observer;
import org.by1337.bairdrop.observer.requirement.Requirements;
import org.by1337.bairdrop.util.ExecuteCommands;
import org.by1337.bairdrop.util.Message;
import org.by1337.lib.command.CommandException;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A custom event listener that observes specific events and triggers commands based on certain conditions.
 */
public class CustomEventListener implements Observer {
    private final CustomEvent customEvent;
    private final String description;
    private final String[] commands;
    private final String[] denyCommands;
    private final Requirements requirements;
    private final NamespacedKey key;

    /**
     * Constructs a new CustomEventListener.
     *
     * @param customEvent  The custom event to observe.
     * @param description  A description of this listener.
     * @param commands     An array of commands to execute when conditions are met.
     * @param denyCommands An array of commands to execute when conditions are not met.
     * @param requirements The requirements that must be satisfied for this listener to trigger.
     * @param key          A unique key associated with this listener.
     */
    public CustomEventListener(CustomEvent customEvent, String description, String[] commands, String[] denyCommands, Requirements requirements, NamespacedKey key) {
        this.customEvent = customEvent;
        this.description = description;
        this.commands = commands;
        this.denyCommands = denyCommands;
        this.requirements = requirements;
        this.key = key;
    }

    /**
     * Updates the listener in response to events.
     *
     * @param pl          The player associated with the event (can be null).
     * @param airDrop     The AirDrop object associated with the event (can be null).
     * @param customEvent The custom event being observed.
     * @param ignoreEvent Indicates whether to ignore the event. If set to true, the listener
     *                    will trigger regardless of the event type.
     */

    @Override
    public void update(@Nullable Player pl, @Nullable AirDrop airDrop, CustomEvent customEvent, boolean ignoreEvent) {
        if (!customEvent.equals(this.customEvent) && !ignoreEvent)
            return;
        if (Arrays.stream(commands).toList().contains("[SCHEDULER]") || Arrays.stream(denyCommands).toList().contains("[SCHEDULER]")) {
            getRunnable(pl, airDrop, customEvent).runTaskLater(BAirDrop.getInstance(), getLaterTime());
            return;
        }
        if (Arrays.stream(commands).toList().contains("[ASYNC]") || Arrays.stream(denyCommands).toList().contains("[ASYNC]")) {
            getRunnable(pl, airDrop, customEvent).runTaskLaterAsynchronously(BAirDrop.getInstance(), getLaterTime());
            return;
        }

        ExecuteCommands executeCommands = new ExecuteCommands();
        if (checkRequirement(airDrop, pl)) {
            executeCommands.runListenerCommands(commands, pl, airDrop, customEvent);
        } else {
            executeCommands.runListenerCommands(denyCommands, pl, airDrop, customEvent);
        }
    }

    /**
     * Creates a BukkitRunnable for event processing in a separate thread.
     *
     * @param pl          The player associated with the event (can be null).
     * @param airDrop     The AirDrop object associated with the event (can be null).
     * @param customEvent The custom event being observed.
     * @return A BukkitRunnable for event processing.
     */
    private BukkitRunnable getRunnable(@Nullable Player pl, @Nullable AirDrop airDrop, CustomEvent customEvent) {
        return new BukkitRunnable() {
            @Override
            public void run() {
                ExecuteCommands executeCommands = new ExecuteCommands();
                if (checkRequirement(airDrop, pl)) {
                    executeCommands.runListenerCommands(commands, pl, airDrop, customEvent);
                } else {
                    executeCommands.runListenerCommands(denyCommands, pl, airDrop, customEvent);
                }
            }
        };
    }

    /**
     * Retrieves the delay time for execution, if specified in the commands.
     *
     * @return The delay time in ticks, or 0 if not specified.
     */
    private int getLaterTime() {
        List<String> list = new ArrayList<>();
        list.add(Arrays.toString(commands));
        list.add(Arrays.toString(denyCommands));
        Pattern laterPattern = Pattern.compile("\\[LATER-(\\d+)]");
        for (String str : list) {
            Matcher matcher = laterPattern.matcher(str);
            if (matcher.find()) {
                try {
                    return Integer.parseInt(matcher.group(1));
                } catch (NumberFormatException e) {
                    Message.error(e.getLocalizedMessage());
                }
            }
        }
        return 0;
    }

    /**
     * Checks all conditions to determine if the listener should trigger.
     *
     * @param airDrop The AirDrop object associated with the event (can be null).
     * @param pl      The player associated with the event (can be null).
     * @return true if all requirements are met, otherwise false.
     */
    private boolean checkRequirement(@Nullable AirDrop airDrop, @Nullable Player pl) {
        return requirements.check(airDrop, pl);
    }

    @Override
    public CustomEvent getEvent() {
        return customEvent;
    }

    @Override
    public String[] getCommands() {
        return commands;
    }

    @Override
    public String[] getDenyCommands() {
        return denyCommands;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void validateCommands() throws CommandException {
        for (String command : commands) {
            AirDropCommandRegistry.validate(command);
        }
        for (String denyCommand : denyCommands) {
            AirDropCommandRegistry.validate(denyCommand);
        }
    }

    @NotNull
    @Override
    public NamespacedKey getKey() {
        return key;
    }

    public static class CustomEventListenerBuilder {
        private CustomEvent customEvent;
        private String description;
        private String[] commands;
        private String[] denyCommands;
        private Requirements requirements;
        private NamespacedKey key;
        public CustomEventListenerBuilder customEvent(CustomEvent customEvent) {
            this.customEvent = customEvent;
            return this;
        }

        public CustomEventListenerBuilder description(String description) {
            this.description = description;
            return this;
        }

        public CustomEventListenerBuilder commands(String[] commands) {
            this.commands = commands;
            return this;
        }

        public CustomEventListenerBuilder denyCommands(String[] denyCommands) {
            this.denyCommands = denyCommands;
            return this;
        }

        public CustomEventListenerBuilder requirements(Requirements requirements) {
            this.requirements = requirements;
            return this;
        }

        public CustomEventListenerBuilder key(NamespacedKey key) {
            this.key = key;
            return this;
        }

        public CustomEventListener build() {
            return new CustomEventListener(this.customEvent, this.description, this.commands, this.denyCommands, this.requirements, this.key);
        }

    }
}
