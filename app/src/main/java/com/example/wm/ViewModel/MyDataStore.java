package com.example.wm.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.wm.Class.AddPhonenum;
import com.example.wm.Class.WebService_Class;
import com.example.wm.Fragment.CheckData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MyDataStore extends AndroidViewModel {
    private List<AddPhonenum> addPhonenumArrayList=new ArrayList<>();
    private MutableLiveData<List<AddPhonenum>> list_livedata=new MutableLiveData<>();
    private List<CheckData> checkData=new ArrayList<>();
    private MutableLiveData<List<CheckData>> mutableCheckData=new MutableLiveData<>();
    private String location;
    private MutableLiveData<String> mutableLiveData=new MutableLiveData<>();
    public MyDataStore(@NonNull Application application) {
        super(application);
        //added phone-number
        String json=new WebService_Class(application).getArraylist();
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<AddPhonenum>>() {}.getType();
        if(json==null||json.isEmpty())
        {

        }
        else {
            addPhonenumArrayList=gson.fromJson(json, type);
            this.list_livedata.postValue(addPhonenumArrayList);
        } //Checked phone-number
        String arraylist=new WebService_Class(application).getCheckArrayList();
        Gson gson1 = new Gson();
        Type type1 = new TypeToken<ArrayList<AddPhonenum>>() {}.getType();
        if(arraylist==null||arraylist.isEmpty())
        {

        }
        else {
            checkData=gson1.fromJson(arraylist, type1);
            this.mutableCheckData.postValue(checkData);
        }
        //last seen location
        String get_locations=new WebService_Class(application).getLocation();
        if(get_locations==null|| json.isEmpty())
        {

        }
        else
        {
            location=get_locations;
            this.mutableLiveData.postValue(location);
        }


    }

    public List<AddPhonenum> getAddPhonenumArrayList() {
        return addPhonenumArrayList;
    }

    public void setAddPhonenumArrayList(List<AddPhonenum> addPhonenumArrayList) {
        this.addPhonenumArrayList = addPhonenumArrayList;
        this.list_livedata.postValue(addPhonenumArrayList);

    }

    public MutableLiveData<List<AddPhonenum>> getList_livedata() {
        return list_livedata;
    }



    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
        this.mutableLiveData.postValue(location);
    }
    public MutableLiveData<String> getMutableLiveData() {
        return mutableLiveData;
    }

    public List<CheckData> getCheckData() {
        return checkData;
    }

    public MutableLiveData<List<CheckData>> getMutableCheckData() {
        return mutableCheckData;
    }

    public void setCheckData(List<CheckData> checkData) {
        this.checkData = checkData;
        this.mutableCheckData.postValue(checkData);
    }
}
