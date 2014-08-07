package com.twod.feralvillage;

public class HotSpotSystem extends BaseObject {
    TiledWorld mWorld;
    
    public class HotSpotType {
        public static final int NONE = -1;
        public static final int GO_RIGHT = 0;
        public static final int GO_LEFT = 1;
        public static final int GO_UP = 2;
        public static final int GO_DOWN = 3;
        
        public static final int WAIT_SHORT = 4;
        public static final int WAIT_MEDIUM = 5;
        public static final int WAIT_LONG = 6;
        
        public static final int ATTACK = 7;
        public static final int TALK = 8;
        public static final int DIE = 9;
        public static final int WALK_AND_TALK = 10;
        public static final int TAKE_CAMERA_FOCUS = 11;
        public static final int RELEASE_CAMERA_FOCUS = 12;
        public static final int END_LEVEL = 13;
        public static final int GAME_EVENT = 14;
        public static final int NPC_RUN_QUEUED_COMMANDS = 15;

        public static final int NPC_GO_RIGHT = 16;
        public static final int NPC_GO_LEFT = 17;
        public static final int NPC_GO_UP = 18;
        public static final int NPC_GO_DOWN = 19;
        public static final int NPC_GO_UP_RIGHT = 20;
        public static final int NPC_GO_UP_LEFT = 21;
        public static final int NPC_GO_DOWN_LEFT = 22;
        public static final int NPC_GO_DOWN_RIGHT = 23;
        public static final int NPC_GO_TOWARDS_PLAYER = 24;
        public static final int NPC_GO_RANDOM = 25;
        public static final int NPC_GO_UP_FROM_GROUND = 26;
        public static final int NPC_GO_DOWN_FROM_CEILING = 27;
        public static final int NPC_STOP = 28;
        public static final int NPC_SLOW = 29;
        public static final int OUTSIDE = 30;
        public static final int INSIDE = 31;

        
        public static final int NPC_SELECT_DIALOG_1_1 = 32;
        public static final int NPC_SELECT_DIALOG_1_2 = 33;
        public static final int NPC_SELECT_DIALOG_1_3 = 34;
        public static final int NPC_SELECT_DIALOG_1_4 = 35;
        public static final int NPC_SELECT_DIALOG_1_5 = 36;
        
        public static final int NPC_SELECT_DIALOG_2_1 = 38;
        public static final int NPC_SELECT_DIALOG_2_2 = 39;
        public static final int NPC_SELECT_DIALOG_2_3 = 40;
        public static final int NPC_SELECT_DIALOG_2_4 = 41;
        public static final int NPC_SELECT_DIALOG_2_5 = 42;
        

        public static final int IN_HOUSE_01 = 48;
        public static final int IN_HOUSE_02 = 49;
        public static final int IN_HOUSE_03 = 50;
        public static final int OUT_HOUSE_01 = 51;
        public static final int OUT_HOUSE_02 = 52;
        public static final int OUT_HOUSE_03 = 53;
        


    }
    
    public HotSpotSystem() {
        super();
    }
    
    @Override
    public void reset() {
        mWorld = null;
    }
    
    public final void setWorld(TiledWorld world) {
        mWorld = world;
    }
    
    public int getHotSpot(float worldX, float worldY) {
        //TOOD: take a region?  how do we deal with multiple hot spot intersections?
        int result = HotSpotType.NONE;
        if (mWorld != null) {
            
            final int xTile = getHitTileX(worldX);
            final int yTile = getHitTileY(worldY);
            
            result = mWorld.getTile(xTile, yTile);
        }
        
        return result;
    }
    
    public int getHotSpotByTile(int tileX, int tileY) {
        //TOOD: take a region?  how do we deal with multiple hot spot intersections?
        int result = HotSpotType.NONE;
        if (mWorld != null) {     
            result = mWorld.getTile(tileX, tileY);
        }
        
        return result;
    }
    
    public final int getHitTileX(float worldX) {
        int xTile = 0;
        LevelSystem level = sSystemRegistry.levelSystem;
        if (mWorld != null && level != null) {
            final float worldPixelWidth = level.getLevelWidth();
            xTile = (int)Math.floor(((worldX) / worldPixelWidth) * mWorld.getWidth());
        }
        return xTile;
    }
    
    public final int getHitTileY(float worldY) {
        int yTile = 0;
        LevelSystem level = sSystemRegistry.levelSystem;
        if (mWorld != null && level != null) {
            final float worldPixelHeight = level.getLevelHeight();
            // TODO: it is stupid to keep doing this space conversion all over the code.  Fix this
            // in the TiledWorld code!
            final float flippedY = worldPixelHeight - (worldY);
            yTile = (int)Math.floor((flippedY / worldPixelHeight) * mWorld.getHeight());
        }
        return yTile;
    }
    
    public final float getTileCenterWorldPositionX(int tileX) {
        float worldX = 0.0f;
        LevelSystem level = sSystemRegistry.levelSystem;
        if (mWorld != null && level != null) {
            final float tileWidth = level.getLevelWidth() / mWorld.getWidth();
            worldX = (tileX * tileWidth) + (tileWidth / 2.0f);
        }
        return worldX;
    }
    
}
