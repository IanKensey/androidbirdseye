package com.twod.feralvillage;

import com.twod.feralvillage.CollisionParameters.HitType;


public class SelectDialogComponent extends GameComponent {
    private HitReactionComponent mHitReact;
    private Vector2 mLastPosition;
    
    public SelectDialogComponent() {
        super();
        setPhase(ComponentPhases.THINK.ordinal());
        mLastPosition = new Vector2();
    }
    
    @Override
    public void reset() {
    	mHitReact = null;
    	mLastPosition.zero();
    }

    @Override
    public void update(float timeDelta, BaseObject parent) {
    	HotSpotSystem hotSpot = sSystemRegistry.hotSpotSystem;
    	if (hotSpot != null && mHitReact != null) {
    		GameObject parentObject = (GameObject)parent;
    		final Vector2 currentPosition = parentObject.getPosition();
    		if (mLastPosition.distance2(parentObject.getPosition()) > 0.0f) {
    			mLastPosition.set(currentPosition);
    			
    			final int hitSpot = hotSpot.getHotSpot(parentObject.getCenteredPositionX(), currentPosition.y + 10);
    			switch(hitSpot) {
	    			case HotSpotSystem.HotSpotType.NPC_SELECT_DIALOG_1_1:
	    	        case HotSpotSystem.HotSpotType.NPC_SELECT_DIALOG_1_2:
	    	        case HotSpotSystem.HotSpotType.NPC_SELECT_DIALOG_1_3:
	    	        case HotSpotSystem.HotSpotType.NPC_SELECT_DIALOG_1_4:
	    	        case HotSpotSystem.HotSpotType.NPC_SELECT_DIALOG_1_5:
	    	        case HotSpotSystem.HotSpotType.NPC_SELECT_DIALOG_2_1:
	    	        case HotSpotSystem.HotSpotType.NPC_SELECT_DIALOG_2_2:
	    	        case HotSpotSystem.HotSpotType.NPC_SELECT_DIALOG_2_3:
	    	        case HotSpotSystem.HotSpotType.NPC_SELECT_DIALOG_2_4:
	    	        case HotSpotSystem.HotSpotType.NPC_SELECT_DIALOG_2_5:
	    	        {
	    	        	int event = GameFlowEvent.EVENT_SHOW_DIALOG_CHARACTER1;
	    	        	int index = hitSpot - HotSpotSystem.HotSpotType.NPC_SELECT_DIALOG_1_1;
	    	        	
	    	        	if (hitSpot >= HotSpotSystem.HotSpotType.NPC_SELECT_DIALOG_2_1) {
	    	        		event = GameFlowEvent.EVENT_SHOW_DIALOG_CHARACTER2;
	    	        		index = hitSpot - HotSpotSystem.HotSpotType.NPC_SELECT_DIALOG_2_1;
	    	        	}
	    	        	
	    	        	mHitReact.setSpawnGameEventOnHit(HitType.COLLECT, event, index);
	    	        }
	    	        	break;
    			}
    		}
    	}
    }

    public void setHitReact(HitReactionComponent hit) {
        mHitReact = hit;
    }
}