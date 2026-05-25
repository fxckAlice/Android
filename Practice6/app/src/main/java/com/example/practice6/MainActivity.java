package com.example.practice6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private Button buttonPopup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        buttonPopup = findViewById(R.id.buttonPopup);

        registerForContextMenu(textView);

        buttonPopup.setOnClickListener(v -> {
            PopupMenu popupMenu = new PopupMenu(MainActivity.this, buttonPopup);

            popupMenu.getMenuInflater().inflate(
                    R.menu.main_menu,
                    popupMenu.getMenu()
            );

            popupMenu.setOnMenuItemClickListener(item -> {
                Toast.makeText(
                        MainActivity.this,
                        item.getTitle(),
                        Toast.LENGTH_SHORT
                ).show();

                return true;
            });

            popupMenu.show();
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_settings) {
            Toast.makeText(this, "Налаштування", Toast.LENGTH_SHORT).show();
            return true;
        }

        if (id == R.id.menu_help) {
            Toast.makeText(this, "Допомога", Toast.LENGTH_SHORT).show();
            return true;
        }

        if (id == R.id.menu_exit) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu,
                                    View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.context_edit) {
            Toast.makeText(this, "Редагування", Toast.LENGTH_SHORT).show();
            return true;
        }

        if (id == R.id.context_delete) {
            Toast.makeText(this, "Видалення", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onContextItemSelected(item);
    }
}
