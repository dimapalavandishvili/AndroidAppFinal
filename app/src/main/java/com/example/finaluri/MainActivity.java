package com.example.finaluri;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {


    BottomNavigationView bottomNavigationView;

    SignInFragment signInFragment = new SignInFragment();
    CreateAccountFragment createAccountFragment = new CreateAccountFragment();
    SettingsFragment settingsFragment = new SettingsFragment();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,signInFragment).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.sign_in:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,signInFragment).commit();
                        return true;

                    case R.id.create_account:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,createAccountFragment).commit();
                        return true;

                    case R.id.settings:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,settingsFragment).commit();
                        return true;

                }

                return false;
            }
        });
    }

}