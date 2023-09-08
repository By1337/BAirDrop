package org.by1337.api.command;

import lombok.NonNull;
import org.by1337.api.world.BLocation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface SummonCommand {
    void spawn(@NotNull String entityType, @NotNull BLocation location, @Nullable String nbt);
}
