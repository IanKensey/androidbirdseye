package com.twod.feralvillage;

public class AABoxCollisionVolume extends CollisionVolume {
    private Vector2 mWidthHeight;
    private Vector2 mBottomLeft;
   
    public AABoxCollisionVolume(float offsetX, float offsetY, float width, float height) {
        super();
        mBottomLeft = new Vector2(offsetX, offsetY);
        mWidthHeight = new Vector2(width, height);
    }
   
    public AABoxCollisionVolume(float offsetX, float offsetY, float width, float height,
            int hit) {
        super(hit);
        mBottomLeft = new Vector2(offsetX, offsetY);
        mWidthHeight = new Vector2(width, height);
    }
   
    @Override
    public final float getMaxX() {
        return mBottomLeft.x + mWidthHeight.x;
    }

    @Override
    public final float getMinX() {
        return mBottomLeft.x;
    }
   
    @Override
    public final float getMaxY() {
        return mBottomLeft.y + mWidthHeight.y;
    }

    @Override
    public final float getMinY() {
        return mBottomLeft.y;
    }
   
    /**
     * Calculates the intersection of this volume and another, and returns true if the
     * volumes intersect.  This test treats the other volume as an AABox.
     * @param position The world position of this volume.
     * @param other The volume to test for intersections.
     * @param otherPosition The world position of the other volume.
     * @return true if the volumes overlap, false otherwise.
     */
    @Override
    public boolean intersects(Vector2 position, FlipInfo flip, CollisionVolume other,
            Vector2 otherPosition, FlipInfo otherFlip) {
        final float left = getMinXPosition(flip) + position.x;
        final float right = getMaxXPosition(flip) + position.x;
        final float bottom = getMinYPosition(flip) + position.y;
        final float top = getMaxYPosition(flip) + position.y;
       
        final float otherLeft = other.getMinXPosition(otherFlip) + otherPosition.x;
        final float otherRight = other.getMaxXPosition(otherFlip) + otherPosition.x;
        final float otherBottom = other.getMinYPosition(otherFlip) + otherPosition.y;
        final float otherTop = other.getMaxYPosition(otherFlip) + otherPosition.y;
       
        final boolean result = boxIntersect(left, right, top, bottom,
                    otherLeft, otherRight, otherTop, otherBottom)
                || boxIntersect(otherLeft, otherRight, otherTop, otherBottom,
                    left, right, top, bottom);
       
        return result;
    }
   
    /** Tests two axis-aligned boxes for overlap. */
    private boolean boxIntersect(float left1, float right1, float top1, float bottom1,
            float left2, float right2, float top2, float bottom2) {
        final boolean horizontalIntersection = left1 < right2 && left2 < right1;
        final boolean verticalIntersection = top1 > bottom2 && top2 > bottom1;
        final boolean intersecting = horizontalIntersection && verticalIntersection;
        return intersecting;
    }
   
    /** Increases the size of this volume as necessary to fit the passed volume. */
    public void growBy(CollisionVolume other) {
        final float maxX;
        final float minX;
       
        final float maxY;
        final float minY;
       
        if (mWidthHeight.length2() > 0) {
            maxX = Math.max(getMaxX(), other.getMaxX());
            minX = Math.max(getMinX(), other.getMinX());
            maxY = Math.max(getMaxY(), other.getMaxY());
            minY = Math.max(getMinY(), other.getMinY());
        } else {
            maxX = other.getMaxX();
            minX = other.getMinX();
            maxY = other.getMaxY();
            minY = other.getMinY();
        }
        final float horizontalDelta = maxX - minX;
        final float verticalDelta = maxY - minY;
        mBottomLeft.set(minX, minY);
        mWidthHeight.set(horizontalDelta, verticalDelta);
    }

}

