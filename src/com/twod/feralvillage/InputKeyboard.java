package com.twod.feralvillage;

import android.view.KeyEvent;

public class InputKeyboard {
        private InputButton[] mKeys;
       
        public InputKeyboard() {
                final int count = KeyEvent.getMaxKeyCode();
                mKeys = new InputButton[count];
                for (int x = 0; x < count; x++) {
                        mKeys[x] = new InputButton();
                }
        }
       
        public void press(float currentTime, int keycode) {
                assert keycode >= 0 && keycode < mKeys.length;  
                if (keycode >= 0 && keycode < mKeys.length){
                        mKeys[keycode].press(currentTime, 1.0f);
                }
        }
       
        public void release(int keycode) {
                assert keycode >= 0 && keycode < mKeys.length;
                if (keycode >= 0 && keycode < mKeys.length){
                        mKeys[keycode].release();
                }
        }
       
        public void releaseAll() {
                final int count = mKeys.length;
                for (int x = 0; x < count; x++) {
                        mKeys[x].release();
                }
        }
       
        public InputButton[] getKeys() {
                return mKeys;
        }
       
        public void resetAll() {
                final int count = mKeys.length;
                for (int x = 0; x < count; x++) {
                        mKeys[x].reset();
                }
        }
}

