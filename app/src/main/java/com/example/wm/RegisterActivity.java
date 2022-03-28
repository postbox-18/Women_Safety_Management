package com.example.wm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class RegisterActivity extends AppCompatActivity {
private EditText name,phonenum,pin,cpin;
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



        cpin=findViewById(R.id.cpin);
        cpin_layout=findViewById(R.id.cpin_layout);
        addTextChanger(cpin,cpin_layout);
        setOnFoucsChangeLister(cpin,cpin_layout);

        register=findViewById(R.id.registerbtn);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });



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
                    // Toast.makeText(getContext(), "Got the focus", Toast.LENGTH_LONG).show();
                    textInputLayout.setBoxBackgroundColor(Color.WHITE);
                } else if(len>0) {
                    //  Toast.makeText(getContext(), "Lost the focus", Toast.LENGTH_LONG).show();
                    textInputLayout.setBoxBackgroundColor(getResources().getColor(R.color.light_red_pink));
                }
                else {
                    //  Toast.makeText(getContext(), "Lost the focus", Toast.LENGTH_LONG).show();
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
                    // Toast.makeText(getContext(), "Got the focus", Toast.LENGTH_LONG).show();
                    textInputLayout.setBoxBackgroundColor(Color.WHITE);
                } else {
                    //  Toast.makeText(getContext(), "Lost the focus", Toast.LENGTH_LONG).show();
                    textInputLayout.setBoxBackgroundColor(getResources().getColor(R.color.light_silver));
                }

            }
        });
    }
}