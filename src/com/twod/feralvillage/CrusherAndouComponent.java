package com.twod.feralvillage;

public class CrusherAndouComponent extends GameComponent {
    private ChangeComponentsComponent mSwap;
    
    public CrusherAndouComponent() {
        super();
        setPhase(ComponentPhases.THINK.ordinal());
        reset();
    }
    
    @Override
    public void reset() {
        mSwap = null;
    }
    
    @Override
    public void update(float timeDelta, BaseObject parent) {
        GameObject parentObject = (GameObject)parent;
        
        if (mSwap.getCurrentlySwapped()) {
                if (parentObject.touchingGround()) {
                        parentObject.setCurrentAction(GameObject.ActionType.IDLE);
                }
        } else {
                InputSystem input = sSystemRegistry.inputSystem;
                if (input.getTouchScreen().getTriggered(sSystemRegistry.timeSystem.getGameTime())) {
                        parentObject.setCurrentAction(GameObject.ActionType.ATTACK);
                        mSwap.activate(parentObject);
                }
        }
    }
    
    public void setSwap(ChangeComponentsComponent swap) {
        mSwap = swap;
    }
}
