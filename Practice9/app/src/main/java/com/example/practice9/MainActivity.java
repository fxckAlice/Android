package com.example.practice9;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {

            FirstFragment fragment = new FirstFragment();

            FragmentTransaction transaction =
                    getSupportFragmentManager().beginTransaction();

            transaction.replace(
                    R.id.fragment_container,
                    fragment
            );

            transaction.commit();
        }
    }
}