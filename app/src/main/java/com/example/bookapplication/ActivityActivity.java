package com.example.bookapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

        int animation_number = getIntent().getIntExtra("animation_number", 0);
        //String type = getIntent().getStringExtra("animation_type");

        //создаем элемент ResoursesHelper,
                                // в котором у нас будут нужный файлы ресурсов по странице
        //создаем элемент AnimationRunner,
                                //в котором есть функции определения типа анимации по номеру анимации и
                                //запуск самой анимации

        ResoursesHelper res_help = new ResoursesHelper(getApplicationContext(), 0);
        res_help.setAnimationNumber(animation_number);

        AnimationRunner anim_run = new AnimationRunner(res_help, linlayout);

        // находим анимации по номеру и запускаем нужный тип

        //anim_run.addImageView();
        ImageView imageView = anim_run.startAnimation();
        linlayout.addView(imageView);
    }

    public void goToMain(View v) {
        Intent intent = new Intent(ActivityActivity.this, GuideActivity.class);
        startActivity(intent);
    }
}