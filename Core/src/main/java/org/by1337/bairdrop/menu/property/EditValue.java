package org.by1337.bairdrop.menu.property;

import org.bukkit.entity.Player;

/**
 * An interface for objects that can be edited by players.
 *
 * @param <T> The type of the value to be edited.
 */
public interface EditValue<T> {
    void editValue(Player player);
    default void init(){}
}
