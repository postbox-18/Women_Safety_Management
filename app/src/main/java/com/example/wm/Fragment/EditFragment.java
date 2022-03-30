package com.example.wm.Fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.example.wm.Adapter.AddAdapter;
import com.example.wm.Class.AddPhonenum;
import com.example.wm.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditFragment extends Fragment {

    private EditText name;
    private AutoCompleteTextView phonenum;
    private String  s_name,s_phonenum,TAG="EditFragment";
    private AppCompatButton add;
    private TextInputLayout name_layout,phonenum_layout;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private AddAdapter addAdapter;
    private ArrayList<AddPhonenum> addPhonenumArrayList;
    private RecyclerView recyclerView;


    private String mParam1;
    private String mParam2;

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
        name_layout=view.findViewById(R.id.name_layout);
        addTextChanger(name,name_layout);
        setOnFoucsChangeLister(name,name_layout);



        phonenum=view.findViewById(R.id.num);
        phonenum_layout=view.findViewById(R.id.phonenum_layout);
        addTextChanger(phonenum,phonenum_layout);
        setOnFoucsChangeLister(phonenum,phonenum_layout);

        addPhonenumArrayList =new ArrayList<>();
        recyclerView = view.findViewById(R.id.recyclerview_add_num);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setNestedScrollingEnabled(false);
        addAdapter = new AddAdapter(getActivity(), addPhonenumArrayList);
        recyclerView.setAdapter(addAdapter);

        add=view.findViewById(R.id.id_add);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Getadddata();
            }
        });



        return view;
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
            Date currentTime = Calendar.getInstance().getTime();
            AddPhonenum addPhonenum=new AddPhonenum(
                    s_name,
                    s_phonenum

            );
            addPhonenumArrayList.add(addPhonenum);
            for(int i=0;i>addPhonenumArrayList.size();i++)
            {

            }
            if(addPhonenumArrayList.size()>0) {
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView.setNestedScrollingEnabled(false);
                addAdapter = new AddAdapter(getActivity(), addPhonenumArrayList);
                recyclerView.setAdapter(addAdapter);
            }

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