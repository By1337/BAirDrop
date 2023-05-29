package org.by1337.bairdrop.customListeners;

import org.bukkit.Keyed;
import org.bukkit.NamespacedKey;
import org.by1337.bairdrop.obfuscate.DontObfuscate;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;

@DontObfuscate
public record CustomEvent(NamespacedKey key) implements Keyed {
    private static final HashMap<NamespacedKey, CustomEvent> byKey = new HashMap<>();
    /**
     * когда кто-то кликает по закрытому аирдропу
     * PLayer not null, AirDrop not null
     */
    public static final CustomEvent CLICK_CLOSE = registerEvent(new CustomEvent(NamespacedKey.fromString("click_close")));
    /**
     * когда кто-то кликает по открытому аирдропу
     * PLayer not null, AirDrop not null
     */
    public static final CustomEvent CLICK_OPEN = registerEvent(new CustomEvent(NamespacedKey.fromString("click_open")));
    /**
     * каждую секунду вызывается аирдропорм
     * PLayer null, AirDrop not null
     */
    public static final CustomEvent TIMER = registerEvent(new CustomEvent(NamespacedKey.fromString("timer")));
    /**
     * когда аирдроп спавнится
     * PLayer null, AirDrop not null
     */
    public static final CustomEvent START_EVENT = registerEvent(new CustomEvent(NamespacedKey.fromString("start_event")));
    /**
     * когда аирдроп заканчивается
     * PLayer null, AirDrop not null
     */
    public static final CustomEvent END_EVENT = registerEvent(new CustomEvent(NamespacedKey.fromString("end_event")));
    /**
     * когда аирдроп открывается
     * PLayer null, AirDrop not null
     */
    public static final CustomEvent UNLOCK_EVENT = registerEvent(new CustomEvent(NamespacedKey.fromString("unlock_event")));
    /**
     * когда кто-то в первый раз открывет аирдроп
     * PLayer not null, AirDrop not null
     */
    public static final CustomEvent FIRST_OPEN = registerEvent(new CustomEvent(NamespacedKey.fromString("first_open")));
    /**
     * когда кто-то призывает аирдроп
     * PLayer not null, AirDrop not null
     */
    public static final CustomEvent SUMMONER = registerEvent(new CustomEvent(NamespacedKey.fromString("summoner")));
    /**
     * когда кто-то активирует аирдроп
     * PLayer not null, AirDrop not null
     */
    public static final CustomEvent ACTIVATE = registerEvent(new CustomEvent(NamespacedKey.fromString("activate")));
    /**
     * когда аирдроп заканчивается из-за опустошения, так же вместе с ним вызываетя END_EVENT
     * PLayer null, AirDrop not null
     */
    public static final CustomEvent STOP_WHEN_EMPTY = registerEvent(new CustomEvent(NamespacedKey.fromString("stop_when_empty")));
    /**
     * этот ивент не вызывается так же слушатели подписыные на этот ивент не отображаються в меню
     * PLayer not null/null, AirDrop not null/null
     */
    public static final CustomEvent NONE = registerEvent(new CustomEvent(NamespacedKey.fromString("none")));
    /**
     * когда кто-то крафтит предмет
     * PLayer not null, AirDrop null
     */
    public static final CustomEvent CRAFT_ITEM = registerEvent(new CustomEvent(NamespacedKey.fromString("craft_item")));
    /**
     * когда аирдроп выгружается
     * PLayer null, AirDrop not null
     */
    public static final CustomEvent UNLOAD = registerEvent(new CustomEvent(NamespacedKey.fromString("unload")));
    /**
     * когда аирдроп загружается
     * PLayer null, AirDrop not null
     */
    public static CustomEvent LOAD = registerEvent(new CustomEvent(NamespacedKey.fromString("load")));
    /**
     * когда плагин выключается
     * вызываються только статические слушатели
     * PLayer null, AirDrop null
     */
    public static CustomEvent DISABLE = registerEvent(new CustomEvent(NamespacedKey.fromString("disable")));
    /**
     * когда плагин включается
     * вызываються только статические слушатели
     * PLayer null, AirDrop null
     */
    public static CustomEvent ENABLE = registerEvent(new CustomEvent(NamespacedKey.fromString("enable")));

    /**
     * @param key NamespacedKey ключ ивента
     * @return null ели такого ивента нет иначе вернёт CustomEvent
     */
    @Nullable
    public static CustomEvent getByKey(NamespacedKey key) {
        return byKey.getOrDefault(key, null);
    }

    /**
     * Регистрирует кастомный ивент
     * @param customEvent ивент который надо зарегистрировать
     * @return объект зарегистрированого ивента
     * @throws IllegalArgumentException если этот ивент уже зарегистрирован
     */
    public static CustomEvent registerEvent(CustomEvent customEvent) {
        if (byKey.containsKey(customEvent.getKey())) {
            throw new IllegalArgumentException("Cannot set already-set customEvent: " + customEvent.getKey().getKey());
        }
        byKey.put(customEvent.getKey(), customEvent);
        return customEvent;
    }

    /**
     * @param customEvent
     * @return вернёт true если этот ивент уже зарегистрирован
     */
    public static boolean hasEvent(CustomEvent customEvent){
        return byKey.containsKey(customEvent.key);
    }
    public static void unRegisterEvent(CustomEvent event){
        byKey.remove(event.key);
    }

    @NotNull
    @Override
    public NamespacedKey getKey() {
        return key;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj instanceof CustomEvent customEvent) {
            return this.hashCode(this.key.getKey().getBytes()) == this.hashCode(customEvent.key.getKey().getBytes());
        } else
            return false;
    }

    private int hashCode(byte[] arr) {
        if (arr == null)
            return 0;

        int result = 0;
        for (byte element : arr)
            result = 45 * result + element;

        return result;
    }
}

