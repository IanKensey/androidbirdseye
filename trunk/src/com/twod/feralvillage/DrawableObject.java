package com.twod.feralvillage;

public abstract class DrawableObject extends AllocationGuard {
    private float mPriority;
    private ObjectPool mParentPool;
    
    public abstract void draw(float x, float y, float scaleX, float scaleY);

    public DrawableObject() {
        super();
    }
    
    public void setPriority(float f) {
        mPriority = f;
    }

    public float getPriority() {
        return mPriority;
    }

    public void setParentPool(ObjectPool pool) {
        mParentPool = pool;
    }

    public ObjectPool getParentPool() {
        return mParentPool;
    }
    
    // Override to allow drawables to be sorted by texture.
    public Texture getTexture() {
        return null;
    }
    
    // Function to allow drawables to specify culling rules.
    public boolean visibleAtPosition(Vector2 position) {
        return true;
    }

}
