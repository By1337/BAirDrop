package org.by1337.lib.util;

import java.util.LinkedList;

public class CyclicList<T> extends LinkedList<T> {

    public int current = 0;

    public T getNext() {
        if (isEmpty()) {
            throw new IllegalStateException("List is empty");
        }
        current = (current+ 1) % size();
        return get(current);
    }

    public T getPrevious() {
        if (isEmpty()) {
            throw new IllegalStateException("List is empty");
        }

        current = (current - 1 + size()) % size();
        return get(current);
    }

    public T getCurrent() {
        if (isEmpty()) {
            throw new IllegalStateException("List is empty");
        }
        return get(current);
    }
}