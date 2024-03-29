package com.twod.feralvillage;

public class ObjectManager extends BaseObject {
    protected static final int DEFAULT_ARRAY_SIZE = 64;
   
    private FixedSizeArray<BaseObject> mObjects;
    private FixedSizeArray<BaseObject> mPendingAdditions;
    private FixedSizeArray<BaseObject> mPendingRemovals;

    public ObjectManager() {
        super();
        mObjects = new FixedSizeArray<BaseObject>(DEFAULT_ARRAY_SIZE);
        mPendingAdditions = new FixedSizeArray<BaseObject>(DEFAULT_ARRAY_SIZE);
        mPendingRemovals = new FixedSizeArray<BaseObject>(DEFAULT_ARRAY_SIZE);
    }
   
    public ObjectManager(int arraySize) {
        super();
        mObjects = new FixedSizeArray<BaseObject>(arraySize);
        mPendingAdditions = new FixedSizeArray<BaseObject>(arraySize);
        mPendingRemovals = new FixedSizeArray<BaseObject>(arraySize);
    }
   
    @Override
    public void reset() {
        commitUpdates();
        final int count = mObjects.getCount();
        for (int i = 0; i < count; i++) {
            BaseObject object = mObjects.get(i);
            object.reset();
        }
    }

    public void commitUpdates() {
        final int additionCount = mPendingAdditions.getCount();
        if (additionCount > 0) {
            final Object[] additionsArray = mPendingAdditions.getArray();
            for (int i = 0; i < additionCount; i++) {
                BaseObject object = (BaseObject)additionsArray[i];
                mObjects.add(object);
            }
            mPendingAdditions.clear();
        }
       
        final int removalCount = mPendingRemovals.getCount();
        if (removalCount > 0) {
            final Object[] removalsArray = mPendingRemovals.getArray();
   
            for (int i = 0; i < removalCount; i++) {
                BaseObject object = (BaseObject)removalsArray[i];
                mObjects.remove(object, true);
            }
            mPendingRemovals.clear();
        }
    }
   
    @Override
    public void update(float timeDelta, BaseObject parent) {
        commitUpdates();
        final int count = mObjects.getCount();
        if (count > 0) {
            final Object[] objectArray = mObjects.getArray();
            for (int i = 0; i < count; i++) {
                BaseObject object = (BaseObject)objectArray[i];
                object.update(timeDelta, this);
            }
        }
    }

    public final FixedSizeArray<BaseObject> getObjects() {
        return mObjects;
    }
   
    public final int getCount() {
        return mObjects.getCount();
    }
   
    /** Returns the count after the next commitUpdates() is called. */
    public final int getConcreteCount() {
        return mObjects.getCount() + mPendingAdditions.getCount() - mPendingRemovals.getCount();
    }
   
    public final BaseObject get(int index) {
        return mObjects.get(index);
    }

    public void add(BaseObject object) {
        mPendingAdditions.add(object);
    }

    public void remove(BaseObject object) {
        mPendingRemovals.add(object);
    }
   
    public void removeAll() {
        final int count = mObjects.getCount();
        final Object[] objectArray = mObjects.getArray();
        for (int i = 0; i < count; i++) {
            mPendingRemovals.add((BaseObject)objectArray[i]);
        }
        mPendingAdditions.clear();
    }

    /**
     * Finds a child object by its type.  Note that this may invoke the class loader and therefore
     * may be slow.
     * @param classObject The class type to search for (e.g. BaseObject.class).
     * @return
     */
    public <T> T findByClass(Class<T> classObject) {
        T object = null;
        final int count = mObjects.getCount();
        for (int i = 0; i < count; i++) {
            BaseObject currentObject = mObjects.get(i);
            if (currentObject.getClass() == classObject) {
                object = classObject.cast(currentObject);
                break;
            }
        }
        return object;
    }
   
    protected FixedSizeArray<BaseObject> getPendingObjects() {
        return mPendingAdditions;
    }

}

