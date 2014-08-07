package com.twod.feralvillage;

import android.content.Context;

public class GameFlowEvent implements Runnable {
        public static final int EVENT_INVALID = -1;
    public static final int EVENT_RESTART_LEVEL = 0;
    public static final int EVENT_END_GAME = 1;
    public static final int EVENT_GO_TO_NEXT_LEVEL = 2;
    public static final int EVENT_GO_TO_PREVIOUS_LEVEL = 7;
   
    public static final int EVENT_SHOW_DIARY = 3;
    public static final int EVENT_SHOW_DIALOG_CHARACTER1 = 4;
    public static final int EVENT_SHOW_DIALOG_CHARACTER2 = 5;
        public static final int EVENT_SHOW_ANIMATION = 6;
        
    public static final int EVENT_GO_TO_HOUSE_01 = 8;
    public static final int EVENT_GO_TO_HOUSE_02 = 9;
    public static final int EVENT_GO_TO_HOUSE_03 = 10;
    public static final int EVENT_GO_TO_VILLAGE_01 = 11;
    public static final int EVENT_GO_TO_VILLAGE_02 = 12;
    public static final int EVENT_GO_TO_VILLAGE_03 = 13;

    private int mEventCode;
    private int mDataIndex;
    private AndouKun mMainActivity;
   
    public void post(int event, int index, Context context) {
        if (context instanceof AndouKun) {
                DebugLog.d("GameFlowEvent", "Post Game Flow Event: " + event + ", " + index);
            mEventCode = event;
            mDataIndex = index;
            mMainActivity = (AndouKun)context;
            mMainActivity.runOnUiThread(this);
        }
    }
   
    public void postImmediate(int event, int index, Context context) {
        if (context instanceof AndouKun) {
                DebugLog.d("GameFlowEvent", "Execute Immediate Game Flow Event: " + event + ", " + index);
            mEventCode = event;
            mDataIndex = index;
            mMainActivity = (AndouKun)context;
            mMainActivity.onGameFlowEvent(mEventCode, mDataIndex);
        }
    }
   
    public void run() {
        if (mMainActivity != null) {
                DebugLog.d("GameFlowEvent", "Execute Game Flow Event: " + mEventCode + ", " + mDataIndex);
            mMainActivity.onGameFlowEvent(mEventCode, mDataIndex);
            mMainActivity = null;
        }
    }

}

