package com.example.wm;

import android.content.Context;
import android.content.SharedPreferences;

public class WebService_Class {
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;
    protected final String Name = "Name";
    protected static final String Pin = "Pin";
    protected final String Phonenum = "Phonenum";
    protected final String Arraylist = "Arraylist";

    public WebService_Class(Context context) {
        sharedPreferences = context.getSharedPreferences("Web_Config", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

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

    
    
}
