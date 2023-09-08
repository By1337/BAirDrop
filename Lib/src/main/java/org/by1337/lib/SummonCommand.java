package org.by1337.lib;

import org.by1337.api.world.BLocation;
import org.by1337.v1_16_5.AsyncCatcherV1_16_5;
import org.by1337.v1_16_5.command.SummonCommandV1_16_5;
import org.by1337.v1_17.command.SummonCommandV1_17;
import org.by1337.v1_17_1.command.SummonCommandV1_17_1;
import org.by1337.v1_18.command.SummonCommandV1_18;
import org.by1337.v1_18_1.command.SummonCommandV1_18_1;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SummonCommand {
    public static void execute(@NotNull String entityType, @NotNull BLocation location, @Nullable String nbt){
        Version version = Version.getVersion();
        if (version == Version.V1_16_5){
           new SummonCommandV1_16_5().spawn(entityType, location, nbt);
        }else  if (version == Version.V1_17){
            new SummonCommandV1_17().spawn(entityType, location, nbt);
        }else  if (version == Version.V1_17_1){
            new SummonCommandV1_17_1().spawn(entityType, location, nbt);
        }else  if (version == Version.V1_18){
            new SummonCommandV1_18().spawn(entityType, location, nbt);
        }else  if (version == Version.V1_18_1){
            new SummonCommandV1_18_1().spawn(entityType, location, nbt);
        }
    }
}
