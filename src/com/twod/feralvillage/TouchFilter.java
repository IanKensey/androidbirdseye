package com.twod.feralvillage;

import android.content.Context;
import android.view.MotionEvent;

public abstract class TouchFilter extends BaseObject {

	public abstract void updateTouch(MotionEvent event);
	
	public boolean supportsMultitouch(Context context) {
		return false;
	}
	
	@Override
	public void reset() {
	}

}