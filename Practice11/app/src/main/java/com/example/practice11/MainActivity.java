package com.example.practice11;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    WebView webView;
    EditText editUrl;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.webView);
        editUrl = findViewById(R.id.editUrl);

        Button buttonOpen =
                findViewById(R.id.buttonOpen);

        webView.setWebViewClient(new WebViewClient());

        webView.getSettings()
                .setJavaScriptEnabled(true);

        buttonOpen.setOnClickListener(v -> {

            String url =
                    editUrl.getText().toString();

            if (!url.startsWith("http")) {

                url = "https://" + url;
            }

            webView.loadUrl(url);
        });
    }
}