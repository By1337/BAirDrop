package org.by1337.bairdrop.menu.property;

import java.util.Optional;

/**
 * Functional interface for callbacks that provide an optional result value.
 *
 * @param <T> The type of the result value.
 */
@FunctionalInterface
public interface SetValueCallBack<T> {
    void result(Optional<T> result);
}
