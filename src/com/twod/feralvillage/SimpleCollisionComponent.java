package com.twod.feralvillage;

public class SimpleCollisionComponent extends GameComponent {
	private Vector2 mPreviousPosition;
	private Vector2 mCurrentPosition;
	private Vector2 mMovementDirection;
	private Vector2 mHitPoint;
	private Vector2 mHitNormal;
	
	public SimpleCollisionComponent() {
		super();
		setPhase(ComponentPhases.COLLISION_DETECTION.ordinal());
		mPreviousPosition = new Vector2();
		mCurrentPosition = new Vector2();
		mMovementDirection = new Vector2();
		mHitPoint = new Vector2();
		mHitNormal = new Vector2();
	}
	
	@Override
	public void reset() {
		mPreviousPosition.zero();
		mCurrentPosition.zero();
		mMovementDirection.zero();
		mHitPoint.zero();
		mHitNormal.zero();
	}
	
	@Override
    public void update(float timeDelta, BaseObject parent) {
        GameObject parentObject = (GameObject) parent;
        
        if (mPreviousPosition.length2() > 0.0f) {
        	mCurrentPosition.set(parentObject.getCenteredPositionX(), parentObject.getCenteredPositionY());
        	mMovementDirection.set(mCurrentPosition);
        	mMovementDirection.subtract(mPreviousPosition);
        	if (mMovementDirection.length2() > 0.0f) {
        		final CollisionSystem collision = sSystemRegistry.collisionSystem;
        		if (collision != null) {
        			final boolean hit = collision.castRay(mPreviousPosition, mCurrentPosition, 
        					mMovementDirection, mHitPoint, mHitNormal, parentObject);
        			
        			if (hit) {
        				// snap
        				final float halfWidth = parentObject.width / 2.0f;
        				final float halfHeight = parentObject.height / 2.0f;
        				if (!Utils.close(mHitNormal.x, 0.0f)) {
        					parentObject.getPosition().x = mHitPoint.x - halfWidth;
        				} 
        				
        				if (!Utils.close(mHitNormal.y, 0.0f)) {
        					parentObject.getPosition().y = mHitPoint.y - halfHeight;
        				}
        				
        				final TimeSystem timeSystem = sSystemRegistry.timeSystem;

    	                if (timeSystem != null) {
    	                    float time = timeSystem.getGameTime();
    	                   if (mHitNormal.x > 0.0f) {
	                            parentObject.setLastTouchedLeftWallTime(time);
	                        } else if (mHitNormal.x < 0.0) {
	                            parentObject.setLastTouchedRightWallTime(time);
	                        }
	                   
	                        if (mHitNormal.y > 0.0f) {
	                            parentObject.setLastTouchedFloorTime(time);
	                        } else if (mHitNormal.y < 0.0f) {
	                            parentObject.setLastTouchedCeilingTime(time);
	                        }
    	                }
    	                    
    	                parentObject.setBackgroundCollisionNormal(mHitNormal);
    	                    
        			}
        		}
        	}
        }
        
        mPreviousPosition.set(parentObject.getCenteredPositionX(), parentObject.getCenteredPositionY());
	}
}