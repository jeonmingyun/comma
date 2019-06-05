package com.org.ticketzone.app_mem.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.org.ticketzone.app_mem.R;

public class LoadingActivity extends Activity {
    private LinearLayout loadinglayout;
    private Animation anim;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        startLoading();
    }

    private void startLoading() {
        loadinglayout = findViewById(R.id.Loadinglayout);
        anim = AnimationUtils.loadAnimation(LoadingActivity.this, R.anim.loading);
        loadinglayout.setAnimation(anim);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable(){
            @Override
            public void run(){

                finish();
            }
        },3000);
    }
}
