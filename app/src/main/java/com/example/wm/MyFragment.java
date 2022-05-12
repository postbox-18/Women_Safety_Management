package com.example.wm;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.wm.Fragment.EditFragment;
import com.example.wm.Fragment.HomeFragment;
import com.example.wm.Fragment.SettingsFragment;

public class MyFragment extends FragmentPagerAdapter {

    private Context myContext;
    int totalTabs;

    public MyFragment(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;
    }

    // this is for fragment tabs
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                EditFragment editFragment = new EditFragment();
                return editFragment;
            case 1:
                HomeFragment homeFragment = new HomeFragment();
                return homeFragment;
            case 2:
                SettingsFragment settingsFragment = new SettingsFragment();
                return settingsFragment;
            default:
                HomeFragment homeFragment1 = new HomeFragment();
                return homeFragment1;
        }
    }
    // this counts total number of tabs
    @Override
    public int getCount() {
        return totalTabs;
    }
}
