package com.example.wm.Fragment;

import static android.content.Context.MODE_PRIVATE;

import android.Manifest;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wm.Adapter.AddAdapter;
import com.example.wm.Class.AddPhonenum;
import com.example.wm.Class.MyLog;
import com.example.wm.R;
import com.example.wm.ViewModel.MyDataStore;
import com.example.wm.WebService_Class;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends BaseFragment {
private MyDataStore myDataStore;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    //private LottieAnimationView press_send_button,send_email;
    private AppCompatButton btn;
    private RecyclerView recyclerview_details;
    private AddAdapter addAdapter;
    private FusedLocationProviderClient mFusedLocationClient;
    private static final int MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION = 1;
    private List<AddPhonenum> addPhonenumArrayList;
    private TextView user_location;
    private AddAdapter.RemovePosition AddListener=new AddAdapter.RemovePosition() {

        @Override
        public void getPosition(int groupPositionParent) {
            addPhonenumArrayList.remove(groupPositionParent);
            addAdapter.notifyDataSetChanged();
            MyLog.e(TAG, "Recyclerview>>Edit after>>\n" + new GsonBuilder().setPrettyPrinting().create().toJson(addPhonenumArrayList));
        }
    };
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String TAG="HomeFragment";
    Bundle bundle;
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
        myDataStore=new ViewModelProvider(getActivity()).get(MyDataStore.class);
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


       /* String json=new WebService_Class(getActivity()).getArraylist();
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<AddPhonenum>>() {}.getType();*/


        //Bottom sheet
        BottomSheetDialog bottomSheet = new BottomSheetDialog(requireContext());
        View bottom_view=LayoutInflater.from(getContext()).inflate(R.layout.bottom_sheet_home,null);
        recyclerview_details = bottom_view.findViewById(R.id.recyclerview_add_num_new);

        //Post post = gson.fromJson(reader, Post.class);
        //AddPhonenum addPhonenumArray = gson.fromJson(json, AddPhonenum.class);
    // MyLog.d(TAG,"ClickeTest:updatedMedicine frag:"+new GsonBuilder().setPrettyPrinting().create().toJson(updatedMedicine));
        //MyLog.e(TAG,"list>>home>>"+addPhonenumArrayList.size()+">>"+new GsonBuilder().setPrettyPrinting().create().toJson(addPhonenumArrayList));
        /* id_tick = view.findViewById(R.id.id_tick);*/
        MyApplication.getMainThreadHandler().post(new Runnable() {
            @Override
            public void run() {

                //addPhonenumArrayList=gson.fromJson(json, type);
                myDataStore.getList_livedata().observe(getViewLifecycleOwner(), new Observer<List<AddPhonenum>>() {
                    @Override
                    public void onChanged(List<AddPhonenum> addPhonenums) {
                        addPhonenumArrayList=addPhonenums;
                        MyLog.e(TAG, "Recyclerview>>home begins>>\n" + new GsonBuilder().setPrettyPrinting().create().toJson(addPhonenumArrayList));
                        recyclerview_details.setHasFixedSize(true);
                        recyclerview_details.setLayoutManager(new LinearLayoutManager(getContext()));
                        recyclerview_details.setNestedScrollingEnabled(false);
                        addAdapter = new AddAdapter(getActivity(), addPhonenumArrayList, AddListener);
                        recyclerview_details.setAdapter(addAdapter);
                        addAdapter.notifyDataSetChanged();
                        //bottomSheet.setContentView(bottom_view);
                       // bottomSheet.show();
                    }
                });



            }
        });

        /*if(addPhonenumArrayList.get(0).getS_phonenum()==null) {

            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle("Message");
            builder.setMessage("Unable to get Details, do you want to retry?");
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //load_SearchFragment();
                    dialog.dismiss();
                }
            });


            builder.create().show();


            Toast.makeText(getContext(), "empty", Toast.LENGTH_SHORT).show();
        }
        else
        {
            recyclerview_details = view.findViewById(R.id.recyclerview_add_num);
            recyclerview_details.setHasFixedSize(true);
            recyclerview_details.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerview_details.setNestedScrollingEnabled(false);
            addAdapter = new AddAdapter(getActivity(), addPhonenumArrayList);
            recyclerview_details.setAdapter(addAdapter);

        }*/



        user_location = view.findViewById(R.id.user_location);


        /*send_email = view.findViewById(R.id.send_email);
        press_send_button = view.findViewById(R.id.press_send_button);*/

        btn = view.findViewById(R.id.btn);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());
        ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.SEND_SMS}, PackageManager.PERMISSION_GRANTED);



       /* press_send_button
                .addAnimatorUpdateListener(
                        (animation) -> {

                            // Do something.
                        });
        press_send_button
                .playAnimation();

        if (press_send_button.isAnimating()) {

            // Do something.
        }*/

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchLocation();
                //Toast.makeText(getActivity(), "SENDING MESSAGE", Toast.LENGTH_SHORT).show();

            }
        });
        /*press_send_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchLocation();
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
        });*/

/*        send_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                send_email.setVisibility(View.GONE);
                press_send_button.setVisibility(View.VISIBLE);
                Toast.makeText(getActivity(), "STOPPED", Toast.LENGTH_SHORT).show();

            }
        });*/

        return view;

    }

    private void fetchLocation() {


        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            MyLog.e(TAG,"msg>>permission is not granted");
            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.ACCESS_COARSE_LOCATION)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                MyLog.e(TAG,"msg>>please grant permission");
                new AlertDialog.Builder(getActivity())
                        .setTitle("Required Location Permission")
                        .setMessage("You have to give this permission to acess this feature")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ActivityCompat.requestPermissions(getActivity(),
                                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION);
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        })
                        .create()
                        .show();


            } else {
                // No explanation needed; request the permission
                MyLog.e(TAG,"msg>>request the permission");
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                        MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.

            }
        }else {
            // Permission has already been granted
            MyLog.e(TAG,"msg>>permission has already granted");
            mFusedLocationClient.getLastLocation()
                    .addOnSuccessListener(getActivity(), new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            // Got last known location. In some rare situations this can be null.
                            if (location != null) {
                                MyLog.e(TAG,"msg>>location");
                                // Logic to handle location object
                                Double latittude = location.getLatitude();
                                Double longitude = location.getLongitude();

                                user_location.setText("Latitude = "+latittude + "\nLongitude = " + longitude);

                                for(int i=0;i<addPhonenumArrayList.size();i++) {

                                    String phoneNumber = addPhonenumArrayList.get(i).getS_phonenum();
                                    //String message = "Latitude = " + latittude + " Longitude = " + longitude;
                                    String message = "http://maps.google.com/maps?daddr=" +latittude+ "," + longitude;
                                    MyLog.e(TAG,"msg>>"+message+"\n>>phone>>"+phoneNumber);
                                    if (message != null && phoneNumber != null) {
                                        SmsManager smsManager = SmsManager.getDefault();
                                        smsManager.sendTextMessage(phoneNumber, null, message, null, null);
                                        Toast.makeText(getActivity(), "SMS send successfully", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(getActivity(), "Enter the phone Number", Toast.LENGTH_SHORT).show();
                                    }
                                }

                            }
                            else
                            {
                                MyLog.e(TAG,"msg>>location is "+location);
                            }
                        }
                    });

        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                //abc

            }else{

            }
        }
    }
}