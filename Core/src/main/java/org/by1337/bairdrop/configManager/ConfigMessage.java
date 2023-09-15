package org.by1337.bairdrop.configManager;

import java.util.List;

public interface ConfigMessage {
    @Deprecated // Временно пока не обновлю все строки
    String getMessage(String path);
    @Deprecated
    List<String> getList(String path);

}
