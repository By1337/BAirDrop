/**
 * Interface representing hover event contents.
 */
package org.by1337.lib.chat.hover;

public interface HoverEventContents {
    String toString();
    HoverEventType getType();
    String toSource();
}
