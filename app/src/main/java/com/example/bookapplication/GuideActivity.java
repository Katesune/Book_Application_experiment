package com.example.bookapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.agog.mathdisplay.MTMathView;
import com.agog.mathdisplay.MTMathView.MTMathViewMode;

import org.w3c.dom.NodeList;

public class GuideActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        MTMathView mathview = (MTMathView)findViewById(R.id.mathview);
        mathview.setLatex("x = \\frac{-b \\pm \\sqrt{b^2-4ac}}{2a}");
        mathview.setFontSize(100);

//        Parser parser = new Parser ("http://whatever");
//        NodeList list = parser.parse (null);
    }

    public void goToMain(View v) {
        Intent intent = new Intent(GuideActivity.this, MainActivity.class);
        startActivity(intent);
    }
}