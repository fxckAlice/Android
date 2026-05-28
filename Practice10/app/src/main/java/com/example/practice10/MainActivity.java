package com.example.practice10;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    TextView textView;

    String FILE_NAME = "data.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.textView);

        Button buttonSave = findViewById(R.id.buttonSave);
        Button buttonRead = findViewById(R.id.buttonRead);

        buttonSave.setOnClickListener(v -> saveFile());

        buttonRead.setOnClickListener(v -> readFile());
    }

    private void saveFile() {

        try {

            FileOutputStream fos =
                    openFileOutput(FILE_NAME, MODE_PRIVATE);

            fos.write(editText.getText()
                    .toString()
                    .getBytes());

            fos.close();

            Toast.makeText(
                    this,
                    "Файл збережено",
                    Toast.LENGTH_SHORT
            ).show();

        } catch (Exception e) {

            Toast.makeText(
                    this,
                    "Помилка запису",
                    Toast.LENGTH_SHORT
            ).show();
        }
    }

    private void readFile() {

        try {

            FileInputStream fis =
                    openFileInput(FILE_NAME);

            InputStreamReader isr =
                    new InputStreamReader(fis);

            BufferedReader reader =
                    new BufferedReader(isr);

            StringBuilder builder =
                    new StringBuilder();

            String line;

            while ((line = reader.readLine()) != null) {

                builder.append(line).append("\n");
            }

            fis.close();

            textView.setText(builder.toString());

            Toast.makeText(
                    this,
                    "Файл прочитано",
                    Toast.LENGTH_SHORT
            ).show();

        } catch (Exception e) {

            Toast.makeText(
                    this,
                    "Помилка читання",
                    Toast.LENGTH_SHORT
            ).show();
        }
    }
}