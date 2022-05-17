package com.example.wm.Login_Register;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wm.Class.WebService_Class;
import com.example.wm.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends Activity {
    private TextView textView;
    private String date;
    private ImageView splash;
    private Animation slide_down_anim,slide_up_anim,fade_in_anim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);

        textView = findViewById(R.id.id_Version);
        splash = findViewById(R.id.splash);
        Top_Bg();
        date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());


        try {
            PackageInfo pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            String version = pInfo.versionName;
            textView.setText(String.format("Version: %s", version));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }



       /* new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
                finish();
            }
        }, 4000);*/
        new WebService_Class(this);
        if ( WebService_Class.getPin() == null) {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
                finish();
            }
        }, 4000);

        } else {
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    finish();
                }
            }, 4000);

        }


    }

    private void Top_Bg() {
        slide_down_anim = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.slide_down);
        slide_up_anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
        fade_in_anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade);
        splash.startAnimation(fade_in_anim);
        textView.startAnimation(fade_in_anim);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
               /* Intent intent = new Intent(RegisterActivity.this, NextActivity.class);
                startActivity(intent);*/

            }
        }, 5000);
    }

    @Override
    public void onBackPressed() {

    }


}
