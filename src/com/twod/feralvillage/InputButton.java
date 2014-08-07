package com.twod.feralvillage;

public class InputButton {
        private boolean mDown;
        private float mLastPressedTime;
        private float mDownTime;
        private float mMagnitude;
       
        public void press(float currentTime, float magnitude) {
                if (!mDown) {
                        mDown = true;
                        mDownTime = currentTime;
                }
                mMagnitude = magnitude;
                mLastPressedTime = currentTime;
        }
       
        public void release() {
                mDown = false;
        }

        public final boolean getPressed() {
                return mDown;
        }
       
        public final boolean getTriggered(float currentTime) {
                return mDown && currentTime - mDownTime <= BaseObject.sSystemRegistry.timeSystem.getFrameDelta() * 2.0f;
        }
       
        public final float getPressedDuration(float currentTime) {
                return currentTime - mDownTime;
        }
       
        public final float getLastPressedTime() {
                return mLastPressedTime;
        }
       
        public final float getMagnitude() {
                float magnitude = 0.0f;
                if (mDown) {
                        magnitude = mMagnitude;
                }
                return magnitude;
        }
       
        public final void setMagnitude(float magnitude) {
                mMagnitude = magnitude;
        }
       
        public final void reset() {
                mDown = false;
                mMagnitude = 0.0f;
                mLastPressedTime = 0.0f;
                mDownTime = 0.0f;
        }
}

