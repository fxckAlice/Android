package com.example.practice15;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText editName;
    private EditText editAge;
    private TextView textResult;

    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editName = findViewById(R.id.editName);
        editAge = findViewById(R.id.editAge);
        textResult = findViewById(R.id.textResult);

        Button buttonSave = findViewById(R.id.buttonSave);
        Button buttonShow = findViewById(R.id.buttonShow);

        databaseHelper = new DatabaseHelper(this);

        database = databaseHelper.getWritableDatabase();

        buttonSave.setOnClickListener(v -> {

            String name = editName.getText().toString();
            String age = editAge.getText().toString();

            ContentValues values = new ContentValues();

            values.put(DatabaseHelper.COLUMN_NAME, name);
            values.put(DatabaseHelper.COLUMN_AGE, age);

            database.insert(
                    DatabaseHelper.TABLE_NAME,
                    null,
                    values
            );

            textResult.setText("Дані збережено");
        });

        buttonShow.setOnClickListener(v -> {

            Cursor cursor = database.query(
                    DatabaseHelper.TABLE_NAME,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
            );

            StringBuilder builder = new StringBuilder();

            while (cursor.moveToNext()) {

                int id = cursor.getInt(
                        cursor.getColumnIndexOrThrow(
                                DatabaseHelper.COLUMN_ID
                        )
                );

                String name = cursor.getString(
                        cursor.getColumnIndexOrThrow(
                                DatabaseHelper.COLUMN_NAME
                        )
                );

                int age = cursor.getInt(
                        cursor.getColumnIndexOrThrow(
                                DatabaseHelper.COLUMN_AGE
                        )
                );

                builder.append("ID: ")
                        .append(id)
                        .append("\n");

                builder.append("Ім'я: ")
                        .append(name)
                        .append("\n");

                builder.append("Вік: ")
                        .append(age)
                        .append("\n\n");
            }

            cursor.close();

            textResult.setText(builder.toString());
        });
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();

        database.close();
        databaseHelper.close();
    }
}