package com.study.android.ex23_fragment1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
        MainFragment mainFragment;
        MenuFragment menuFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainFragment = new MainFragment();
        menuFragment = new MenuFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.container,mainFragment).commit();
    }
    public void onFragmentChange(int index){
        if (index ==0){
            getSupportFragmentManager().beginTransaction().replace(R.id.container, menuFragment).commit();

        }
        else if (index == 1){
            getSupportFragmentManager().beginTransaction().replace(R.id.container, mainFragment).commit();

        }
    }
}