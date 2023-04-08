package org.by1337.bairdrop.util;

public enum Events {
    CLICK_CLOSE(true),
    CLICK_OPEN(true),
    TIMER(false),
    START_EVENT(false),
    END_EVENT(false),
    UNLOCK_EVENT(false),
    FIRST_OPEN(true),
    SUMMONER(true),
    ACTIVATE(true),
    STOP_WHEN_EMPTY(false),
    NONE(true),
    CRAFT_ITEM(true),
    UNLOAD(false);
    private final boolean hasPlayer;

    Events(boolean hasPlayer) {
        this.hasPlayer = hasPlayer;
    }

    public boolean isHasPlayer() {
        return hasPlayer;
    }

}
