package com.example.practice16;

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
    private TextView textResult;

    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editName = findViewById(R.id.editName);
        textResult = findViewById(R.id.textResult);

        Button buttonSaveSQLite = findViewById(R.id.buttonSaveSQLite);
        Button buttonSaveProvider = findViewById(R.id.buttonSaveProvider);
        Button buttonShowSQLite = findViewById(R.id.buttonShowSQLite);
        Button buttonShowProvider = findViewById(R.id.buttonShowProvider);
        Button buttonClear = findViewById(R.id.buttonClear);

        databaseHelper = new DatabaseHelper(this);
        database = databaseHelper.getWritableDatabase();

        buttonSaveSQLite.setOnClickListener(v -> saveDirectlyToSQLite());
        buttonSaveProvider.setOnClickListener(v -> saveThroughProvider());
        buttonShowSQLite.setOnClickListener(v -> showDirectlyFromSQLite());
        buttonShowProvider.setOnClickListener(v -> showThroughProvider());
        buttonClear.setOnClickListener(v -> clearAllData());
    }

    private void saveDirectlyToSQLite() {
        String name = editName.getText().toString().trim();

        if (name.isEmpty()) {
            textResult.setText("Введіть ім'я");
            return;
        }

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_NAME, name);
        values.put(DatabaseHelper.COLUMN_SOURCE, "SQLite напряму");

        long result = database.insert(
                DatabaseHelper.TABLE_NAME,
                null,
                values
        );

        editName.setText("");

        if (result == -1) {
            textResult.setText("Помилка збереження в SQLite");
        } else {
            textResult.setText("Дані збережено напряму в SQLite");
        }
    }

    private void saveThroughProvider() {
        String name = editName.getText().toString().trim();

        if (name.isEmpty()) {
            textResult.setText("Введіть ім'я");
            return;
        }

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_NAME, name);
        values.put(DatabaseHelper.COLUMN_SOURCE, "ContentProvider");

        getContentResolver().insert(
                StudentProvider.CONTENT_URI,
                values
        );

        editName.setText("");
        textResult.setText("Дані збережено через ContentProvider");
    }

    private void showDirectlyFromSQLite() {
        try {
            Cursor cursor = database.query(
                    DatabaseHelper.TABLE_NAME,
                    new String[]{
                            DatabaseHelper.COLUMN_ID,
                            DatabaseHelper.COLUMN_NAME,
                            DatabaseHelper.COLUMN_SOURCE
                    },
                    null,
                    null,
                    null,
                    null,
                    DatabaseHelper.COLUMN_ID + " ASC"
            );

            showCursorData(cursor, "Отримано напряму з SQLite:\n\n");

        } catch (Exception e) {
            textResult.setText("Помилка читання SQLite:\n" + e.getMessage());
        }
    }

    private void showThroughProvider() {
        try {
            Cursor cursor = getContentResolver().query(
                    StudentProvider.CONTENT_URI,
                    new String[]{
                            DatabaseHelper.COLUMN_ID,
                            DatabaseHelper.COLUMN_NAME,
                            DatabaseHelper.COLUMN_SOURCE
                    },
                    null,
                    null,
                    null
            );

            showCursorData(cursor, "Отримано через ContentResolver + ContentProvider:\n\n");

        } catch (Exception e) {
            textResult.setText("Помилка читання через Provider:\n" + e.getMessage());
        }
    }

    private void showCursorData(Cursor cursor, String title) {
        if (cursor == null) {
            textResult.setText(title + "Cursor = null");
            return;
        }

        StringBuilder builder = new StringBuilder();
        builder.append(title);

        int idIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_ID);
        int nameIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME);
        int sourceIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_SOURCE);

        if (idIndex == -1 || nameIndex == -1 || sourceIndex == -1) {
            cursor.close();
            textResult.setText("Помилка: у таблиці немає потрібних колонок.\n" +
                    "Видали додаток з емулятора або збільш DATABASE_VERSION.");
            return;
        }

        if (cursor.getCount() == 0) {
            cursor.close();
            textResult.setText(title + "Даних немає");
            return;
        }

        while (cursor.moveToNext()) {
            int id = cursor.getInt(idIndex);
            String name = cursor.getString(nameIndex);
            String source = cursor.getString(sourceIndex);

            builder.append("ID: ").append(id).append("\n");
            builder.append("Ім'я: ").append(name).append("\n");
            builder.append("Спосіб запису: ").append(source).append("\n\n");
        }

        cursor.close();
        textResult.setText(builder.toString());
    }

    private void clearAllData() {
        try {
            database.delete(
                    DatabaseHelper.TABLE_NAME,
                    null,
                    null
            );

            textResult.setText("Усі дані видалено");
        } catch (Exception e) {
            textResult.setText("Помилка очищення:\n" + e.getMessage());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (database != null && database.isOpen()) {
            database.close();
        }

        if (databaseHelper != null) {
            databaseHelper.close();
        }
    }
}