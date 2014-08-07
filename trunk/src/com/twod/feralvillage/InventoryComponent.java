package com.twod.feralvillage;

public class InventoryComponent extends GameComponent {
    private UpdateRecord mInventory;
    private boolean mInventoryChanged;
   
    public InventoryComponent() {
        super();
        mInventory = new UpdateRecord();
        reset();
        setPhase(ComponentPhases.FRAME_END.ordinal());
    }
   
    @Override
    public void reset() {
        mInventoryChanged = true;
        mInventory.reset();
    }
   
    public void applyUpdate(UpdateRecord record) {
        mInventory.add(record);
        mInventoryChanged = true;
    }
   
    @Override
    public void update(float timeDelta, BaseObject parent) {
        if (mInventoryChanged) {
            HudSystem hud = sSystemRegistry.hudSystem;
            if (hud != null) {
                hud.updateInventory(mInventory);
            }
            mInventoryChanged = false;
        }
    }
   
    public UpdateRecord getRecord() {
        return mInventory;
    }
   
    public void setChanged() {
        mInventoryChanged = true;
    }
   
    public static class UpdateRecord extends BaseObject {
        public int rubyCount;
        public int coinCount;
        public int diaryCount;
       
        public UpdateRecord() {
            super();
        }
       
        public void reset() {
            rubyCount = 0;
            coinCount = 0;
            diaryCount = 0;
        }
       
        public void add(UpdateRecord other) {
            rubyCount += other.rubyCount;
            coinCount += other.coinCount;
            diaryCount += other.diaryCount;
        }
    }
}

