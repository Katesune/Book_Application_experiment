package com.example.bookapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class ActivityActivity extends AppCompatActivity {
    LinearLayout linlayout;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity);

        linlayout = (LinearLayout) findViewById(R.id.activity_activity);

        int page_number = getIntent().getIntExtra("page_number", 0);
        //String type = getIntent().getStringExtra("animation_type");

        //создаем элемент ResoursesHelper,
                                // в котором у нас будут нужный файлы ресурсов по странице
        //создаем элемент AnimationRunner,
                                //в котором есть функции определения типа анимации по номеру страницы и
                                //запуск самой анимации

        ResoursesHelper res_help = new ResoursesHelper(linlayout, getApplicationContext(), page_number);
        AnimationRunner anim_run = new AnimationRunner(res_help);

        // находим анимации по странице и запускаем нужный тип
        // необходимо продумать определение анимации по странице, возможно создать файл "page_number"="draw"
        res_help.CheckAnimationToPage();
        anim_run.checkAnimationType("draw");
    }

//    public String getType(int page_number){
//        String page = "page_" + Integer.toString(page_number);
//        int id = getResources().getIdentifier(page, "String","com.example.bookapplication");
//        return page;
//    }
}