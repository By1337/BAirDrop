package org.by1337.lib.command;

import org.by1337.api.world.BLocation;
import org.by1337.lib.Version;
import org.by1337.v1_16_R3.command.SummonCommandV1_16_R3;
import org.by1337.v1_17_R1.command.SummonCommandV1_17_R1;
import org.by1337.v1_18_R1.command.SummonCommandV1_18_R1;
import org.by1337.v1_18_R2.command.SummonCommandV1_18_R2;
import org.by1337.v1_19_R1.command.SummonCommandV1_19_R1;
import org.by1337.v1_19_R2.command.SummonCommandV1_19_R2;
import org.by1337.v1_19_R3.command.SummonCommandV1_19_R3;
import org.by1337.v1_20_R1.command.SummonCommandV1_20_R1;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SummonCommand {
    public static void execute(@NotNull String entityType, @NotNull BLocation location, @Nullable String nbt){
        if (Version.version == Version.V1_16_R3){
            new SummonCommandV1_16_R3().spawn(entityType, location, nbt);
        }else if (Version.version == Version.V1_17_R1){
            new SummonCommandV1_17_R1().spawn(entityType, location, nbt);
        }else if (Version.version == Version.V1_18_R1){
            new SummonCommandV1_18_R1().spawn(entityType, location, nbt);
        }else if (Version.version == Version.V1_18_R2){
            new SummonCommandV1_18_R2().spawn(entityType, location, nbt);
        }else if (Version.version == Version.V1_19_R1){
            new SummonCommandV1_19_R1().spawn(entityType, location, nbt);
        }else if (Version.version == Version.V1_19_R2){
            new SummonCommandV1_19_R2().spawn(entityType, location, nbt);
        }else if (Version.version == Version.V1_19_R3){
            new SummonCommandV1_19_R3().spawn(entityType, location, nbt);
        }else { // V1_20_R1
            new SummonCommandV1_20_R1().spawn(entityType, location, nbt);
        }
    }
}
