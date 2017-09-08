package com.linghao.projecttraining.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.RelativeLayout;

import com.linghao.projecttraining.R;

import static android.R.attr.handle;

public class SplashActivity extends Activity {
    private Handler handler;
    private RelativeLayout splash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        splash= (RelativeLayout) findViewById(R.id.splash);
        handler=new Handler();
        setAnimatin();
    }

    private void setAnimatin() {
        AlphaAnimation alphaAnimation=new AlphaAnimation((float) 0.5,1);
        alphaAnimation.setDuration(2000);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                toMain();

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }



            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        splash.startAnimation(alphaAnimation);
    }

    private void toMain() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(SplashActivity.this,LoginActivity.class);
                startActivity(intent);
                   finish();
            }
        },2000);
    }
}
