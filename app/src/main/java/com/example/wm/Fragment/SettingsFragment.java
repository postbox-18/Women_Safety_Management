package com.example.wm.Fragment;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alimuzaffar.lib.pin.PinEntryEditText;
import com.example.wm.Class.MyLog;
import com.example.wm.Login_Register.LoginActivity;
import com.example.wm.R;
import com.example.wm.WebService_Class;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.Objects;

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

    private TextView user_name,phone_number,change_pin,key_maps;
    private String tuser_name,tphone_number,tchange_pin,TAG="SettingsFragment";
    private AppCompatButton logout;
    private BottomSheetDialog bottomSheetDialog;
    //bottom sheet
    private TextView head;
    private EditText text;
    private PinEntryEditText check_pin;
    private AppCompatButton update;
    private CardView check_pin_layout,first_lay;
    private int btn=-1;
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
        key_maps=view.findViewById(R.id.key_map);
        logout=view.findViewById(R.id.log_out);

        bottomSheetDialog = new BottomSheetDialog(requireContext());
        tuser_name=new WebService_Class(getContext()).getName();
        tphone_number=new WebService_Class(getContext()).getPhonenum();
        user_name.setText(tuser_name);
        phone_number.setText(tphone_number);
        //click on next page
        View view_BottomSheetDialod= inflater.inflate(R.layout.bottom_view, container, false);
        text=view_BottomSheetDialod.findViewById(R.id.text);
        head=view_BottomSheetDialod.findViewById(R.id.heading);
        update=view_BottomSheetDialod.findViewById(R.id.update);
        first_lay=view_BottomSheetDialod.findViewById(R.id.first_lay);
        check_pin=view_BottomSheetDialod.findViewById(R.id.check_pin);
        check_pin_layout=view_BottomSheetDialod.findViewById(R.id.check_pin_layout);
        tchange_pin=new WebService_Class(getContext()).getPin();
        bottomSheetDialog.setContentView(view_BottomSheetDialod);

        change_pin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.show();
                first_lay.setVisibility(View.GONE);
                check_pin_layout.setVisibility(View.VISIBLE);
                if (check_pin != null) {
                    check_pin.setOnPinEnteredListener(new PinEntryEditText.OnPinEnteredListener() {
                        @Override
                        public void onPinEntered(CharSequence str) {
                            if (tchange_pin.equals(check_pin.getText().toString())) {
                                first_lay.setVisibility(View.VISIBLE);
                                check_pin.setVisibility(View.GONE);
                                head.setText("Change Pin");
                                text.setText(tchange_pin);
                                btn = 1;
                            } else {
                                Toast.makeText(getContext(), "Error pin is incorrect", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
        key_maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.show();
                head.setText("Change Key Map");
                text.setText(getContext().getResources().getString(R.string.key_map));
                btn=2;
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //check if enter pin an local pin is crt

                if(btn==1)
                {
                    new WebService_Class(getContext()).setPin(text.getText().toString());
                    bottomSheetDialog.dismiss();
                }
                else if(btn==2)
                {
                    bottomSheetDialog.dismiss();
                    //update key map in @string
                }
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyLog.e(TAG,"logout>>btn clicked");
                WebService_Class.logout_User();
                Intent intent=new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
        bottomSheetDialog.setContentView(view_BottomSheetDialod);
        return view;
    }


}