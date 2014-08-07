package com.twod.feralvillage;

import android.os.Vibrator;
import android.content.Context;

/** A system for accessing the Android vibrator.  Note that this system requires the app's 
 * AndroidManifest.xml to contain permissions for the Vibrator service.
 */
public class VibrationSystem extends BaseObject {

    public VibrationSystem() {
        super();
    }
    
    @Override
    public void reset() {
    }
    
    public void vibrate(float seconds) {
        ContextParameters params = sSystemRegistry.contextParameters;
        if (params != null && params.context != null) {
            Vibrator vibrator = (Vibrator)params.context.getSystemService(Context.VIBRATOR_SERVICE);
            if (vibrator != null) {
                vibrator.vibrate((int)(seconds * 1000));
            }
        }
    }
}