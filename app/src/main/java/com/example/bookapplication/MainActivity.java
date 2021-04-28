package com.example.bookapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button go_to_page = (Button)findViewById(R.id.go_to_page);
    }

    public void goToPage(View v) {
        Intent intent = new Intent(MainActivity.this, PageActivity.class);
        intent.putExtra("page_number", 2);
        startActivity(intent);
        //intent.putExtra("back", "new_pallette");
    }

    public void goToEpubBook(View v) {
        Intent intent = new Intent(MainActivity.this, GotoEpubFile.class);
        startActivity(intent);
    }
}