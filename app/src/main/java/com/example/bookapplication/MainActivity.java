package com.example.bookapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToEpubBook(View v) {
        Intent intent = new Intent(MainActivity.this, GotoEpubFile.class);
        startActivity(intent);
    }

    public void goToDirectory(View v) {
        Intent intent = new Intent(MainActivity.this, GuideActivity.class);
        startActivity(intent);
    }

    public void goToTest(View v) {
        Intent intent = new Intent(MainActivity.this, TestActivity.class);
        intent.putExtra("q_number", 0);
        startActivity(intent);
    }

}