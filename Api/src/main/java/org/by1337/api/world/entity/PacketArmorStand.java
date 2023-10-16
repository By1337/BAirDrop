package org.by1337.api.world.entity;

public interface PacketArmorStand extends PacketLivingEntity {
    void setSmall(boolean param0);
    boolean isSmall();
    void setShowArms(boolean param0);
    boolean isShowArms();
    void setNoBasePlate(boolean param0);
    boolean isNoBasePlate();
    void setMarker(boolean param0);
    boolean isMarker();
    void setHeadPose(float x, float y, float z);
    void setBodyPose(float x, float y, float z);
    void setLeftArmPose(float x, float y, float z);
    void setRightArmPose(float x, float y, float z);
    void setLeftLegPose(float x, float y, float z);
    void setRightLegPose(float x, float y, float z);

}
