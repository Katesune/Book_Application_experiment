package com.example.bookapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
        setContentView(R.layout.main_activity_guide);

        linlayout = (LinearLayout) findViewById(R.id.activity_guide);
        MTMathView mathview = (MTMathView)findViewById(R.id.mathview);

        ArrayList<TextView> texts = new ArrayList<TextView>();

        ArrayList<String> def = getDefenition();

        mathview.setLatex("F = G \\cdot \\frac{m_1 \\cdot m_2 }{r_2}");
        mathview.setFontSize(90);

        texts = generateTextView(def);

        linlayout.addView(texts.get(0));
        linlayout.addView(texts.get(1));

        setLinLayouts();

        linlayout.addView(texts.get(2));
        linlayout.addView(texts.get(3));

        //def - массив строк с определениями
        // в getParams() получаем параметры layout
        // def_text - textiew, тут разные настройки ставить

//        Parser parser = new Parser ("http://whatever");
//        NodeList list = parser.parse (null);
    }

    public void setResult(int[] values, TextView text){
        if (values[3]==0) text.setText("wrong values"); else {
            int res = values[1] * values[2] / values[3];
            res *= values[0];
            text.setText(Integer.toString(res));
        }

    }

    public TextView getResultField(){
        LinearLayout new_linlayout = new LinearLayout(getApplicationContext());
        MTMathView mark = new MTMathView(getApplicationContext());
        TextView text = new TextView(getApplicationContext());

        mark.setLatex("F = ");
        mark.setLayoutParams(getParams());
        mark.setFontSize(60);

        new_linlayout.addView(mark);
        new_linlayout.addView(text);
        linlayout.addView(new_linlayout);

        return text;
    }

    private int[] getIntValues(EditText[] edit_fields){
        int[] values = new int[4];
        for (int i =0 ; i<4; i++) {
            values[i] = Integer.parseInt(String.valueOf(edit_fields[i].getText()));
        }
        return values;
    }


    public void setLinLayouts(){
        EditText[] edit_fields = generateEditText();
        MTMathView[] marks = generateMarks();

        setTextInMarks(marks);

        ArrayList<LinearLayout> linlayouts = new ArrayList<LinearLayout>();

        for (int i =0 ; i<4; i++) {
            LinearLayout new_linlayout = new LinearLayout(getApplicationContext());
            new_linlayout.addView(marks[i]);
            new_linlayout.addView(edit_fields[i]);
            linlayout.addView(new_linlayout);
        }

        TextView text = getResultField();

        for (int i =0 ; i<4; i++) {
            edit_fields[i].setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus) {
                        setResult(getIntValues(edit_fields), text);
                    }
                }
            });
        }

    }

    private void setTextInMarks(MTMathView[] marks){
        marks[0].setLatex("G");
        marks[1].setLatex("m_1");
        marks[2].setLatex("m_2");
        marks[3].setLatex("r_2");
    }

    public MTMathView[] generateMarks(){

        MTMathView[] marks = new MTMathView[4];
        for (int i =0 ; i<4; i++) {
            MTMathView mark = new MTMathView(getApplicationContext());
            mark.setLayoutParams(getParams());
            mark.setFontSize(60);
            marks[i] = mark;
        }

        return marks;
    }

    public EditText[] generateEditText(){

        EditText[] edit_fileds = new EditText[4];
        for (int i =0 ; i<4; i++) {
            EditText ed_text = new EditText(getApplicationContext());
            ed_text.setText("0");
            ed_text.setInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_FLAG_SIGNED|InputType.TYPE_NUMBER_FLAG_DECIMAL);
            ed_text.setLayoutParams(getParams());
            edit_fileds[i] = ed_text;
        }
        edit_fileds[3].setText("1");

        return edit_fileds;
    }

    public ArrayList<TextView> generateTextView(ArrayList<String> def) {
        ArrayList<TextView> texts = new ArrayList<TextView>();
        Typeface typeface = ResourcesCompat.getFont(getApplicationContext(), R.font.fira_sans_medium);

        for (String d : def) {
            TextView def_text = new TextView(getApplicationContext());
            def_text.setText(d);
            def_text.setTextColor(Color.BLACK);
            def_text.setTextSize(18);
            def_text.setTypeface(typeface);
            def_text.setLayoutParams(getTextParams());
            //def_text.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
            texts.add(def_text);
        }

        return texts;
    }

    public void goToMain(View v) {
        Intent intent = new Intent(GuideActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public LinearLayout.LayoutParams getTextParams() {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams
                (LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.leftMargin=200;
        layoutParams.setMargins(100,20,100,50);
        return layoutParams;
    }

    public LinearLayout.LayoutParams getParams() {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams
                (LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
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