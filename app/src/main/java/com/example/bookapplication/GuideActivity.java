package com.example.bookapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
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
        Typeface typeface = ResourcesCompat.getFont(getApplicationContext(), R.font.fira_sans_medium);

        linlayout = (LinearLayout) findViewById(R.id.activity_guide);
        MTMathView mathview = (MTMathView)findViewById(R.id.mathview);

        mathview.setLatex("F = G \\frac{mM}{r^2}");
        mathview.setFontSize(60);

        ArrayList<String> def = getDefenition();

        //def - массив строк с определениями
        // в getParams() получаем параметры layout
        // def_text - textiew, тут разные настройки ставить

        for (String d: def) {

            TextView def_text = new TextView(getApplicationContext());
            def_text.setText(d);
            def_text.setLayoutParams(getParams());
            def_text.setTextColor(Color.BLACK);
            def_text.setTextSize(18);
            def_text.setTypeface(typeface);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(100,20,100,50);
            def_text.setLayoutParams(params);

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