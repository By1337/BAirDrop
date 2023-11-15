package org.by1337.bairdrop.util;


import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.by1337.bairdrop.airdrop.Airdrop;
import org.by1337.bairdrop.airdrop.registry.AirDropCommandRegistry;
import org.by1337.bairdrop.api.event.ExecuteCommandEvent;
import org.by1337.bairdrop.observer.event.CustomEvent;
import org.by1337.api.match.BMatch;
import org.by1337.bairdrop.observer.event.Event;
import org.jetbrains.annotations.Nullable;

public class ExecuteCommands {
    public void runListenerCommands(Event event, String[] commands) {
        for (String command : commands) {
            AirDropCommandRegistry.execute(event, event.getPlaceholderable().replace(command));
        }
    }
}
