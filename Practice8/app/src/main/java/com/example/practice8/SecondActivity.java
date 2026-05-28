package com.example.practice8;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        resultText = findViewById(R.id.resultText);

        String message = getIntent().getStringExtra("message");

        if (message != null && !message.isEmpty()) {
            resultText.setText(message);
        }
    }
}