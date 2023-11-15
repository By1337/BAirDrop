package org.by1337.bairdrop.observer.event;

import org.bukkit.entity.Player;
import org.by1337.api.chat.Placeholderable;
import org.by1337.api.match.BMatch;
import org.by1337.bairdrop.BAirDrop;
import org.by1337.bairdrop.airdrop.Airdrop;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class Event implements Placeholderable {
    private final Map<String, Supplier<String>> placeholders;
    @NotNull
    private final Airdrop airdrop;
    @Nullable
    private final Player player;
    @NotNull
    private final CustomEvent event;
    private boolean canceled = false;

    public Event(@NotNull Airdrop airdrop, @Nullable Player player, @NotNull CustomEvent event) {
        this.airdrop = airdrop;
        this.player = player;
        this.event = event;
        placeholders = new HashMap<>();
        placeholders.put("{canceled}", () -> String.valueOf(canceled));
        placeholders.put("{event-type}", () -> event.getName().getName());
    }

    @NotNull
    public Airdrop getAirdrop() {
        return airdrop;
    }

    @Nullable
    public Player getPlayer() {
        return player;
    }

    @NotNull
    public CustomEvent getEvent() {
        return event;
    }

    public boolean isCanceled() {
        return canceled;
    }

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }

    @Override
    public String replace(String string) {
        StringBuilder sb = new StringBuilder(string);
        for (Map.Entry<String, Supplier<String>> entry : placeholders.entrySet()) {
            String placeholder = entry.getKey();
            int len = placeholder.length();
            int pos = sb.indexOf(placeholder);
            while (pos != -1) {
                sb.replace(pos, pos + len, entry.getValue().get());
                pos = sb.indexOf(placeholder, pos + len - 1);
            }
        }
        return sb.toString();
    }

    public Placeholderable getPlaceholderable() {
        return string -> BAirDrop.MESSAGE.messageBuilder(BMatch.matchSave(replace(airdrop.replace(string))), player);
    }
}
