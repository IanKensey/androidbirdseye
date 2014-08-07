package com.twod.feralvillage;

public class PhasedObject extends BaseObject {

    public int phase;   // This is public because the phased is accessed extremely often, so much
                        // so that the function overhead of an getter is non-trivial.  

    public PhasedObject() {
        super();
    }
   
    @Override
    public void reset() {
       
    }

    public void setPhase(int phaseValue) {
        phase = phaseValue;
    }
}

