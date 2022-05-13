package com.example.wm.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.wm.Class.AddPhonenum;

import java.util.ArrayList;
import java.util.List;

public class MyDataStore extends AndroidViewModel {
    private List<AddPhonenum> addPhonenumArrayList=new ArrayList<>();
    private MutableLiveData<List<AddPhonenum>> list_livedata=new MutableLiveData<>();
    public MyDataStore(@NonNull Application application) {
        super(application);
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
}
