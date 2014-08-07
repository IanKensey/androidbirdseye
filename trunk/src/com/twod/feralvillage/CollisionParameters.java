package com.twod.feralvillage;

public final class CollisionParameters {
    // HitType describes the type of hit that a victim object receives.  Victims may choose to 
    // react differently to the intersection depending on the hit type.
        // TODO: Make this a bit field so that objects can support multiple hit types.
    public final class HitType {
        public final static int INVALID = 0;    // No type.
        public final static int HIT = 1;        // Standard hit type.  Life is reduced by 1.
        public final static int DEATH = 2;      // Causes instant death.
        public final static int COLLECT = 3;    // Causes collectable objects to be collected by the attacker.
        public final static int POSSESS = 4;    // Causes possessable objects to become possessed.
        public final static int DEPRESS = 5;    // A hit indicating that the attacker is pressing into the victim.
        public final static int LAUNCH = 6;     // A hit indicating that the attacker will launch the victim.
    }
    
   
}
