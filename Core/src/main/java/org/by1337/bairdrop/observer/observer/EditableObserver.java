package org.by1337.bairdrop.observer.observer;

import org.by1337.bairdrop.observer.event.CustomEvent;
import org.jetbrains.annotations.NotNull;

public interface EditableObserver extends Observer {
    @NotNull
    CustomEvent customEvent();
    @NotNull
    String[] commands();
    @NotNull
    String[] denyCommands();
    @NotNull
    String description();
}
