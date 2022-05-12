package com.example.wm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.wm.Class.MyLog;
import com.example.wm.Fragment.EditFragment;
import com.example.wm.Fragment.HomeFragment;
import com.example.wm.Fragment.SettingsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    private static BottomNavigationView bottomNavigationView;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout= (TabLayout) findViewById(R.id.tabLayout);
        viewPager=(ViewPager)findViewById(R.id.view_pager);

        tabLayout.addTab(tabLayout.newTab().setText("Edit"));
        tabLayout.addTab(tabLayout.newTab().setText("Home"));
        tabLayout.addTab(tabLayout.newTab().setText("Settings"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final MyFragment adapter = new MyFragment(this,getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        /*bottomNavigationView = findViewById(R.id.id_BottonNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                String Tag="";
                int itemId = item.getItemId();
                if (itemId == R.id.Edit) {
                    fragment = new EditFragment();
                    Tag="EditFragment";
                } else if (itemId == R.id.Home) {
                    fragment = new HomeFragment();
                    Tag="HomeFragment";
                } else if (itemId == R.id.Settings) {
                        fragment = new SettingsFragment();
                        Tag="SettingsFragment";
                }
                else {
                    fragment = new HomeFragment();
                    Tag="HomeFragment";
                }
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.Fragment, fragment, Tag);
                transaction.addToBackStack(null);
                transaction.commit();

                return true;
            }
        });*/
    }
    public static void set_BottomViewItem(int in_SelectedItem) {
        bottomNavigationView.getMenu().getItem(in_SelectedItem).setChecked(true);
    }
}