package com.twod.feralvillage;

import com.twod.feralvillage.BaseObject;
import com.twod.feralvillage.GameComponent;
import com.twod.feralvillage.GameObject;
import com.twod.feralvillage.Vector2;
import com.twod.feralvillage.GameComponent.ComponentPhases;

public class GravityComponent extends GameComponent {
    private Vector2 mGravity;
    private Vector2 mScaledGravity;
    private static final Vector2 sDefaultGravity = new Vector2(0.0f, 0.01f);
   
    public GravityComponent() {
        super();
        mGravity = new Vector2(sDefaultGravity);
        mScaledGravity = new Vector2();
        setPhase(ComponentPhases.PHYSICS.ordinal());
    }
   
    @Override
    public void reset() {
        mGravity.set(sDefaultGravity);
    }

    @Override
    public void update(float timeDelta, BaseObject parent) {
        mScaledGravity.set(mGravity);
        mScaledGravity.multiply(timeDelta);
        ((GameObject) parent).getVelocity().add(mScaledGravity);
    }

    public Vector2 getGravity() {
        return mGravity;
    }
   
    public void setGravityMultiplier(float multiplier) {
        mGravity.set(sDefaultGravity);
        mGravity.multiply(multiplier);
    }
}

