package com.example.wm.Login_Register;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;

import com.example.wm.Fragment.LoginFragment;
import com.example.wm.Fragment.RegisterFragment;
import com.example.wm.R;
import com.example.wm.WebService_Class;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);

        TextView textView = findViewById(R.id.id_Version);
        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());


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



    @Override
    public void onBackPressed() {

    }


}
