package com.twod.feralvillage;

public class BabyDifficultyConstants extends DifficultyConstants {

        private static final float FUEL_AIR_REFILL_SPEED = 0.22f;
    private static final float FUEL_GROUND_REFILL_SPEED = 4.0f;
        public static final int MAX_PLAYER_LIFE = 5;
        private static final int COINS_PER_POWERUP = 15;
             
        public static final float GLOW_DURATION = 20.0f;
           
        // DDA boosts
        private static final int DDA_STAGE_1_ATTEMPTS = 3;
        private static final int DDA_STAGE_2_ATTEMPTS = 5;
        private static final int DDA_STAGE_1_LIFE_BOOST = 1;
        private static final int DDA_STAGE_2_LIFE_BOOST = 3;
        private static final float DDA_STAGE_1_FUEL_AIR_REFILL_SPEED = 0.30f;
        private static final float DDA_STAGE_2_FUEL_AIR_REFILL_SPEED = 0.40f;
       
        @Override
        public float getFuelAirRefillSpeed() {
                return FUEL_AIR_REFILL_SPEED;
        }

        @Override
        public float getFuelGroundRefillSpeed() {
                return FUEL_GROUND_REFILL_SPEED;
        }

        @Override
        public int getMaxPlayerLife() {
                return MAX_PLAYER_LIFE;
        }

        @Override
        public int getCoinsPerPowerup() {
                return COINS_PER_POWERUP;
        }

        @Override
        public float getGlowDuration() {
                return GLOW_DURATION;
        }

        @Override
        public int getDDAStage1Attempts() {
                return DDA_STAGE_1_ATTEMPTS;
        }

        @Override
        public int getDDAStage2Attempts() {
                return DDA_STAGE_2_ATTEMPTS;
        }

        @Override
        public int getDDAStage1LifeBoost() {
                return DDA_STAGE_1_LIFE_BOOST;
        }

        @Override
        public int getDDAStage2LifeBoost() {
                return DDA_STAGE_2_LIFE_BOOST;
        }

        @Override
        public float getDDAStage1FuelAirRefillSpeed() {
                return DDA_STAGE_1_FUEL_AIR_REFILL_SPEED;
        }

        @Override
        public float getDDAStage2FuelAirRefillSpeed() {
                return DDA_STAGE_2_FUEL_AIR_REFILL_SPEED;
        }

}
