package com.twod.feralvillage;

public class MovementComponent extends GameComponent {
    // If multiple game components were ever running in different threads, this would need
    // to be non-static.
    private static Interpolator sInterpolator = new Interpolator();

    public MovementComponent() {
        super();
        setPhase(ComponentPhases.MOVEMENT.ordinal());
    }
   
    @Override
    public void reset() {
       
    }
   
    @Override
    public void update(float timeDelta, BaseObject parent) {
        GameObject object = (GameObject) parent;

        sInterpolator.set(object.getVelocity().x, object.getTargetVelocity().x,
                object.getAcceleration().x);
        float offsetX = sInterpolator.interpolate(timeDelta);
        float newX = object.getPosition().x + offsetX;
        float newVelocityX = sInterpolator.getCurrent();

        sInterpolator.set(object.getVelocity().y, object.getTargetVelocity().y,
                object.getAcceleration().y);
        float offsetY = sInterpolator.interpolate(timeDelta);
        float newY = object.getPosition().y + offsetY;
        float newVelocityY = sInterpolator.getCurrent();

        if (object.positionLocked == false) {
            object.getPosition().set(newX, newY);
        }
       
        object.getVelocity().set(newVelocityX, newVelocityY);
    }

}

