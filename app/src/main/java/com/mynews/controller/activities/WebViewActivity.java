package com.mynews.controller.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import com.mynews.R;

import java.util.Objects;

public class WebViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        WebView webView = findViewById(R.id.activity_web_view);
        webView.getSettings().setJavaScriptEnabled(true); // Add javaScript compatibility (more pages will be compatible). not very secure but sufficient for this application
        webView.loadUrl(Objects.requireNonNull(getIntent().getExtras()).getString("getUrl")); // Load URL on WebView and display it
    }
}
