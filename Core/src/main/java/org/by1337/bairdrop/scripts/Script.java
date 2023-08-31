package org.by1337.bairdrop.scripts;

import javax.annotation.Nullable;
import java.util.HashMap;

public interface Script {
    Object runScript(String scriptName, @Nullable HashMap<String, Object> property);
}
