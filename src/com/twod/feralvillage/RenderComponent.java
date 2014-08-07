package com.twod.feralvillage;

public class RenderComponent extends GameComponent {
    private DrawableObject mDrawable;
    private int mPriority;
    private boolean mCameraRelative;
    private Vector2 mPositionWorkspace;
    private Vector2 mScreenLocation;
    private Vector2 mDrawOffset;
    
    public RenderComponent() {
        super();
        setPhase(ComponentPhases.DRAW.ordinal());
        
        mPositionWorkspace = new Vector2();
        mScreenLocation = new Vector2();
        mDrawOffset = new Vector2();
        reset();
    }
    
    @Override
    public void reset() {
        mPriority = 0;
        mCameraRelative = true;
        mDrawable = null;
        mDrawOffset.zero();
    }

    public void update(float timeDelta, BaseObject parent) {
        if (mDrawable != null) {
            RenderSystem system = sSystemRegistry.renderSystem;
            if (system != null) {
                mPositionWorkspace.set(((GameObject)parent).getPosition());
                mPositionWorkspace.add(mDrawOffset);
                if (mCameraRelative) {
                    CameraSystem camera = sSystemRegistry.cameraSystem;
                    ContextParameters params = sSystemRegistry.contextParameters;
                    mScreenLocation.x = (mPositionWorkspace.x - camera.getFocusPositionX()
                                    + (params.gameWidth / 2));
                    mScreenLocation.y = (mPositionWorkspace.y - camera.getFocusPositionY()
                                    + (params.gameHeight / 2));
                }
                // It might be better not to do culling here, as doing it in the render thread
                // would allow us to have multiple views into the same scene and things like that.
                // But at the moment significant CPU is being spent on sorting the list of objects
                // to draw per frame, so I'm going to go ahead and cull early.
                if (mDrawable.visibleAtPosition(mScreenLocation)) {
                    system.scheduleForDraw(mDrawable, mPositionWorkspace, mPriority, mCameraRelative);
                } else if (mDrawable.getParentPool() != null) {
                    // Normally the render system releases drawable objects back to the factory
                    // pool, but in this case we're short-circuiting the render system, so we
                    // need to release the object manually.
                    sSystemRegistry.drawableFactory.release(mDrawable);
                    mDrawable = null;
                }
            }
        }
    }

    public DrawableObject getDrawable() {
        return mDrawable;
    }
    
    public void setDrawable(DrawableObject drawable) {
        mDrawable = drawable;
    }

    public void setPriority(int priority) {
        mPriority = priority;
    }
    
    public int getPriority() {
        return mPriority;
    }

    public void setCameraRelative(boolean relative) {
        mCameraRelative = relative;
    }
    
    public void setDrawOffset(float x, float y) {
        mDrawOffset.set(x, y);
    }

}