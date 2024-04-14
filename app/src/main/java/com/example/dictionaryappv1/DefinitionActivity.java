package com.example.dictionaryappv1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.webkit.WebView;
import android.widget.TextView;


public class DefinitionActivity extends AppCompatActivity {

    private TextView tvWord;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_definition);

        Toolbar toolbar = findViewById(R.id.toolbar_definition);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Definition");

        WebView myWebView = (WebView) findViewById(R.id.webview);
        tvWord = findViewById(R.id.tv_word);
        Intent intent = getIntent();
        String word = intent.getStringExtra("word");
        String definition = intent.getStringExtra("definition");
        tvWord.setText(word);
        myWebView.loadDataWithBaseURL(null, definition, "text/html", "utf-8", null);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_back, menu);
        menu.findItem(R.id.app_bar_back).setOnMenuItemClickListener(item -> {
            onBackPressed();
            return true;
        });
        return true;
    }

    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu);
        inflater.inflate(R.menu.menu_back, menu);
        menu.findItem(R.id.app_bar_back).setOnMenuItemClickListener(item -> {
            onBackPressed();
            return true;
        });
    }
}