package com.twod.feralvillage;

public class RenderSystem extends BaseObject {
    private static final int TEXTURE_SORT_BUCKET_SIZE = 1000;
    private RenderElementPool mElementPool;
    private ObjectManager[] mRenderQueues;
    private int mQueueIndex;
    
    private final static int DRAW_QUEUE_COUNT = 2;
    private final static int MAX_RENDER_OBJECTS_PER_FRAME = 384;
    private final static int MAX_RENDER_OBJECTS = MAX_RENDER_OBJECTS_PER_FRAME * DRAW_QUEUE_COUNT;
    
    public RenderSystem() {
        super();
        mElementPool = new RenderElementPool(MAX_RENDER_OBJECTS);
        mRenderQueues = new ObjectManager[DRAW_QUEUE_COUNT];
        for (int x = 0; x < DRAW_QUEUE_COUNT; x++) {
            mRenderQueues[x] = new PhasedObjectManager(MAX_RENDER_OBJECTS_PER_FRAME);
        }
        mQueueIndex = 0;
    }
    
    @Override
    public void reset() {
        
    }

    public void scheduleForDraw(DrawableObject object, Vector2 position, int priority, boolean cameraRelative) {
        RenderElement element = mElementPool.allocate();
        if (element != null) {
            element.set(object, position, priority, cameraRelative);
            mRenderQueues[mQueueIndex].add(element);
        }
    }

    private void clearQueue(FixedSizeArray<BaseObject> objects) {
        final int count = objects.getCount();
        final Object[] objectArray = objects.getArray();
        final RenderElementPool elementPool = mElementPool;
        for (int i = count - 1; i >= 0; i--) {
            RenderElement element = (RenderElement)objectArray[i];
            elementPool.release(element);
            objects.removeLast();
        }
        
    }
    
    public void swap(GameRenderer renderer, float cameraX, float cameraY) {
        mRenderQueues[mQueueIndex].commitUpdates();
        
        // This code will block if the previous queue is still being executed.
        renderer.setDrawQueue(mRenderQueues[mQueueIndex], cameraX, cameraY); 
    
        final int lastQueue = (mQueueIndex == 0) ? DRAW_QUEUE_COUNT - 1 : mQueueIndex - 1;
    
        // Clear the old queue.
        FixedSizeArray<BaseObject> objects = mRenderQueues[lastQueue].getObjects();
        clearQueue(objects);
    
        mQueueIndex = (mQueueIndex + 1) % DRAW_QUEUE_COUNT;
    }
    
    /* Empties all draw queues and disconnects the game thread from the renderer. */
    public void emptyQueues(GameRenderer renderer) {
        renderer.setDrawQueue(null, 0.0f, 0.0f); 
        for (int x = 0; x < DRAW_QUEUE_COUNT; x++) {
            mRenderQueues[x].commitUpdates();
            FixedSizeArray<BaseObject> objects = mRenderQueues[x].getObjects();
            clearQueue(objects);
        
        }
    }

    public class RenderElement extends PhasedObject {
        public RenderElement() {
            super();
        }

        public void set(DrawableObject drawable, Vector2 position, int priority, boolean isCameraRelative) {
            mDrawable = drawable;
            x = position.x;
            y = position.y;
            cameraRelative = isCameraRelative;
            final int sortBucket = priority * TEXTURE_SORT_BUCKET_SIZE;
            int sortOffset = 0;
            if (drawable != null) {
                Texture tex = drawable.getTexture();
                if (tex != null) {
                    sortOffset = (tex.resource % TEXTURE_SORT_BUCKET_SIZE) * Utils.sign(priority);
                }
            }
            setPhase(sortBucket + sortOffset);
        }

        public void reset() {
            mDrawable = null;
            x = 0.0f;
            y = 0.0f;
            cameraRelative = false;
        }

        public DrawableObject mDrawable;
        public float x;
        public float y;
        public boolean cameraRelative;
    }

    protected class RenderElementPool extends TObjectPool<RenderElement> {

        RenderElementPool(int max) {
            super(max);
        }

        @Override
        public void release(Object element) {
            RenderElement renderable = (RenderElement)element;
            // if this drawable came out of a pool, make sure it is returned to that pool.
            final ObjectPool pool = renderable.mDrawable.getParentPool();
            if (pool != null) {
            	pool.release(renderable.mDrawable);
            }
            // reset on release
            renderable.reset();
            super.release(element);
        }

        @Override
        protected void fill() {
            for (int x = 0; x < getSize(); x++) {
                getAvailable().add(new RenderElement());
            }
        }
    }
}