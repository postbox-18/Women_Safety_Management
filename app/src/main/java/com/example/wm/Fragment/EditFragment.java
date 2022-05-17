package com.example.wm.Fragment;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.wm.Adapter.AddAdapter;
import com.example.wm.Class.AddPhonenum;
import com.example.wm.Class.WebService_Class;
import com.example.wm.R;
import com.example.wm.ViewModel.MyDataStore;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditFragment extends Fragment {
    private MyDataStore myDataStore;
    private BottomSheetBehavior bottomSheetBehavior;
    private EditText name;
    private AutoCompleteTextView phonenum;
    private String  s_name,s_phonenum,TAG="EditFragment";
    private AppCompatButton add;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private AddAdapter addAdapter;
    private List<AddPhonenum> addPhonenumArrayList=new ArrayList<>();
    private List<AddPhonenum> data_set=new ArrayList<>();
    //private RecyclerView recyclerView,recyclerview_add_num_new;
    private String mParam1;
    private String mParam2;

    private TextView heads,about;
    private ConstraintLayout top_bg;
    private Animation slide_down_anim,slide_up_anim,fade_in_anim;
    private LinearLayout name_ct_layout,recyclerview_layout;
    private AddAdapter.RemovePosition AddListener=new AddAdapter.RemovePosition() {

        @Override
        public void getPosition(int groupPositionParent) {
            data_set.remove(groupPositionParent);
            addAdapter.notifyDataSetChanged();
            //MyLog.e(TAG, "Recyclerview>>Edit after>>\n" + new GsonBuilder().setPrettyPrinting().create().toJson(data_set));
        }
    };


    public EditFragment() {
        // Required empty public constructor
    }


    public static EditFragment newInstance(String param1, String param2) {
        EditFragment fragment = new EditFragment();
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
        View view= inflater.inflate(R.layout.fragment_edit, container, false);
        name=view.findViewById(R.id.name);
        name.requestFocus();
        heads=view.findViewById(R.id.head);
        about=view.findViewById(R.id.about);
        top_bg=view.findViewById(R.id.head_layout);
        name_ct_layout=view.findViewById(R.id.name_ct_layout);
        recyclerview_layout=view.findViewById(R.id.recyclerview_layout);
        Top_Bg();
        SetTextAbout("CONTACTS...",0);
        SetTextAbout("Please Enter Name and Phone Number to Send My Location.",1);

        phonenum=view.findViewById(R.id.num);

        addPhonenumArrayList =new ArrayList<>();
        String json=new WebService_Class(getActivity()).getArraylist();
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<AddPhonenum>>() {}.getType();

        //Post post = gson.fromJson(reader, Post.class);
        //AddPhonenum addPhonenumArray = gson.fromJson(json, AddPhonenum.class);
        //data_set=gson.fromJson(json, type);
        //MyLog.e(TAG, "Recyclerview>>edit begin data_set>>\n" + new GsonBuilder().setPrettyPrinting().create().toJson(data_set));
        /*recyclerView=view.findViewById(R.id.recyclerview_add_num);
        recyclerview_add_num_new=view.findViewById(R.id.recyclerview_add_num_new);
        MyApplication.getMainThreadHandler().post(new Runnable() {
            @Override
            public void run() {
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView.setNestedScrollingEnabled(false);
                addAdapter = new AddAdapter(getActivity(), data_set,AddListener);
                recyclerView.setAdapter(addAdapter);
                addAdapter.notifyDataSetChanged();


            }
        });*/

        add=view.findViewById(R.id.id_add);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Getadddata();

            }
        });

        ///////////************////////////

        ///////////************////////////

        return view;
    }
    private void Top_Bg() {
        slide_down_anim = AnimationUtils.loadAnimation(getContext(),
                R.anim.slide_down);
        slide_up_anim = AnimationUtils.loadAnimation(getContext(), R.anim.slide_up);
        fade_in_anim = AnimationUtils.loadAnimation(getContext(), R.anim.fade);
        top_bg.startAnimation(slide_down_anim);
        name_ct_layout.startAnimation(fade_in_anim);
        //linearLayout.startAnimation(slide_up_anim);
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
    private void Getadddata() {

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

        else if(s_phonenum.length()!=10)
        {
            phonenum.setError("please enter a valid  phone number",getResources().getDrawable(R.drawable.ic_warning_symbol));
        }
        else {
            name.setText("");
            phonenum.setText("");
            hideKeyboard(add);
            Date currentTime = Calendar.getInstance().getTime();
            AddPhonenum addPhonenum=new AddPhonenum(
                    s_name,
                    s_phonenum

            );
            addPhonenumArrayList.add(addPhonenum);

            myDataStore.setAddPhonenumArrayList(addPhonenumArrayList);

            BottomSheetDialog bottomSheet = new BottomSheetDialog(requireContext());
            View view1=LayoutInflater.from(getContext()).inflate(R.layout.bottom_sheet,null);
            RecyclerView recyclerview_add_num_new=view1.findViewById(R.id.recyclerview_add_num_new);
            bottomSheetBehavior = bottomSheet.getBehavior();
            //bottomSheetBehavior.setBottomSheetCallback(bottomSheetCallback);
            ////////////////*******************************************
            BottomSheetBehavior.BottomSheetCallback bottomSheetCallback =
                    new BottomSheetBehavior.BottomSheetCallback(){
                        @Override
                        public void onStateChanged(@NonNull View bottomSheet, int newState) {
                            switch (newState){
                                case BottomSheetBehavior.STATE_COLLAPSED:
                                    //textPrompt.setText("COLLAPSED");
                                    break;
                                case BottomSheetBehavior.STATE_DRAGGING:
                                    //textPrompt.setText("DRAGGING");
                                    break;
                                case BottomSheetBehavior.STATE_EXPANDED:
                                    //textPrompt.setText("EXPANDED");
                                    break;
                                case BottomSheetBehavior.STATE_HIDDEN:
                                    //textPrompt.setText("HIDDEN");
                                    break;
                                case BottomSheetBehavior.STATE_SETTLING:
                                    //textPrompt.setText("SETTLING");
                                    break;
                                default:
                                    //textPrompt.setText("unknown...");
                            }
                        }

                        @Override
                        public void onSlide(@NonNull View bottomSheet, float slideOffset) {

                        }
                    };
            ////////////////*******************************************
            bottomSheetBehavior.setBottomSheetCallback(bottomSheetCallback);

            MyApplication.getMainThreadHandler().post(new Runnable() {
                @Override
                public void run() {

                    recyclerview_add_num_new.setHasFixedSize(true);
                    recyclerview_add_num_new.setLayoutManager(new LinearLayoutManager(getContext()));
                    recyclerview_add_num_new.setNestedScrollingEnabled(false);
                    addAdapter = new AddAdapter(getActivity(), addPhonenumArrayList, AddListener, myDataStore);
                    recyclerview_add_num_new.setAdapter(addAdapter);

                    data_set.addAll(addPhonenumArrayList);
                    Gson gson = new Gson();
                    String json = gson.toJson(data_set);
                    new WebService_Class(getContext()).setArraylist(json);
                    addAdapter.notifyDataSetChanged();


                }
            });
            bottomSheet.setContentView(view1);
            bottomSheet.show();
            bottomSheetBehavior.setPeekHeight(150);
            //bottomSheet.setCancelable(false);



        }


    }
      public void hideKeyboard(View view) {
           try {
               InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(getActivity().INPUT_METHOD_SERVICE);
               imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
           } catch(Exception ignored) {
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
                if (hasFocus) {
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