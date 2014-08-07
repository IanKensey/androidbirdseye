package com.twod.feralvillage;

public class EventRecorder extends BaseObject {
        public final static int COUNTER_ROBOTS_DESTROYED = 0;
        public final static int COUNTER_PEARLS_COLLECTED = 1;
        public final static int COUNTER_PEARLS_TOTAL = 2;
       
        private Vector2 mLastDeathPosition = new Vector2();
        private int mLastEnding = -1;
        private int mRobotsDestroyed = 0;
        private int mPearlsCollected = 0;
        private int mPearlsTotal = 0;
       
        @Override
        public void reset() {
                mRobotsDestroyed = 0;
                mPearlsCollected = 0;
                mPearlsTotal = 0;
        }
       
        synchronized void setLastDeathPosition(Vector2 position) {
                mLastDeathPosition.set(position);
        }
       
        synchronized Vector2 getLastDeathPosition() {
                return mLastDeathPosition;
        }
       
        synchronized void setLastEnding(int ending) {
                mLastEnding = ending;
        }
       
        synchronized int getLastEnding() {
                return mLastEnding;
        }
       
        synchronized void incrementEventCounter(int event) {
                if (event == COUNTER_ROBOTS_DESTROYED) {
                        mRobotsDestroyed++;
                } else if (event == COUNTER_PEARLS_COLLECTED) {
                        mPearlsCollected++;
                } else if (event == COUNTER_PEARLS_TOTAL) {
                        mPearlsTotal++;
                }
        }
       
        synchronized int getRobotsDestroyed() {
                return mRobotsDestroyed;
        }
       
        synchronized int getPearlsCollected() {
                return mPearlsCollected;
        }

        synchronized int getPearlsTotal() {
                return mPearlsTotal;
        }
}

