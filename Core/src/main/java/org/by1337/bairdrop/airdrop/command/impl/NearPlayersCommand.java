package org.by1337.bairdrop.airdrop.command.impl;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.BAirDrop;
import org.by1337.bairdrop.airdrop.command.CommandExecutor;
import org.by1337.bairdrop.customListeners.CustomEvent;
import org.by1337.bairdrop.util.Message;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class NearPlayersCommand implements CommandExecutor {
    @Override
    public String getCommandPrefix() {
        return "[NEAR-PLAYERS=";
    }

    @Override
    public void execute(@Nullable AirDrop airDrop, @Nullable Player player1, @NotNull String command) {
        try {
            int range = Integer.parseInt(command.split("=")[1].split("]")[0]);

            for (Entity entity : Objects.requireNonNull(Objects.requireNonNull(airDrop, "airdrop is null! " + command).getAnyLoc(), "location is null! " + command).getWorld().getNearbyEntities(airDrop.getAnyLoc(), range, range, range)) {
                if (entity instanceof Player player) {
                    airDrop.invokeListener(NamespacedKey.fromString(command
                            .replace(String.format("[NEAR-PLAYERS=%s] {CALL-", range), "")
                            .replace("}", "")), player, CustomEvent.NONE);
                }
            }
        } catch (NumberFormatException e) {
            Message.error(String.format(BAirDrop.getConfigMessage().getMessage("near-error-1"), command));
        } catch (NullPointerException e) {
            Message.error(String.format(BAirDrop.getConfigMessage().getMessage("loc-is-null-command"), command));
        } catch (ArrayIndexOutOfBoundsException e) {
            Message.error(String.format(BAirDrop.getConfigMessage().getMessage("few-args-command"), command));
        }
    }
}