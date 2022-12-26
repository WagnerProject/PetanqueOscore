package com.example.petanqueoscore;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class TableauScore extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tableau_score);
        WebView webView = (WebView) findViewById(R.id.webview);
        webView.loadUrl("https://bastienforestier.fr/paul/actions/Tableauscore.php");
    }
}