package com.example.wm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.wm.Class.MyLog;
import com.example.wm.Fragment.EditFragment;
import com.example.wm.Fragment.HomeFragment;
import com.example.wm.Fragment.SettingsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private static BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.id_BottonNavigationView);

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
//                    fragment = getMyPatientFragment();
                        fragment = new SettingsFragment();
                        Tag="SettingsFragment";

                }
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.Fragment, fragment, Tag);
                transaction.addToBackStack(null);
                transaction.commit();

                return true;
            }
        });
    }
    public static void set_BottomViewItem(int in_SelectedItem) {
        bottomNavigationView.getMenu().getItem(in_SelectedItem).setChecked(true);
    }
}