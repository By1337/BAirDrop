package org.by1337.bairdrop.observer.observer;

import org.by1337.bairdrop.observer.event.Event;
import org.by1337.api.util.Named;

public interface Observer extends Named {
    void update(Event event);
}
