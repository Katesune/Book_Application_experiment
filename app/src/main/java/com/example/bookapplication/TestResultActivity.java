package com.example.bookapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class TestResultActivity extends AppCompatActivity {

    int result;
    TextView message;
    TextView scores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.avtivity_result);

        message = (TextView)findViewById(R.id.msg);
        scores = (TextView)findViewById(R.id.scores);

        result = getIntent().getIntExtra("result", 0);

        showMessage();
        scores.setText(result+"/4");
    }

    public void showMessage() {
        String message_text;
        if (result < 1) message_text = "Вам стоит попытаться еще раз, вы можете лучше!";
        if (result < 3) message_text = "Вы хорошо справились!";
        else message_text = "Отличная работа! Вы молодец!";

        message.setText(message_text);
    }

    public void goToMain(View v) {
        Intent intent = new Intent(TestResultActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void again(View v) {
        Intent intent = new Intent(TestResultActivity.this, TestActivity.class);
        intent.putExtra("q_number", 0);
        intent.putExtra("result", 0);
        intent.putExtra("step", 0);
        startActivity(intent);
    }
}