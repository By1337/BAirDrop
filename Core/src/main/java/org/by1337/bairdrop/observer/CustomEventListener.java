package org.by1337.bairdrop.observer;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.by1337.bairdrop.BAirDrop;
import org.by1337.bairdrop.airdrop.Airdrop;
import org.by1337.bairdrop.observer.event.CustomEvent;
import org.by1337.bairdrop.observer.event.Event;
import org.by1337.bairdrop.observer.observer.EditableObserver;
import org.by1337.bairdrop.observer.requirement.Requirements;
import org.by1337.bairdrop.observer.requirement.condition.Condition;
import org.by1337.bairdrop.util.ExecuteCommands;
import org.by1337.api.util.NameKey;
import org.by1337.bairdrop.util.OLDMessage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A custom event listener that observes specific events and triggers commands based on certain conditions.
 */
public class CustomEventListener implements EditableObserver {
    @NotNull
    private final CustomEvent customEvent;
    @Nullable
    private final String description;
    @Nullable
    private final String[] commands;
    @Nullable
    private final String[] denyCommands;
    @Nullable
    private final Requirements requirements;
    @Nullable
    private final Condition condition;
    @NotNull
    private final NameKey nameKey;

    private final boolean scheduler;
    private final boolean async;
    private final int later;

    public CustomEventListener(@NotNull CustomEvent customEvent, @Nullable String description,
                               @Nullable String[] commands, @Nullable String[] denyCommands,
                               @Nullable Requirements requirements, @Nullable Condition condition,
                               @NotNull NameKey nameKey) {
        this.customEvent = customEvent;
        this.description = description;
        this.commands = commands;
        this.denyCommands = denyCommands;
        this.requirements = requirements;
        this.condition = condition;
        this.nameKey = nameKey;

        scheduler = Arrays.stream(commands).toList().contains("[SCHEDULER]") || Arrays.stream(denyCommands).toList().contains("[SCHEDULER]");
        async = Arrays.stream(commands).toList().contains("[ASYNC]") || Arrays.stream(denyCommands).toList().contains("[ASYNC]");
        later = getLaterTime();
    }


    @Override
    public void update(Event event) {
        if (scheduler) {
            getRunnable(event).runTaskLater(BAirDrop.getInstance(), later);
            return;
        }
        if (async) {
            getRunnable(event).runTaskLaterAsynchronously(BAirDrop.getInstance(), later);
            return;
        }

        ExecuteCommands executeCommands = new ExecuteCommands();
        if (checkRequirement(event)) {
            executeCommands.runListenerCommands(event, commands);
        } else {
            executeCommands.runListenerCommands(event, denyCommands);
        }
        if (condition != null) {
            List<String> list = condition.getCommands(event);
            executeCommands.runListenerCommands(event, list.toArray(new String[0]));
        }
    }

    private BukkitRunnable getRunnable(Event event) {
        return new BukkitRunnable() {
            @Override
            public void run() {
                ExecuteCommands executeCommands = new ExecuteCommands();
                if (checkRequirement(event)) {
                    executeCommands.runListenerCommands(event, commands);
                } else {
                    executeCommands.runListenerCommands(event, denyCommands);
                }
                if (condition != null) {
                    List<String> list = condition.getCommands(event);
                    executeCommands.runListenerCommands(event, list.toArray(new String[0]));
                }
            }
        };
    }

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
                    OLDMessage.error(e.getLocalizedMessage());
                }
            }
        }
        return 0;
    }


    private boolean checkRequirement(@NotNull Event event) {
        if (requirements != null)
            return requirements.check(event);
        return true;
    }


    @Override
    public @NotNull CustomEvent customEvent() {
        return customEvent;
    }

    @Override
    public String[] commands() {
        return commands;
    }

    @Override
    public String[] denyCommands() {
        return denyCommands;
    }

    @Override
    public @NotNull String description() {
        return description;

    }

    public Requirements requirements() {
        return requirements;
    }

    public Condition condition() {
        return condition;
    }

    @Override
    public @NotNull NameKey getName() {
        return nameKey;
    }

    @Override
    public String toString() {
        return "CustomEventListener{" +
                "customEvent=" + customEvent +
                ", description='" + description + '\'' +
                ", commands=" + Arrays.toString(commands) +
                ", denyCommands=" + Arrays.toString(denyCommands) +
                ", requirements=" + requirements +
                ", condition=" + condition +
                ", nameKey=" + nameKey +
                '}';
    }
}
