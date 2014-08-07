package com.twod.feralvillage;

public class Texture extends AllocationGuard {
    public int resource;
    public int name;
    public int width;
    public int height;
    public boolean loaded;
    
    public Texture() {
        super();
        reset();
    }
    
    public void reset() {
        resource = -1;
        name = -1;
        width = 0;
        height = 0;
        loaded = false;
    }
}