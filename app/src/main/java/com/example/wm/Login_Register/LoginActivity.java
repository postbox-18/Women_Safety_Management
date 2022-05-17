package com.example.wm.Login_Register;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.alimuzaffar.lib.pin.PinEntryEditText;
import com.example.wm.Class.WebService_Class;
import com.example.wm.MainActivity;
import com.example.wm.Class.MyLog;
import com.example.wm.R;

import java.util.Timer;
import java.util.TimerTask;

public class LoginActivity extends AppCompatActivity {
    private PinEntryEditText pinEntry;
    private CheckBox remember_me;
    private boolean bpin=false;
    private String login_pin,pin,etrpin,TAG="LoginActivity",CheckedBox;
    private TextView heads,about;
    private ConstraintLayout top_bg;
    private Animation slide_down_anim,slide_up_anim,fade_in_anim;
    private CardView login_card,checkbox_card;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        pinEntry = (PinEntryEditText) findViewById(R.id.login_pin);
       remember_me = (CheckBox) findViewById(R.id.remember_me);
        pin=new WebService_Class(LoginActivity.this).getPin();
        etrpin=new WebService_Class(LoginActivity.this).getEtrPin();

        heads=findViewById(R.id.heads);
        about=findViewById(R.id.about);
        top_bg=findViewById(R.id.head);
        checkbox_card=findViewById(R.id.checkbox_card);
        login_card=findViewById(R.id.login_card);
        Top_Bg();
        SetTextAbout("SIGN IN...",0);
        SetTextAbout("Welcome Back! Please Login Enter your Login PIN.",1);
        MyLog.e(TAG,"logout>>etr pin>>"+etrpin);
        //////////***********/////////

        CheckedBox=new WebService_Class(LoginActivity.this).getCheckedBox();
        if(CheckedBox!=null) {
            if (CheckedBox.equals("true")) {
                MyLog.e(TAG, "logout>>Check condition>>" + CheckedBox);
                login();
            } else if (CheckedBox.equals("false")) {
                MyLog.e(TAG, "logout>>Check is condition>>" + CheckedBox);
                WebService_Class.logout_User();
            }
        }
        ///////////////************///////////
        /*if(remember_me.isChecked())
        {
            bpin=true;
            boolean status = WebService_Class.insert_UserName(pinEntry.getText().toString());
            MyLog.e(TAG,"logout>>check box checked");
            if(status) {
                MyLog.e(TAG,"logout>>status>>"+status);
                MyLog.e(TAG,"logout>>etr is >>"+etrpin);
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }
            else {
                MyLog.e(TAG,"logout>>status>>"+status);
                MyLog.e(TAG,"logout>>etr is >>"+etrpin);
                WebService_Class.logout_User();
                Toast.makeText(this, "pin is empty", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            WebService_Class.logout_User();
        }*/
        if (pinEntry != null) {
            pinEntry.setOnPinEnteredListener(new PinEntryEditText.OnPinEnteredListener() {
                @Override
                public void onPinEntered(CharSequence str) {
                    if(remember_me.isChecked())
                    {
                        MyLog.e(TAG,"logout>>Check box checked>>"+remember_me.isChecked());
                        bpin=true;

                    }
                    else
                    {
                        MyLog.e(TAG,"logout>>Check box not checked>>"+remember_me.isChecked());

                        WebService_Class.logout_User();
                    }
                    login_pin = pinEntry.getText().toString();
                    MyLog.e(TAG, "pin>>" + login_pin + "==" + pin);
                    if (pin.equals(login_pin)) {
                        login();
                    } else {
                    Toast.makeText(LoginActivity.this, "FAIL", Toast.LENGTH_SHORT).show();
                    pinEntry.setText(null);

                }
                }
            });
        }
    }
    private void Top_Bg() {
        slide_down_anim = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.slide_down);
        slide_up_anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
        fade_in_anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade);
        top_bg.startAnimation(slide_down_anim);
        login_card.startAnimation(fade_in_anim);
        checkbox_card.startAnimation(fade_in_anim);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
               /* Intent intent = new Intent(RegisterActivity.this, NextActivity.class);
                startActivity(intent);*/

            }
        }, 5000);
    }

    private void SetTextAbout(String s,int n) {
        final int[] i = new int[1];
        i[0] = 0;
        final int length = s.length();
        final Handler handler = new Handler()
        {
            @SuppressLint("HandlerLeak")
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                char c= s.charAt(i[0]);
                if(n==0) {
                    heads.append(String.valueOf(c));
                }
                else if(n==1) {
                    about.append(String.valueOf(c));
                }
                i[0]++;
            }
        };
        final Timer timer = new Timer();
        TimerTask taskEverySplitSecond = new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(0);
                if (i[0] == length - 1) {
                    timer.cancel();
                }
            }
        };
        timer.schedule(taskEverySplitSecond, 1, 250);

    }
    private void login() {

            MyLog.e(TAG,"logout>>pin check >>"+bpin);
            new WebService_Class(LoginActivity.this).setCheckedBox(String.valueOf(bpin));
            new WebService_Class(LoginActivity.this).setEtrPin(pinEntry.getText().toString());
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            Toast.makeText(LoginActivity.this, "SUCCESS", Toast.LENGTH_SHORT).show();

    }
}