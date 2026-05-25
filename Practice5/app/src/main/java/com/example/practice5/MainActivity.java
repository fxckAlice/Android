package com.example.practice5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    ListView listViewSubjects;
    Spinner spinnerSubjects;

    String[] subjects = {
            "Математика",
            "Програмування",
            "Фізика",
            "Бази даних",
            "Мережі"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        listViewSubjects = findViewById(R.id.listViewSubjects);
        spinnerSubjects = findViewById(R.id.spinnerSubjects);

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(
                        this,
                        R.layout.list_item,
                        R.id.itemText,
                        subjects
                );

        listViewSubjects.setAdapter(adapter);

        ArrayAdapter<String> spinnerAdapter =
                new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_spinner_item,
                        subjects
                );

        spinnerAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );

        spinnerSubjects.setAdapter(spinnerAdapter);
    }
}