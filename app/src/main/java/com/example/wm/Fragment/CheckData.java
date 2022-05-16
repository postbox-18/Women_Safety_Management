package com.example.wm.Fragment;

public class CheckData {
    private final String s_name, s_phonenum;
    //  private final Date currentTime;
    public CheckData(String s_name, String s_phonenum) {
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
