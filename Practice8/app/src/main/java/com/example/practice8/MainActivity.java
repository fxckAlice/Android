package com.example.practice8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText inputText;
    private Button openButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputText = findViewById(R.id.inputText);
        openButton = findViewById(R.id.openButton);

        openButton.setOnClickListener(v -> {

            String text = inputText.getText().toString();

            Intent intent = new Intent(
                    MainActivity.this,
                    SecondActivity.class
            );

            intent.putExtra("message", text);

            startActivity(intent);
        });
    }
}