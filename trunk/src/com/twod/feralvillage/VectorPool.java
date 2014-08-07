package com.twod.feralvillage;

public class VectorPool extends TObjectPool<Vector2> {

    public VectorPool() {
        super();
    }
    
    @Override
    protected void fill() {
        for (int x = 0; x < getSize(); x++) {
            getAvailable().add(new Vector2());
        }
    }

    @Override
    public void release(Object entry) {
        ((Vector2)entry).zero();
        super.release(entry);
    }

    /** Allocates a vector and assigns the value of the passed source vector to it. */
    public Vector2 allocate(Vector2 source) {
        Vector2 entry = super.allocate();
        entry.set(source);
        return entry;
    }

}