package com.example.wm.Login_Register;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wm.MainActivity;
import com.example.wm.R;
import com.example.wm.WebService_Class;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Timer;
import java.util.TimerTask;

public class RegisterActivity extends AppCompatActivity {

    private EditText name;
    private AutoCompleteTextView pin,cpin,phonenum;
    private String  s_name,s_phonenum,s_pin,s_cpin,TAG="RegisterActivity";
    private AppCompatButton register;

    private TextView heads,about;
    private ConstraintLayout top_bg;
    private Animation slide_down_anim,slide_up_anim,fade_in_anim;
    private LinearLayout linearLayout,registerbtn_layout;
    //private TextInputLayout name_layout,phonenum_layout,pin_layout,cpin_layout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name=findViewById(R.id.name);
        name.requestFocus();
        heads=findViewById(R.id.heads);
        about=findViewById(R.id.about);
        top_bg=findViewById(R.id.head);
        linearLayout=findViewById(R.id.linear);
        registerbtn_layout=findViewById(R.id.registerbtn_layout);
        //SetText(heads.getText().toString());
        Top_Bg();
        SetTextAbout("SIGN UP...",0);
        SetTextAbout("Sign up here to get the most out of device.",1);



       /* name_layout=findViewById(R.id.name_layout);
        addTextChanger(name,name_layout);
        setOnFoucsChangeLister(name,name_layout);*/



        phonenum=findViewById(R.id.phonenum);
       /* phonenum_layout=findViewById(R.id.phonenum_layout);
        addTextChanger(phonenum,phonenum_layout);
        setOnFoucsChangeLister(phonenum,phonenum_layout);*/
        phonenum.setThreshold(10);


        pin=findViewById(R.id.pin);
        /*pin_layout=findViewById(R.id.pin_layout);
        addTextChanger(pin,pin_layout);
        setOnFoucsChangeLister(pin,pin_layout);*/
        pin.setThreshold(3);



        cpin=findViewById(R.id.cpin);
       /* cpin_layout=findViewById(R.id.cpin_layout);
        addTextChanger(cpin,cpin_layout);
        setOnFoucsChangeLister(cpin,cpin_layout);*/
        pin.setThreshold(3);

        register=findViewById(R.id.registerbtn);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             //   hideKeyboard(register);
                Getregisterdata();
            }
        });
    }

    private void Top_Bg() {
        slide_down_anim = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.slide_down);
        slide_up_anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
        fade_in_anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade);
        top_bg.startAnimation(slide_down_anim);
        registerbtn_layout.startAnimation(slide_up_anim);
        linearLayout.startAnimation(fade_in_anim);
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

    /*  public void hideKeyboard(View view) {
          try {
              InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(getActivity().INPUT_METHOD_SERVICE);
              imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
          } catch(Exception ignored) {
          }
      }*/
    private void Getregisterdata() {
        s_cpin=cpin.getText().toString();
        s_pin=pin.getText().toString();
        s_name=name.getText().toString();
        s_phonenum=phonenum.getText().toString();
        if(s_name.isEmpty() || s_phonenum.isEmpty() || s_pin.isEmpty() || s_cpin.isEmpty())
        {
            name.setError("please enter a value",getResources().getDrawable(R.drawable.ic_warning_symbol));
        }
        else if(!s_cpin.equals(s_pin))
        {
            name.setError("please enter a value",getResources().getDrawable(R.drawable.ic_x_circle));
        }
        else {
            new WebService_Class(RegisterActivity.this).setPin(s_pin);
            new WebService_Class(RegisterActivity.this).setName(s_name);
            new WebService_Class(RegisterActivity.this).setPhonenum(s_phonenum);
            Toast.makeText(this, "Successfully Register", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            /*showSucessDialog("Successfully Register !", new DoneClickListener() {
                @Override
                public void onDoneClick() {
                   *//* Intent intent=new Intent(getActivity(),HomeFragment.class);
                    startActivity(intent);*//*
                    startActivity(new Intent(RegisterActivity.this, HomeFragment.class));
                }
            });*/


        }


    }



    private void addTextChanger(EditText editText,TextInputLayout textInputLayout) {
        final int[] len = new int[1];

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.e(TAG,"charSequence"+charSequence);
                len[0]=charSequence.length();
                Log.e(TAG,"Length"+ len);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    private void setOnFoucsChangeLister(EditText editext, TextInputLayout textInputLayout) {
        editext.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                int len= editext.getText().toString().length();
                if (hasFocus) {
                    // Toast.makeText(RegisterActivity.this, "Got the focus", Toast.LENGTH_LONG).show();
                    textInputLayout.setBoxBackgroundColor(Color.WHITE);
                } else if(len>0) {
                    //  Toast.makeText(RegisterActivity.this, "Lost the focus", Toast.LENGTH_LONG).show();

                    textInputLayout.setBoxBackgroundColor(getResources().getColor(R.color.light_red_pink));
                }
                else {
                    //  Toast.makeText(RegisterActivity.this, "Lost the focus", Toast.LENGTH_LONG).show();
                    textInputLayout.setBoxBackgroundColor(getResources().getColor(R.color.light_silver));
                }

            }
        });
    }
    private void FoucsChangeLister(EditText editext, TextInputLayout textInputLayout ) {
        editext.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus ) {
                    // Toast.makeText(RegisterActivity.this, "Got the focus", Toast.LENGTH_LONG).show();
                    textInputLayout.setBoxBackgroundColor(Color.WHITE);
                } else {
                    //  Toast.makeText(RegisterActivity.this, "Lost the focus", Toast.LENGTH_LONG).show();
                    textInputLayout.setBoxBackgroundColor(getResources().getColor(R.color.light_silver));
                }

            }
        });
    }


}