package com.example.wm.Login_Register;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.example.wm.BaseFragment;
import com.example.wm.HomeFragment;
import com.example.wm.MainActivity;
import com.example.wm.R;
import com.example.wm.WebService_Class;
import com.google.android.material.textfield.TextInputLayout;

public class RegisterActivity extends AppCompatActivity {

    private EditText name,phonenum;
    private AutoCompleteTextView pin,cpin;
    private String  s_name,s_phonenum,s_pin,s_cpin,TAG="RegisterActivity";
    private AppCompatButton register;
    private TextInputLayout name_layout,phonenum_layout,pin_layout,cpin_layout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name=findViewById(R.id.name);
        name_layout=findViewById(R.id.name_layout);
        addTextChanger(name,name_layout);
        setOnFoucsChangeLister(name,name_layout);



        phonenum=findViewById(R.id.phonenum);
        phonenum_layout=findViewById(R.id.phonenum_layout);
        addTextChanger(phonenum,phonenum_layout);
        setOnFoucsChangeLister(phonenum,phonenum_layout);



        pin=findViewById(R.id.pin);
        pin_layout=findViewById(R.id.pin_layout);
        addTextChanger(pin,pin_layout);
        setOnFoucsChangeLister(pin,pin_layout);
        pin.requestFocus();
        pin.setThreshold(3);



        cpin=findViewById(R.id.cpin);
        cpin_layout=findViewById(R.id.cpin_layout);
        addTextChanger(cpin,cpin_layout);
        setOnFoucsChangeLister(cpin,cpin_layout);
        pin.requestFocus();
        pin.setThreshold(3);

        register=findViewById(R.id.registerbtn);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Getregisterdata();
            }
        });
    }
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
            startActivity(new Intent(RegisterActivity.this, MainActivity.class));
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