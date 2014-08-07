package com.twod.feralvillage;

public class EnemyAnimationComponent extends GameComponent {
    
    public enum EnemyAnimations {
        IDLE,
        MOVE,
        ATTACK,
        HIDDEN,
        APPEAR,
    }
    
    private enum AnimationState {
        IDLING,
        MOVING,
        HIDING,
        APPEARING,
        ATTACKING
    }
    
    private SpriteComponent mSprite;
    private AnimationState mState;
    private boolean mFacePlayer;
    
    public EnemyAnimationComponent() {
        super();
        setPhase(ComponentPhases.ANIMATION.ordinal());
        reset();
    }
    
    @Override
    public void reset() {
        mState = AnimationState.IDLING;
        mFacePlayer = false;
        mSprite = null;
    }
    
    @Override
    public void update(float timeDelta, BaseObject parent) {
        if (mSprite != null) {
            GameObject parentObject = (GameObject) parent;
            final float velocityX = parentObject.getVelocity().x;

            GameObject.ActionType currentAction = parentObject.getCurrentAction();

            switch(mState) {
                case IDLING:
                    mSprite.playAnimation(EnemyAnimations.IDLE.ordinal());
                    if (mFacePlayer) {
                        facePlayer(parentObject);
                    }
                    
                    if (currentAction == GameObject.ActionType.ATTACK) {
                        mState = AnimationState.ATTACKING;
                    } else if (currentAction == GameObject.ActionType.HIDE) {
                        mState = AnimationState.HIDING;
                    } else if (Math.abs(velocityX) > 0.0f) {
                        mState = AnimationState.MOVING;
                    } 
                    
                    break;
                    
                case MOVING:
                    mSprite.playAnimation(EnemyAnimations.MOVE.ordinal());
                    final float targetVelocityX = parentObject.getTargetVelocity().x;
                    
                    if (!Utils.close(velocityX, 0.0f)) {
                        if (velocityX < 0.0f && targetVelocityX < 0.0f) {
                            parentObject.facingDirection.x = -1.0f;
                        } else if (velocityX > 0.0f && targetVelocityX > 0.0f) {
                            parentObject.facingDirection.x = 1.0f;
                        }
                    }
                    if (currentAction == GameObject.ActionType.ATTACK) {
                        mState = AnimationState.ATTACKING;
                    } else if (currentAction == GameObject.ActionType.HIDE) {
                        mState = AnimationState.HIDING;
                    } else if (Math.abs(velocityX) == 0.0f) {
                        mState = AnimationState.IDLING;
                    }
                    break;
                case ATTACKING:
                    mSprite.playAnimation(EnemyAnimations.ATTACK.ordinal());
                    if (currentAction != GameObject.ActionType.ATTACK 
                            && mSprite.animationFinished()) {
                        mState = AnimationState.IDLING;
                    }
                    break;
                case HIDING:
                    mSprite.playAnimation(EnemyAnimations.HIDDEN.ordinal());
                    if (currentAction != GameObject.ActionType.HIDE) {
                        mState = AnimationState.APPEARING;
                    }
                    break;
                case APPEARING:
                    if (mFacePlayer) {
                        facePlayer(parentObject);
                    }
                    
                    mSprite.playAnimation(EnemyAnimations.APPEAR.ordinal());
                    if (mSprite.animationFinished()) {
                        mState = AnimationState.IDLING;
                    }
                    break;
                    
            }
           
            
        }
    }
    
    private void facePlayer(GameObject parentObject) {
        GameObjectManager manager = sSystemRegistry.gameObjectManager;
        if (manager != null) {
            GameObject player = manager.getPlayer();
            if (player != null) {
                if (player.getPosition().x < parentObject.getPosition().x) {
                    parentObject.facingDirection.x = -1.0f;
                } else {
                    parentObject.facingDirection.x = 1.0f;
                }
            }
        }  
    }
    
    public void setSprite(SpriteComponent sprite) {
        mSprite = sprite;
    }
    
    public void setFacePlayer(boolean facePlayer) {
        mFacePlayer = facePlayer;
    }
}
