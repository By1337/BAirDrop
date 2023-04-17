package org.by1337.bairdrop.util;

public enum LogLevel {
    LOW(0),
    MEDIUM(1),
    HARD(2);
    private final int lvl;

    LogLevel(int lvl) {
        this.lvl = lvl;
    }

    public int getLvl() {
        return lvl;
    }
}
