package org.by1337.lib;

import org.by1337.v1_16_5.AsyncCatcherV1_16_5;

public class AsyncCatcher {
    public static void catchOp(String s){
        Version version = Version.getVersion();
        if (version == Version.V1_16_5){
            AsyncCatcherV1_16_5.catchOp(s);
        }
    }
}
