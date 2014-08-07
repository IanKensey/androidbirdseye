package com.twod.feralvillage;

public class AnimationFrame extends AllocationGuard {
    public Texture texture;
    public float holdTime;
    FixedSizeArray<CollisionVolume> attackVolumes;
    FixedSizeArray<CollisionVolume> vulnerabilityVolumes;
   
    public AnimationFrame(Texture textureObject, float animationHoldTime) {
        super();
        texture = textureObject;
        holdTime = animationHoldTime;
    }
   
    public AnimationFrame(Texture textureObject, float animationHoldTime,
            FixedSizeArray<CollisionVolume> attackVolumeList,
            FixedSizeArray<CollisionVolume> vulnerabilityVolumeList) {
        super();
        texture = textureObject;
        holdTime = animationHoldTime;
        attackVolumes = attackVolumeList;
        vulnerabilityVolumes = vulnerabilityVolumeList;
    }
}

