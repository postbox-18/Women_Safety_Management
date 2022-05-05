package com.example.wm.Fragment;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wm.Class.MyLog;
import com.example.wm.Login_Register.LoginActivity;
import com.example.wm.R;
import com.example.wm.WebService_Class;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TextView user_name,phone_number,change_pin;
    private String tuser_name,tphone_number,tchange_pin,TAG="SettingsFragment";
    private AppCompatButton logout;
    public SettingsFragment() {
        // Required empty public constructor
    }

    public static SettingsFragment newInstance(String param1, String param2) {
        SettingsFragment fragment = new SettingsFragment();
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
        View view= inflater.inflate(R.layout.fragment_settings, container, false);
        user_name=view.findViewById(R.id.user_name);
        phone_number=view.findViewById(R.id.phone_number);
        change_pin=view.findViewById(R.id.change_pin);
        logout=view.findViewById(R.id.log_out);

        tuser_name=new WebService_Class(getContext()).getName();
        tphone_number=new WebService_Class(getContext()).getPhonenum();
        user_name.setText(tuser_name);
        phone_number.setText(tphone_number);
        //click on next page
        tchange_pin=new WebService_Class(getContext()).getPin();
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyLog.e(TAG,"logout>>btn clicked");
                WebService_Class.logout_User();
                Intent intent=new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }


}