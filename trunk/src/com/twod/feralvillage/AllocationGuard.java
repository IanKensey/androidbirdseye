package com.twod.feralvillage;

public class AllocationGuard {
    public static boolean sGuardActive = false;
    public AllocationGuard() {
        if (sGuardActive) {
            // An allocation has occurred while the guard is active!  Report it.
            DebugLog.e("AllocGuard", "An allocation of type " + this.getClass().getName()
                    + " occurred while the AllocGuard is active.");
           
           
        }
    }
}
