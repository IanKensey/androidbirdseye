package com.twod.feralvillage;

import java.util.Arrays;

/**
 * Describes a single animation for a sprite.
 */
public class SpriteAnimation extends PhasedObject {
    private final static int LINEAR_SEARCH_CUTOFF = 16;
    
    private FixedSizeArray<AnimationFrame> mFrames;
    private float[] mFrameStartTimes;
    private boolean mLoop;
    private float mLength;
    
    public SpriteAnimation(int animationId, int frameCount) {
        super();
        mFrames = new FixedSizeArray<AnimationFrame>(frameCount);
        mFrameStartTimes = new float[frameCount];
        mLoop = false;
        mLength = 0.0f;
        setPhase(animationId);
    }

    public AnimationFrame getFrame(float animationTime) {
        AnimationFrame result = null;
        final float length = mLength;
        if (length > 0.0f) {
        	final FixedSizeArray<AnimationFrame> frames = mFrames;
        	assert frames.getCount() == frames.getCapacity();
            final int frameCount = frames.getCount();
            result = frames.get(frameCount - 1);

            if (frameCount > 1) {
	            float currentTime = 0.0f;
	            float cycleTime = animationTime;
	            if (mLoop) {
	                cycleTime = animationTime % length;
	            }
	
	            if (cycleTime < length) {
	            	// When there are very few frames it's actually slower to do a binary search
	            	// of the frame list.  So we'll use a linear search for small animations
	            	// and only pull the binary search out when the frame count is large.
	            	if (mFrameStartTimes.length > LINEAR_SEARCH_CUTOFF) {
		            	int index = Arrays.binarySearch(mFrameStartTimes, cycleTime);
		            	if (index < 0) {
		            		index = -(index + 1) - 1;
		            	}
		            	result = frames.get(index);
	            	} else {
		                for (int x = 0; x < frameCount; x++) {
		                    AnimationFrame frame = frames.get(x);
		                    currentTime += frame.holdTime;
		                    if (currentTime > cycleTime) {
		                        result = frame;
		                        break;
		                    }
		                }
	            	}
	            }
	        }
        }
        return result;
    }

    public void addFrame(AnimationFrame frame) {
    	mFrameStartTimes[mFrames.getCount()] = mLength;
    	mFrames.add(frame);
        mLength += frame.holdTime;
    }
    
    public float getLength() {
        return mLength;
    }

    public void setLoop(boolean loop) {
        mLoop = loop;
    }

    public boolean getLoop() {
        return mLoop;
    }
}