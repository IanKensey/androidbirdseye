package com.twod.feralvillage;

import java.lang.reflect.Method;

import android.app.Activity;

public class UIConstants {

	// Some versions of Android can support custom Activity transitions.
	// If this method isn't null, we can use them.
	
	public static Method mOverridePendingTransition;
	
    static {
    	try {
    		mOverridePendingTransition = Activity.class.getMethod(
                    "overridePendingTransition", new Class[] { Integer.TYPE, Integer.TYPE } );
            /* success, this is a newer device */
        } catch (NoSuchMethodException nsme) {
            /* failure, must be older device */
        }    
    };
}