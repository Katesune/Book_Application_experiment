package com.example.bookapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.agog.mathdisplay.MTMathView;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

public class GuideActivity extends AppCompatActivity {
    LinearLayout linlayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        linlayout = (LinearLayout) findViewById(R.id.activity_guide);
        MTMathView mathview = (MTMathView)findViewById(R.id.mathview);

        mathview.setLatex("x = \\frac{-b \\pm \\sqrt{b^2-4ac}}{2a}");
        mathview.setFontSize(100);

        ArrayList<String> def = getDefenition();

        //def - массив строк с определениями
        // в getParams() получаем параметры layout
        // def_text - textiew, тут разные настройки ставить
        for (String d: def) {
            TextView def_text = new TextView(getApplicationContext());
            def_text.setText(d);
            def_text.setLayoutParams(getParams());


            linlayout.addView(def_text);
        }

//        Parser parser = new Parser ("http://whatever");
//        NodeList list = parser.parse (null);
    }

    public void goToMain(View v) {
        Intent intent = new Intent(GuideActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public ConstraintLayout.LayoutParams getParams() {
        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams
                (ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.leftMargin=200;
        return layoutParams;
    }

    public ArrayList<String> getDefenition() {
        ArrayList<String> def = new ArrayList<String>();
        Parser p = new Parser(getApplicationContext());

        try {
            def = p.getDefenition();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

       return def;
    }
}