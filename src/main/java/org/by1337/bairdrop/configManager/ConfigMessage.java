package org.by1337.bairdrop.configManager;

import java.util.List;

public interface ConfigMessage {
    String getMessage(String path);
    String getMessageFromPlugin(String path);
    List<String> getList(String path);
    List<String> getListFromPlugin(String path);

}
