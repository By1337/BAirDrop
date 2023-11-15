package org.by1337.bairdrop.util;

import java.util.Optional;

/**
 * Functional interface for callbacks that provide an optional result value.
 *
 * @param <T> The type of the result value.
 */
@FunctionalInterface
public interface SetValueCallBack<T extends Optional<?>> {
    void result(T result);
}
