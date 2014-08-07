package com.twod.feralvillage;

public class FixedAnimationComponent extends GameComponent {
    private int mAnimationIndex;
   
    public FixedAnimationComponent() {
        super();
        setPhase(ComponentPhases.ANIMATION.ordinal());
        reset();
    }
   
    @Override
    public void reset() {
        mAnimationIndex = 0;
    }
   
    @Override
    public void update(float timeDelta, BaseObject parent) {
        // We look up the sprite component each frame so that this component can be shared.
        GameObject parentObject = (GameObject)parent;
        SpriteComponent sprite = parentObject.findByClass(SpriteComponent.class);
        if (sprite != null) {
            sprite.playAnimation(mAnimationIndex);
        }
    }
   
    public void setAnimation(int index) {
        mAnimationIndex = index;
    }
}

