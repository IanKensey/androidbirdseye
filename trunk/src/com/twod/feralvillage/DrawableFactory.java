package com.twod.feralvillage;

public class DrawableFactory extends BaseObject {
    private final static int BITMAP_POOL_SIZE = 768;
   
    private DrawableBitmapPool mBitmapPool;
    private ScrollableBitmapPool mScrollableBitmapPool;
    private TiledBackgroundVertexGridPool mTiledBackgroundVertexGridPool;
   
    // This class wraps several object pools and provides a type-sensitive release function.
    public DrawableFactory() {
        super();
        mBitmapPool = new DrawableBitmapPool(BITMAP_POOL_SIZE);
        mTiledBackgroundVertexGridPool = new TiledBackgroundVertexGridPool();
        mScrollableBitmapPool = new ScrollableBitmapPool();
    }
   
    @Override
    public void reset() {
    }

    public DrawableBitmap allocateDrawableBitmap() {
        return mBitmapPool.allocate();
    }
   
    public TiledBackgroundVertexGrid allocateTiledBackgroundVertexGrid() {
        return mTiledBackgroundVertexGridPool.allocate();
    }

    public ScrollableBitmap allocateScrollableBitmap() {
        return mScrollableBitmapPool.allocate();
    }

    public void release(DrawableObject object) {
        ObjectPool pool = object.getParentPool();
        if (pool != null) {
            pool.release(object);
        }
        // Objects with no pool weren't created by this factory.  Ignore them.
    }

    private class DrawableBitmapPool extends TObjectPool<DrawableBitmap> {
       
        public DrawableBitmapPool(int size) {
            super(size);
        }
       
        @Override
        public void reset() {
        }
       
        @Override
        protected void fill() {
            int size = getSize();
            for (int x = 0; x < size; x++) {
                DrawableBitmap entry = new DrawableBitmap(null, 0, 0);
                entry.setParentPool(this);
                getAvailable().add(entry);
            }
        }

        @Override
        public void release(Object entry) {
            ((DrawableBitmap)entry).reset();
            super.release(entry);
        }
       
        @Override
        public DrawableBitmap allocate() {
            DrawableBitmap result = super.allocate();
            ContextParameters params = sSystemRegistry.contextParameters;
            if (result != null && params != null) {
                result.setViewSize(params.gameWidth, params.gameHeight);
            }
            return result;
        }
    }

    private class ScrollableBitmapPool extends TObjectPool<ScrollableBitmap> {
       
        public ScrollableBitmapPool() {
            super();
        }
       
        @Override
        public void reset() {
        }
       
        @Override
        protected void fill() {
            int size = getSize();
            for (int x = 0; x < size; x++) {
                ScrollableBitmap entry = new ScrollableBitmap(null, 0, 0);
                entry.setParentPool(this);
                getAvailable().add(entry);
            }
        }

        @Override
        public void release(Object entry) {
            ((ScrollableBitmap)entry).reset();
            super.release(entry);
        }
       
       
    }
   
    private class TiledBackgroundVertexGridPool extends TObjectPool<TiledBackgroundVertexGrid> {
       
        public TiledBackgroundVertexGridPool() {
            super();
        }
       
        @Override
        public void reset() {
        }
       
        @Override
        protected void fill() {
            int size = getSize();
            for (int x = 0; x < size; x++) {
                TiledBackgroundVertexGrid entry = new TiledBackgroundVertexGrid();
                entry.setParentPool(this);
                getAvailable().add(entry);
            }
        }

        @Override
        public void release(Object entry) {
            TiledBackgroundVertexGrid bg = (TiledBackgroundVertexGrid)entry;
            bg.reset();
            super.release(entry);
        }
       
    }
}

