package org.by1337.bairdrop.observer.requirement;


import org.bukkit.entity.Player;
import org.by1337.bairdrop.AirDrop;
import org.jetbrains.annotations.Nullable;

public interface Requirement {
    boolean check(@Nullable AirDrop airDrop, @Nullable Player player);

    RequirementType getType();

}
