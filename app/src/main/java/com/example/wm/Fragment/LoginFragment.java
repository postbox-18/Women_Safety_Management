package com.example.wm.Fragment;

import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alimuzaffar.lib.pin.PinEntryEditText;
import com.example.wm.Class.MyLog;
import com.example.wm.R;
import com.example.wm.Class.WebService_Class;


public class LoginFragment extends BaseFragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private PinEntryEditText pinEntry;

    private String mParam1;
    private String mParam2;
    private String login_pin,pin,TAG="LoginFragment";

    public LoginFragment() {
        // Required empty public constructor
    }


    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
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
        View view= inflater.inflate(R.layout.fragment_login, container, false);
         pinEntry = (PinEntryEditText) view.findViewById(R.id.login_pin);
         pin=new WebService_Class(getContext()).getPin();


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
                    if(login_pin.equals(pin))
                    {
                        Toast.makeText(getActivity(), "SUCCESS", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getActivity(), "FAIL", Toast.LENGTH_SHORT).show();
                        pinEntry.setText(null);
                        startActivity(new Intent(getActivity(), HomeFragment.class));
                    }
                }
            });
        }
        return view;
    }
}