package com.twod.feralvillage;

import android.content.Context;

/** Contains global (but typically constant) parameters about the current operating context */
public class ContextParameters extends BaseObject {
    public int viewWidth;
    public int viewHeight;
    public Context context;
        public int gameWidth;
        public int gameHeight;
        public float viewScaleX;
        public float viewScaleY;
        public boolean supportsDrawTexture;
        public boolean supportsVBOs;
        public int difficulty;
   
    public ContextParameters() {
        super();
    }
   
    @Override
    public void reset() {
       
    }
}

