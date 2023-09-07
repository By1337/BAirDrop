package org.by1337.bairdrop.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Lang {
    public HashMap<String, List<String>> messages = new HashMap<>();

    public Lang() {
    }
    public String getMessage(String patch){
        return String.join("\n", messages.getOrDefault(patch, List.of(String.format("Message along path %s not found!", patch))));
    }
    public List<String> getList(String patch){
        return messages.getOrDefault(patch, List.of(String.format("Message along path %s not found!", patch)));
    }
}
