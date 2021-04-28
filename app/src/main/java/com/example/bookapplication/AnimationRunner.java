package com.example.bookapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;

//добавить onClickListener для картинки вместо кнопки + автоматическое добавление ImageView

public class AnimationRunner {
    Context cnt;
    ResoursesHelper res_help;
    private ArrayList<Drawable> animations;

    int page_number = 0;

    AnimationRunner(){}

    AnimationRunner(ResoursesHelper r){
        res_help = r;

        cnt = r.cnt;
        page_number = r.page_number;
        animations = r.animations;
    }

    // если понадобиться вызвать анимацию в отрыве от страницы,
    // нужно заполнить файлы ресурсов отдельно и вызвать checkAnimationType(<"Тип анимации">)
    // на возврат получим готовый imageview
    public void setAnimationResources(ArrayList<Drawable> a){
        animations = a;
    }

    //Добавление анимации на linearlayot
    public ImageView addImageView(){
        ImageView imageView = new ImageView(cnt);

        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams
                (ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);

        imageView.setLayoutParams(layoutParams);
        res_help.linlayout.addView(imageView);

        return imageView;
    }

    //сверяем тип анимации и запукаем нужный
    public ImageView checkAnimationType(String type){

        ImageView imageView = addImageView();

        switch (type) {
            case "cadr":
                cadrAnimation(imageView);
            case "draw":
                oneElementAnimation(imageView);
        }
        return imageView;
    }

    //покадровая анимация
    public void cadrAnimation(ImageView imageView) {
        imageView.setBackground(animations.get(0));
                                // animations.get(0) - ресурс, содержащий пути к кадрам, которые будут сменяться

        AnimationDrawable PageAnimationDrawable = (AnimationDrawable) imageView.getBackground();
        PageAnimationDrawable.start();
    }

    //анимация преобразований, будет удобно использовать для одного элемента
    //включает в себя вращение, изменение размера, транспортировку по осям
    public void oneElementAnimation(ImageView imageView) {
        imageView.setImageDrawable(animations.get(0));
                                    // animations.get(0) - первый ресурс drawable, содержит описание объекта
        Animation sunRiseAnimation = AnimationUtils.loadAnimation(cnt, R.anim.anim_one_element_test);
                                                                        //anim_one_element_test - 2 ресурс xml,
                                                                        //содержит описание преобразований
        imageView.startAnimation(sunRiseAnimation);
    }

}
