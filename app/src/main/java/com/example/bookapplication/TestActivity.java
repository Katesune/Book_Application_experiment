package com.example.bookapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class TestActivity extends AppCompatActivity {
    int q_number = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        q_number = getIntent().getIntExtra("q_number", 0);

        final TextView qu = (TextView)findViewById(R.id.textq_1);
        final Button b1 = (Button)findViewById(R.id.var1);
        final Button b2 = (Button)findViewById(R.id.var2);
        final Button b3 = (Button)findViewById(R.id.var3);
        final Button b4 = (Button)findViewById(R.id.var4);

        Button[] bs = {b1, b2, b3, b4};

        setQuestion(qu);
        setAnswers(bs);
    }

    private String getQuestionName(){
        return "q"+Integer.toString(q_number);
    }

    private String[] getAnswersName(){
        String[] ans = new String[4];

        for (int i = 0; i < 4; i++) {
            ans[i] = "q"+Integer.toString(q_number)+"a"+Integer.toString(i);
        }

        return ans;
    }

    private void setQuestion(TextView qu) {
        int num = getIdRes(getQuestionName());
        qu.setText(getResources().getString(num));
    }


    private void setAnswers(Button[] bs) {
        String[] ans = getAnswersName();

        for (int i = 0; i < bs.length; i++) {
            int num = getIdRes(ans[i]);
            bs[i].setText(getResources().getString(num));
        }
    }

    private int getIdRes(String name) {
        return getResources().getIdentifier(name, "string", getApplicationContext().getPackageName());
    }

    public void goToMain(View v) {
        Intent intent = new Intent(TestActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void goToNext(View v) {
        Intent intent = new Intent(TestActivity.this, TestActivity.class);
        intent.putExtra("q_number", q_number+1);
        startActivity(intent);
    }

    public void goToBack(View v) {
        Intent intent = new Intent(TestActivity.this, TestActivity.class);
        if (q_number>1) intent.putExtra("q_number", q_number-1); else intent.putExtra("q_number", q_number);
        startActivity(intent);
    }

}