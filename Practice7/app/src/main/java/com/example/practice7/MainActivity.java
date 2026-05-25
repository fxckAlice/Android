package com.example.practice7;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private RadioGroup themeGroup;
    private RadioButton lightTheme;
    private RadioButton darkTheme;
    private Button applyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        themeGroup = findViewById(R.id.themeGroup);
        lightTheme = findViewById(R.id.lightTheme);
        darkTheme = findViewById(R.id.darkTheme);
        applyButton = findViewById(R.id.applyButton);

        applyButton.setOnClickListener(v -> {
            int selectedId = themeGroup.getCheckedRadioButtonId();

            if (selectedId == R.id.lightTheme) {
                AppCompatDelegate.setDefaultNightMode(
                        AppCompatDelegate.MODE_NIGHT_NO
                );

                Toast.makeText(
                        MainActivity.this,
                        "Світлу тему застосовано",
                        Toast.LENGTH_SHORT
                ).show();
            }

            else if (selectedId == R.id.darkTheme) {
                AppCompatDelegate.setDefaultNightMode(
                        AppCompatDelegate.MODE_NIGHT_YES
                );

                Toast.makeText(
                        MainActivity.this,
                        "Темну тему застосовано",
                        Toast.LENGTH_SHORT
                ).show();
            }

            else {
                Toast.makeText(
                        MainActivity.this,
                        "Оберіть тему",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
    }
}