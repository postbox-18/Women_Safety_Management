package com.example.wm.Fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.example.wm.R;
import com.example.wm.WebService_Class;
import com.google.android.material.textfield.TextInputLayout;


public class RegisterFragment extends BaseFragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private EditText name,phonenum;
    private AutoCompleteTextView pin,cpin;
    private String  s_name,s_phonenum,s_pin,s_cpin,TAG="RegisterActivity";
    private AppCompatButton register;
    private TextInputLayout name_layout,phonenum_layout,pin_layout,cpin_layout;
    private String mParam1;
    private String mParam2;


    public RegisterFragment() {
        // Required empty public constructor
    }


    public static RegisterFragment newInstance(String param1, String param2) {
        RegisterFragment fragment = new RegisterFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_register_activitys, container, false);
        name=view.findViewById(R.id.name);
        name_layout=view.findViewById(R.id.name_layout);
        addTextChanger(name,name_layout);
        setOnFoucsChangeLister(name,name_layout);



        phonenum=view.findViewById(R.id.phonenum);
        phonenum_layout=view.findViewById(R.id.phonenum_layout);
        addTextChanger(phonenum,phonenum_layout);
        setOnFoucsChangeLister(phonenum,phonenum_layout);



        pin=view.findViewById(R.id.pin);
        pin_layout=view.findViewById(R.id.pin_layout);
        addTextChanger(pin,pin_layout);
        setOnFoucsChangeLister(pin,pin_layout);
        pin.requestFocus();
        pin.setThreshold(3);



        cpin=view.findViewById(R.id.cpin);
        cpin_layout=view.findViewById(R.id.cpin_layout);
        addTextChanger(cpin,cpin_layout);
        setOnFoucsChangeLister(cpin,cpin_layout);
        pin.requestFocus();
        pin.setThreshold(3);

        register=view.findViewById(R.id.registerbtn);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Getregisterdata();
            }
        });

        return view;
    }
    private void Getregisterdata() {
        s_cpin=cpin.getText().toString();
        s_pin=pin.getText().toString();
        s_name=name.getText().toString();
        s_phonenum=phonenum.getText().toString();
        if(s_name.isEmpty())
        {
            name.setError("please enter a value",getResources().getDrawable(R.drawable.ic_warning_symbol));
        }
        else if(s_phonenum.isEmpty() )
        {
            phonenum.setError("please enter a value",getResources().getDrawable(R.drawable.ic_warning_symbol));
        }
        else if(s_pin.isEmpty() )
        {
            pin.setError("please enter a value",getResources().getDrawable(R.drawable.ic_warning_symbol));
        }
        else if(s_cpin.isEmpty() )
        {
            cpin.setError("please enter a value",getResources().getDrawable(R.drawable.ic_warning_symbol));
        }
        else if(!s_cpin.equals(s_pin))
        {
            pin.setError("both password are same",getResources().getDrawable(R.drawable.ic_x_circle));
            cpin.setError("both password are same",getResources().getDrawable(R.drawable.ic_x_circle));
        }
        else if(s_phonenum.length()!=10)
        {
            phonenum.setError("please enter a valid  phone number",getResources().getDrawable(R.drawable.ic_warning_symbol));
        }
        else {
            new WebService_Class(getContext()).setPin(s_pin);
            new WebService_Class(getContext()).setName(s_name);
            new WebService_Class(getContext()).setPhonenum(s_phonenum);
            showSucessDialog("Successfully Register !", new DoneClickListener() {
                @Override
                public void onDoneClick() {
                   /* Intent intent=new Intent(getActivity(),HomeFragment.class);
                    startActivity(intent);*/
                    startActivity(new Intent(getActivity(), HomeFragment.class));
                }
            });


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