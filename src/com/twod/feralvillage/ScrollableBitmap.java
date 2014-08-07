package com.twod.feralvillage;

public class ScrollableBitmap extends DrawableBitmap {
    private float mScrollOriginX;
    private float mScrollOriginY;
    
    public ScrollableBitmap(Texture texture, int width, int height) {
        super(texture, width, height);
    }

    public void setScrollOrigin(float x, float y) {
        mScrollOriginX = x;
        mScrollOriginY = y;
    }

    @Override
    public void draw(float x, float y, float scaleX, float scaleY) {
        super.draw(x - mScrollOriginX, y - mScrollOriginY, scaleX, scaleY);
    }

    public float getScrollOriginX() {
        return mScrollOriginX;
    }

    public void setScrollOriginX(float scrollOriginX) {
        mScrollOriginX = scrollOriginX;
    }

    public float getScrollOriginY() {
        return mScrollOriginY;
    }

    public void setScrollOriginY(float scrollOriginY) {
        mScrollOriginY = scrollOriginY;
    }
}