package com.twod.feralvillage;

public class DynamicCollisionComponent extends GameComponent {
    private FixedSizeArray<CollisionVolume> mAttackVolumes;
    private FixedSizeArray<CollisionVolume> mVulnerabilityVolumes;
    private SphereCollisionVolume mBoundingVolume;
    private HitReactionComponent mHitReactionComponent;
    
    public DynamicCollisionComponent() {
        super();
        mBoundingVolume = new SphereCollisionVolume(0.0f, 0.0f, 0.0f);
        setPhase(ComponentPhases.FRAME_END.ordinal());
        reset();
    }
    
    @Override
    public void reset() {
       mAttackVolumes = null;
       mVulnerabilityVolumes = null;
       mBoundingVolume.setCenter(Vector2.ZERO);
       mBoundingVolume.setRadius(0.0f);
       mHitReactionComponent = null;     
    }
    
    @Override
    public void update(float timeDelta, BaseObject parent) {
        GameObjectCollisionSystem collision = sSystemRegistry.gameObjectCollisionSystem;
        if (collision != null && mBoundingVolume.getRadius() > 0.0f) {
            collision.registerForCollisions((GameObject)parent, mHitReactionComponent, mBoundingVolume, 
                    mAttackVolumes, mVulnerabilityVolumes);
        }
    }
    
    public void setHitReactionComponent(HitReactionComponent component) {
        mHitReactionComponent = component;
    }
    
    public void setCollisionVolumes(FixedSizeArray<CollisionVolume> attackVolumes, 
            FixedSizeArray<CollisionVolume> vulnerableVolumes) {
        if (mVulnerabilityVolumes != vulnerableVolumes || mAttackVolumes != attackVolumes) {
            mAttackVolumes = attackVolumes;
            mVulnerabilityVolumes = vulnerableVolumes;
            mBoundingVolume.reset();
            if (mAttackVolumes != null) {
               final int count = mAttackVolumes.getCount();
               for (int x = 0; x < count; x++) {
                   mBoundingVolume.growBy(mAttackVolumes.get(x));
               }
            }
            
            if (mVulnerabilityVolumes != null) {
                final int count = mVulnerabilityVolumes.getCount();
                for (int x = 0; x < count; x++) {
                    mBoundingVolume.growBy(mVulnerabilityVolumes.get(x));
                }
             }
        }
    }
}
