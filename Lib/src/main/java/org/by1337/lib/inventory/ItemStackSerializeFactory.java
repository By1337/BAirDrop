package org.by1337.lib.inventory;

import org.by1337.api.inventory.ItemStackSerialize;
import org.by1337.lib.Version;
import org.by1337.v1_16_R3.inventory.ItemStackSerializeV1_16_5;

public class ItemStackSerializeFactory {
    public static ItemStackSerialize create() {
        if (Version.version == Version.V1_16_R3) {
            return new ItemStackSerializeV1_16_5();
        } else if (Version.version == Version.V1_17_R1) {
            //todo
        } else if (Version.version == Version.V1_18_R1) {
            //todo
        } else if (Version.version == Version.V1_18_R2) {
            //todo
        } else if (Version.version == Version.V1_19_R1) {
            //todo
        } else if (Version.version == Version.V1_19_R2) {
            //todo
        } else if (Version.version == Version.V1_19_R3) {
            //todo
        } else { // V1_20_R1
            //todo
        }
        return null; // will never return
    }
}
