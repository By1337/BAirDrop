package org.by1337.lib;

import org.by1337.v1_16_5.AsyncCatcherV1_16_5;
import org.by1337.v1_17.AsyncCatcherV1_17;
import org.by1337.v1_17_1.AsyncCatcherV1_17_1;
import org.by1337.v1_18.AsyncCatcherV1_18;
import org.by1337.v1_18_1.AsyncCatcherV1_18_1;

public class AsyncCatcher {
    public static void catchOp(String s){
        Version version = Version.getVersion();
        if (version == Version.V1_16_5){
            AsyncCatcherV1_16_5.catchOp(s);
        }else if (version == Version.V1_17_1){
            AsyncCatcherV1_17_1.catchOp(s);
        }else if (version == Version.V1_17){
            AsyncCatcherV1_17.catchOp(s);
        }else if (version == Version.V1_18){
            AsyncCatcherV1_18.catchOp(s);
        }else if (version == Version.V1_18_1){
            AsyncCatcherV1_18_1.catchOp(s);
        }
    }
}
