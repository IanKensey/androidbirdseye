package com.twod.feralvillage;

public class AttackAtDistanceComponent extends GameComponent {
    private static final int DEFAULT_ATTACK_DISTANCE = 100;
    private float mAttackDistance;
    private float mAttackDelay;
    private float mAttackLength;
    private float mAttackStartTime;
    private boolean mRequireFacing;
    private Vector2 mDistance;
   
    public AttackAtDistanceComponent() {
        super();
        setPhase(GameComponent.ComponentPhases.THINK.ordinal());
        mDistance = new Vector2();
        reset();
    }
   
    @Override
    public void reset() {
        mAttackDelay = 0;
        mAttackLength = 0;
        mAttackDistance = DEFAULT_ATTACK_DISTANCE;
        mRequireFacing = false;
    }
   
    @Override
    public void update(float timeDelta, BaseObject parent) {  
        GameObject parentObject = (GameObject) parent;

        GameObjectManager manager = sSystemRegistry.gameObjectManager;
        if (manager != null) {
            GameObject player = manager.getPlayer();
            if (player != null) {
                mDistance.set(player.getPosition());
                mDistance.subtract(parentObject.getPosition());
               
                TimeSystem time = sSystemRegistry.timeSystem;
                final float currentTime = time.getGameTime();
                final boolean facingPlayer =
                    (Utils.sign(player.getPosition().x - parentObject.getPosition().x)
                        == Utils.sign(parentObject.facingDirection.x));
                final boolean facingDirectionCorrect = (mRequireFacing && facingPlayer)
                    || !mRequireFacing;
                if (parentObject.getCurrentAction() == GameObject.ActionType.ATTACK) {
                    if (currentTime > mAttackStartTime + mAttackLength) {
                        parentObject.setCurrentAction(GameObject.ActionType.IDLE);
                    }
                } else if (mDistance.length2() < (mAttackDistance * mAttackDistance)
                            && currentTime > mAttackStartTime + mAttackLength + mAttackDelay
                            && facingDirectionCorrect) {
                    mAttackStartTime = currentTime;
                    parentObject.setCurrentAction(GameObject.ActionType.ATTACK);
                } else {
                    parentObject.setCurrentAction(GameObject.ActionType.IDLE);
                }
            }
        }
       
    }

    public void setupAttack(float distance, float delay, float duration, boolean requireFacing) {
        mAttackDistance = distance;
        mAttackDelay = delay;
        mAttackLength = duration;
        mRequireFacing = requireFacing;
    }
   
 
}
