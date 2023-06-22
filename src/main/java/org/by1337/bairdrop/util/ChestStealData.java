package org.by1337.bairdrop.util;

import org.by1337.bairdrop.BAirDrop;

public class ChestStealData {
    private int INTERVAL = BAirDrop.getInstance().getConfig().getInt("anti-steal.max-interval");
    private int warnings = 0;
    private long lastInterval = -1;
    private long lastSteal = -1;
    private long lastTime;

    public int getWarnings() {
        return warnings;
    }

    public void addTime(long time) {
        if (time > BAirDrop.getInstance().getConfig().getInt("anti-steal.min-interval-to-ignore")) {
            reset();
            return;
        }
        if (lastInterval != -1) {
            if (Math.abs(lastInterval - time) < INTERVAL) {
                warnings++;
            } else
                reset();
        } else {
            lastInterval = time;
        }
    }

    public void setLastSteal(long lastSteal) {
        this.lastSteal = lastSteal;
    }

    public long getLastSteal() {
        return lastSteal;
    }

    public void reset() {
        warnings = 0;
        lastInterval = -1;
    }

    public long getLastTime() {
        return lastTime;
    }

    public void setLastTime(long lastTime) {
        this.lastTime = lastTime;
    }
}
