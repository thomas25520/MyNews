package com.mynews.controller.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import com.mynews.R;

public class WebViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        WebView webView = findViewById(R.id.activity_web_view);
        webView.getSettings().setJavaScriptEnabled(true); // Add javaScript compatibility
        webView.loadUrl(getIntent().getExtras().getString("intent")); // Load URL on WebView
    }
}
