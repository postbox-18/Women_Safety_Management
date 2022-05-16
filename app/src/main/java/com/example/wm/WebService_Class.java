package com.example.wm;

import android.content.Context;
import android.content.SharedPreferences;

public class WebService_Class {
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;
    protected final String Name = "Name";
    protected static final String Pin = "Pin";
    protected static final String etrPin = "etrPin";
    protected final String Phonenum = "Phonenum";
    protected final String Arraylist = "Arraylist";
    protected final String CheckedBox = "CheckedBox";
    protected final String Location = "Location";
    protected final String CheckArrayList = "CheckArrayList";

    public WebService_Class(Context context) {
        sharedPreferences = context.getSharedPreferences("Web_Config", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

    }
    public static boolean logout_User() {
        if (editor != null) {
            editor.remove("etrPin");
            editor.apply();
            return true;
        } else {
            return false;
        }
    }
    public static Boolean insert_UserName(String etrPin) {
        editor.putString("etrPin", etrPin);
        editor.apply();
        return true;
    }
    public void setName(String name){
        sharedPreferences.edit().putString(Name, name).commit();
      
    }
    public String getName()
    {

        return sharedPreferences.getString(Name, null);
    }
    
    
    public void setPin(String pin){
        sharedPreferences.edit().putString(Pin, pin).commit();
      
    }
    public static String getPin()
    {
        editor.apply();
        return sharedPreferences.getString(Pin, null);
    }
    
    
    public void setPhonenum(String phonenum){
        sharedPreferences.edit().putString(Phonenum, phonenum).commit();
      
    }
    public String getPhonenum()
    {
        return sharedPreferences.getString(Phonenum, null);
    }

    public void setArraylist(String arraylist){
        sharedPreferences.edit().putString(Arraylist, arraylist).commit();

    }
    public String getArraylist()
    {
        return sharedPreferences.getString(Arraylist, null);
    }

    public void setEtrPin(String etrpin){
        sharedPreferences.edit().putString(etrPin, etrpin).commit();

    }
    public String getEtrPin()
    {
        return sharedPreferences.getString(etrPin, null);
    }



    public void setCheckedBox(String checkedBox){
        sharedPreferences.edit().putString(CheckedBox, checkedBox).commit();

    }
    public String getCheckedBox()
    {
        return sharedPreferences.getString(CheckedBox, null);
    }


    public void setLocation(String location){
        sharedPreferences.edit().putString(Location, location).commit();

    }
    public String getLocation()
    {
        return sharedPreferences.getString(Location, null);
    }


    public void setCheckArrayList(String checkedBoxarray){
        sharedPreferences.edit().putString(CheckArrayList, checkedBoxarray).commit();

    }
    public String getCheckArrayList()
    {
        return sharedPreferences.getString(CheckArrayList, null);
    }



    
}
