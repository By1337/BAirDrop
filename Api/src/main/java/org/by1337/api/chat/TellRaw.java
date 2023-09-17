package org.by1337.api.chat;

import com.google.gson.JsonParseException;
import org.bukkit.entity.Player;

public interface TellRaw {
    void tell(String raw, Player player) throws JsonParseException;
}
