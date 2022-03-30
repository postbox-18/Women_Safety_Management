package com.example.wm.Fragment;

import static android.content.Context.MODE_PRIVATE;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.example.wm.R;
import com.example.wm.Class.WebService_Class;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends BaseFragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private LottieAnimationView press_send_button,send_email;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
  //  private ImageView id_tick;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        View view=   inflater.inflate(R.layout.fragment_home, container, false);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("Web_Config", MODE_PRIVATE);
        String temp_URL = sharedPreferences.getString("Pin", null);
        String pin=new WebService_Class(getActivity()).getPin();

       /* id_tick = view.findViewById(R.id.id_tick);*/
        send_email = view.findViewById(R.id.send_email);
        press_send_button = view.findViewById(R.id.press_send_button);
        press_send_button
                .addAnimatorUpdateListener(
                        (animation) -> {

                            // Do something.
                        });
        press_send_button
                .playAnimation();

        if (press_send_button.isAnimating()) {

            // Do something.
        }

        press_send_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                press_send_button.setVisibility(View.GONE);
                send_email.setVisibility(View.VISIBLE);
                Toast.makeText(getActivity(), "SENDING MESSAGE", Toast.LENGTH_SHORT).show();
                send_email
                        .addAnimatorUpdateListener(
                                (animation) -> {

                                    // Do something.
                                });
                send_email
                        .playAnimation();

                if (send_email.isAnimating()) {

                    // Do something.
                }
            }
        });

        send_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                send_email.setVisibility(View.GONE);
                press_send_button.setVisibility(View.VISIBLE);
                Toast.makeText(getActivity(), "STOPPED", Toast.LENGTH_SHORT).show();

            }
        });

        return view;

    }
}