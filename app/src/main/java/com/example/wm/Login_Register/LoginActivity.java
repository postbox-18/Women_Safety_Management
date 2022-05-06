package com.example.wm.Login_Register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.Toast;

import com.alimuzaffar.lib.pin.PinEntryEditText;
import com.example.wm.MainActivity;
import com.example.wm.Class.MyLog;
import com.example.wm.R;
import com.example.wm.WebService_Class;

public class LoginActivity extends AppCompatActivity {
    private PinEntryEditText pinEntry;
    private CheckBox remember_me;
    private boolean bpin=false;
    private String login_pin,pin,etrpin,TAG="LoginActivity",CheckedBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        pinEntry = (PinEntryEditText) findViewById(R.id.login_pin);
       remember_me = (CheckBox) findViewById(R.id.remember_me);
        pin=new WebService_Class(LoginActivity.this).getPin();
        etrpin=new WebService_Class(LoginActivity.this).getEtrPin();
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

    private void login() {

            MyLog.e(TAG,"logout>>pin check >>"+bpin);
            new WebService_Class(LoginActivity.this).setCheckedBox(String.valueOf(bpin));
            new WebService_Class(LoginActivity.this).setEtrPin(pinEntry.getText().toString());
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            Toast.makeText(LoginActivity.this, "SUCCESS", Toast.LENGTH_SHORT).show();

    }
}