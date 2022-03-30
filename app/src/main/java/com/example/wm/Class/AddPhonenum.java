package com.example.wm.Class;

import java.util.Date;

public class AddPhonenum {
    private final String s_name, s_phonenum;
  //  private final Date currentTime;
    public AddPhonenum(String s_name, String s_phonenum) {
        this.s_name = s_name;
        this.s_phonenum = s_phonenum;
       // this.currentTime = currentTime;
    }

   /* public CharSequence getCurrentTime() {
        return (CharSequence) currentTime;
    }*/

    public String getS_name() {
        return s_name;
    }

    public String getS_phonenum() {
        return s_phonenum;
    }
}
