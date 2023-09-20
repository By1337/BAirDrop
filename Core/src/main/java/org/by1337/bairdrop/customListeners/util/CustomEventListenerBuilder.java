package org.by1337.bairdrop.customListeners.util;

import org.bukkit.NamespacedKey;
import org.by1337.bairdrop.customListeners.CustomEvent;
import org.by1337.bairdrop.customListeners.CustomEventListener;

import java.util.HashMap;
public class CustomEventListenerBuilder {
    private  CustomEvent customEvent;
    private  String description;
    private  String[] commands;
    private  String[] denyCommands;
    private  HashMap<String, HashMap<String, String>> requirement;
    private  NamespacedKey key;

    public CustomEventListenerBuilder customEvent(CustomEvent customEvent) {
        this.customEvent = customEvent;
        return this;
    }

    public CustomEventListenerBuilder description(String description) {
        this.description = description;
        return this;
    }

    public CustomEventListenerBuilder commands(String[] commands) {
        this.commands = commands;
        return this;
    }

    public CustomEventListenerBuilder denyCommands(String[] denyCommands) {
        this.denyCommands = denyCommands;
        return this;
    }

    public CustomEventListenerBuilder requirement(HashMap<String, HashMap<String, String>> requirement) {
        this.requirement = requirement;
        return this;
    }

    public CustomEventListenerBuilder key(NamespacedKey key) {
        this.key = key;
        return this;
    }
    public CustomEventListener build(){
        return new CustomEventListener(customEvent,
                commands,
                requirement,
                description, denyCommands, key
                );
    }
}
