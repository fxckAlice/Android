package com.example.practice4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText nameInput;
    CheckBox checkAgree;
    Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        nameInput = findViewById(R.id.nameInput);
        checkAgree = findViewById(R.id.checkAgree);
        registerButton = findViewById(R.id.registerButton);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = nameInput.getText().toString();

                if(checkAgree.isChecked()) {

                    Toast.makeText(
                            MainActivity.this,
                            "Користувач " + name + " зареєстрований",
                            Toast.LENGTH_LONG
                    ).show();

                } else {

                    Toast.makeText(
                            MainActivity.this,
                            "Підтвердіть умови",
                            Toast.LENGTH_SHORT
                    ).show();
                }
            }
        });
    }
}