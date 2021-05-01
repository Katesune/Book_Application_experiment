package com.example.bookapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class TestActivity extends AppCompatActivity {
    int q_number = 0;
    int result = 0;
    int step = 0;

    StringResourceHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        q_number = getIntent().getIntExtra("q_number", 0);
        result = getIntent().getIntExtra("result", 0);
        step = getIntent().getIntExtra("step", 0);

        helper = new StringResourceHelper(getApplicationContext());
        helper.setQuestionNumber(q_number);

        final TextView qu = (TextView)findViewById(R.id.textq_1);
        final Button b1 = (Button)findViewById(R.id.var1);
        final Button b2 = (Button)findViewById(R.id.var2);
        final Button b3 = (Button)findViewById(R.id.var3);
        final Button b4 = (Button)findViewById(R.id.var4);

        Button[] bs = {b1, b2, b3, b4};

        setQuestion(qu);
        setAnswers(bs);
        setListeners(bs);
    }

    private void setQuestion(TextView qu) {
        qu.setText(helper.getQuestion());
    }

    private void setAnswers(Button[] bs) {
        String[] ans = helper.getAnswers(bs.length);

        for (int i = 0; i < bs.length; i++) {
            bs[i].setText(ans[i]);
        }
    }

    public void goToMain(View v) {
        Intent intent = new Intent(TestActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void goToBack(View v) {
        Intent intent = new Intent(TestActivity.this, TestActivity.class);
        if (q_number>-1) intent.putExtra("q_number", q_number-1); else intent.putExtra("q_number", q_number);
        startActivity(intent);
    }


    public void setListeners(Button[] bs) {
        int right_answer = helper.getRightAnswer();

        for (int i = 0; i < bs.length; i++) {
            if (i==right_answer) {
                bs[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        result+=1;
                        v.setBackgroundColor(Color.GREEN);

                        SystemClock.sleep(1200);
                        if (step<3) {
                            Intent intent = new Intent(TestActivity.this, TestActivity.class);
                            intent.putExtra("q_number", q_number+1);
                            intent.putExtra("step", step+1);
                            intent.putExtra("result", result);
                            startActivity(intent);
                        } else {
                            Intent intent = new Intent(TestActivity.this, TestResultActivity.class);
                            intent.putExtra("result", result);
                            startActivity(intent);
                        }
                    }
                });
            } else {
                bs[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        v.setBackgroundColor(Color.RED);

                        SystemClock.sleep(1200);
                        if (step<3) {
                            Intent intent = new Intent(TestActivity.this, TestActivity.class);
                            intent.putExtra("q_number", q_number+1);
                            intent.putExtra("step", step+1);
                            intent.putExtra("result", result);
                            startActivity(intent);
                        } else {
                            Intent intent = new Intent(TestActivity.this, TestResultActivity.class);
                            intent.putExtra("result", result);
                            startActivity(intent);
                        }
                    }
                });
            }
        }
    }

}