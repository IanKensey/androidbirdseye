package com.twod.feralvillage;

public class FrameRateWatcherComponent extends GameComponent {
        private RenderComponent mRenderComponent;
        private DrawableObject mDrawable;
        private float mMaxFrameTime = 1.0f / 30.0f;
        
        public FrameRateWatcherComponent() {
        super();
        setPhase(GameComponent.ComponentPhases.THINK.ordinal());
    }
    
    @Override
    public void reset() {
        mRenderComponent = null;
        mDrawable = null;
    }
    
    @Override
    public void update(float timeDelta, BaseObject parent) {   
        if (mRenderComponent != null && mDrawable != null) {
                if (timeDelta > mMaxFrameTime) {
                        mRenderComponent.setDrawable(mDrawable);
                } else {
                        mRenderComponent.setDrawable(null);
                }
        }
        }
    
    public void setup(RenderComponent render, DrawableObject drawable) {
        mRenderComponent = render;
        mDrawable = drawable;
    }
}
