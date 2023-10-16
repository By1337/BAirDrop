package org.by1337.bairdrop.util;


import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.BAirDrop;
import org.by1337.bairdrop.airdrop.Airdrop;
import org.by1337.bairdrop.airdrop.registry.AirDropCommandRegistry;
import org.by1337.bairdrop.lang.Resource;
import org.by1337.bairdrop.listeners.SetStaticLocation;
import org.by1337.bairdrop.listeners.util.ListenChat;
import org.by1337.bairdrop.menu.property.enums.EnumChooser;
import org.by1337.bairdrop.api.event.ExecuteCommandEvent;
import org.by1337.bairdrop.observer.CustomEvent;
import org.by1337.bairdrop.menu.*;
import org.by1337.bairdrop.menu.util.MenuItem;
import org.by1337.api.match.BMatch;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static org.bukkit.Bukkit.*;

public class ExecuteCommands {
    public void runListenerCommands(String[] commands, @Nullable Player pl, @Nullable Airdrop airDrop, CustomEvent customEvent) {
        ExecuteCommandEvent event = new ExecuteCommandEvent(airDrop, commands, pl, customEvent);
        Bukkit.getPluginManager().callEvent(event);
        if (event.isCancelled()) {
            return;
        }
        for (String command : commands) {
            if (airDrop != null)
                command = airDrop.replace(command);
            command = Message.setPlaceholders(pl, command);
            command = BMatch.match(command);


            AirDropCommandRegistry.execute(airDrop, pl, command);
        }
    }
}
