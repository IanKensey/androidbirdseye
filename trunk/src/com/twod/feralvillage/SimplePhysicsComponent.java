package com.twod.feralvillage;

public class SimplePhysicsComponent extends GameComponent {
    private static final float DEFAULT_BOUNCINESS = 0.1f;
    private float mBounciness;
    
    public SimplePhysicsComponent() {
        super();
        setPhase(GameComponent.ComponentPhases.POST_PHYSICS.ordinal());
        reset();
    }
    
    @Override
    public void reset() {
        mBounciness = DEFAULT_BOUNCINESS;
    }
    
    public void setBounciness(float bounciness) {
        mBounciness = bounciness;
    }
    
    @Override
    public void update(float timeDelta, BaseObject parent) {
        GameObject parentObject = (GameObject) parent;
        
        final Vector2 impulse = parentObject.getImpulse();
        float velocityX = parentObject.getVelocity().x + impulse.x;
        float velocityY = parentObject.getVelocity().y + impulse.y;
        
        if ((parentObject.touchingCeiling() && velocityY > 0.0f) 
            || (parentObject.touchingGround() && velocityY < 0.0f)) {
            velocityY = -velocityY * mBounciness;
            
            if (Utils.close(velocityY, 0.0f)) {
                velocityY = 0.0f;
            }
        }
        
        if ((parentObject.touchingRightWall() && velocityX > 0.0f)
            || (parentObject.touchingLeftWall() && velocityX < 0.0f)){
            velocityX = -velocityX * mBounciness;
            
            if (Utils.close(velocityX, 0.0f)) {
                velocityX = 0.0f;
            }
        }

        parentObject.getVelocity().set(velocityX, velocityY);
        impulse.zero();
    }
}