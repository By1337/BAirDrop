package org.by1337.api.world.entity;

public interface PacketArmorStand extends PacketEntity{
    void setGlowingTag_(boolean flag);

    void setInvisible_(boolean flag);

    void setSprinting_(boolean flag);

    void setCustomNameVisible_(boolean flag);

    void setSilent_(boolean flag);

    void setNoGravity_(boolean flag);

    void setMarker_(boolean flag);

    void setSmall_(boolean flag);

    void setCustomName_(String name);

    String getCustomName_();

    boolean isNoGravity_();

    boolean isMarker_();

    boolean isSmall_();
}
