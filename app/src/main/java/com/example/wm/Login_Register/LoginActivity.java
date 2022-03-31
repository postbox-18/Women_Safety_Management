package com.example.wm.Login_Register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.alimuzaffar.lib.pin.PinEntryEditText;
import com.example.wm.MainActivity;
import com.example.wm.Class.MyLog;
import com.example.wm.R;
import com.example.wm.WebService_Class;

public class LoginActivity extends AppCompatActivity {
    private PinEntryEditText pinEntry;

    private String login_pin,pin,TAG="LoginActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        pinEntry = (PinEntryEditText) findViewById(R.id.login_pin);
        pin=new WebService_Class(LoginActivity.this).getPin();


        if (pinEntry != null) {
            pinEntry.setOnPinEnteredListener(new PinEntryEditText.OnPinEnteredListener() {
                @Override
                public void onPinEntered(CharSequence str) {

                   /* if (str.toString().equals("1234")) {
                        Toast.makeText(getActivity(), "SUCCESS", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getActivity(), "FAIL", Toast.LENGTH_SHORT).show();
                        pinEntry.setText(null);
                    }*/
                    login_pin=pinEntry.getText().toString();
                    MyLog.e(TAG,"pin>>"+login_pin+"=="+pin);
                    if(pin.equals(login_pin))
                    {
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        Toast.makeText(LoginActivity.this, "SUCCESS", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(LoginActivity.this, "FAIL", Toast.LENGTH_SHORT).show();
                        pinEntry.setText(null);

                    }
                }
            });
        }
    }
}