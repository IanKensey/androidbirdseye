package com.twod.feralvillage;

import java.lang.reflect.InvocationTargetException;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DiaryActivity extends Activity {

        private OnClickListener mKillDiaryListener = new OnClickListener() {
                public void onClick(View arg0) {
                        finish();              
                        if (UIConstants.mOverridePendingTransition != null) {
                       try {
                          UIConstants.mOverridePendingTransition.invoke(DiaryActivity.this, R.anim.activity_fade_in, R.anim.activity_fade_out);
                       } catch (InvocationTargetException ite) {
                           DebugLog.d("Activity Transition", "Invocation Target Exception");
                       } catch (IllegalAccessException ie) {
                           DebugLog.d("Activity Transition", "Illegal Access Exception");
                       }
                        }
                }
    };
       
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diary);
       
        TextView text = (TextView)findViewById(R.id.diarytext);
       
        ImageView image = (ImageView)findViewById(R.id.diarybackground);
        image.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fade));
        final Intent callingIntent = getIntent();
        final int textResource = callingIntent.getIntExtra("text", -1);
       
        if (textResource != -1) {
            text.setText(textResource);
        }
       
        ImageView okArrow = (ImageView)findViewById(R.id.ok);
        okArrow.setOnClickListener(mKillDiaryListener);
        okArrow.setBackgroundResource(R.anim.ui_button);
        AnimationDrawable anim = (AnimationDrawable) okArrow.getBackground();
        anim.start();
       
        BaseObject.sSystemRegistry.customToastSystem.toast(getString(R.string.diary_found), Toast.LENGTH_SHORT);
       
    }
   
   
   

}

