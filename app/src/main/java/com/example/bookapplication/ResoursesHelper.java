package com.example.bookapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.view.View.OnClickListener;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;

// поставляет нужные файлы анимаций
// реализовать:
//              - добавление нескольких imageview в activity при наличии нескольких анимаций
//              - размещение нескольких imageview в нужных местах ??


public class ResoursesHelper {
    Context cnt;
    LinearLayout linlayout;
    ArrayList<Drawable> animations = new ArrayList<Drawable>();

    int page_number = 0;

    ResoursesHelper(){}

    ResoursesHelper(LinearLayout l, Context c, int p_num){
        linlayout = l;
        cnt = l.getContext();
        page_number = p_num;
    }

    //нужен файл ресурсов, в котором мы будем смотреть, есть ли у нас какие либо анимации для поля
    //чтобы можно было посмотреть кол-во анимаций

    //смотрим, есть ли у нас анимации для этой страницы
    public ArrayList<Drawable> CheckAnimationToPage() {
        Drawable animation;

        switch(page_number) {
            case 1:
                animation = cnt.getResources().getDrawable(R.drawable.kometa);
                break;
            case 2:
                animation = cnt.getResources().getDrawable(R.drawable.one_element_test);
                break;
            default:
                animation = cnt.getResources().getDrawable(R.drawable.kometa);
                break;
        }
        Log.d("mytag", animation.toString());
        animations.add(animation);

        return animations;
    }

    //добавление mini animation
    //добавить появление мини-анимаций в pageActivity, отдалить переход на ActivityActivity
    public ImageView addImageView(){
        ImageView imageView = new ImageView(cnt);

        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams
                (animations.get(0).getMinimumWidth(), animations.get(0).getMinimumHeight());

        imageView.setLayoutParams(layoutParams);

        imageView.setClickable(true);

        imageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(cnt, ActivityActivity.class);
                intent.putExtra("page_number", page_number);
                cnt.startActivity(intent);
                //intent.putExtra("animation_type", "draw");
            }
        });

        linlayout.addView(imageView);

        return imageView;
    }
}
