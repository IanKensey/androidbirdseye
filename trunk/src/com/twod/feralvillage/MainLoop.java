package com.twod.feralvillage;

public class MainLoop extends ObjectManager {

    // Ensures that time updates before everything else.
    public MainLoop() {
        super();
        mTimeSystem = new TimeSystem();
        sSystemRegistry.timeSystem = mTimeSystem;
        sSystemRegistry.registerForReset(mTimeSystem);
    }

    @Override
    public void update(float timeDelta, BaseObject parent) {
        mTimeSystem.update(timeDelta, parent);
        final float newTimeDelta = mTimeSystem.getFrameDelta();  // The time system may warp time.
        super.update(newTimeDelta, parent);
    }

    private TimeSystem mTimeSystem;
}

