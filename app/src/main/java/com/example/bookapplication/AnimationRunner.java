package com.example.bookapplication;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;

//добавить onClickListener для картинки вместо кнопки + автоматическое добавление ImageView

public class AnimationRunner {
    Context c;
    LinearLayout linlayout;
    ResoursesHelper res_help;
    private Drawable animation;
    ImageView imageView;

    int animation_number = 0;
    int length;

    AnimationRunner(){}

    AnimationRunner(ResoursesHelper r, LinearLayout l){
        res_help = r;
        linlayout = l;

        c = r.c;
        animation_number = r.animation_number;
        animation = r.setDrawableResource();
        imageView = new ImageView(c);
    }

    // если понадобиться вызвать анимацию в отрыве от страницы,
    // нужно заполнить файлы ресурсов отдельно и вызвать checkAnimationType(<"Тип анимации">)
    // на возврат получим готовый imageview
    public void setAnimationResource(Drawable a){
        animation = a;
    }
    
    //добавление mini animation
    //добляем мини-анимацию на активити, с слушателем для перехода на страницу анимации
    public ImageView setImageViewDetails(){

        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams
                (1396, 770);

        imageView.setLayoutParams(layoutParams);

        imageView.setClickable(true);

        return imageView;
    }


    //сверяем тип анимации и запукаем нужный
    public ImageView startAnimation(){

        String type = res_help.getAnimationType();

        switch (type) {
            case "cadr":
                imageView = setImageViewDetails();
                cadrAnimation(imageView);
                break;
            case "draw":
                oneElementAnimation(imageView);
                break;
        }
        return imageView;
    }

    //покадровая анимация
    public void cadrAnimation(ImageView im) {
        im.setBackground(animation);
                                // animations.get(0) - ресурс, содержащий пути к кадрам, которые будут сменяться

        AnimationDrawable PageAnimationDrawable = (AnimationDrawable) im.getBackground();
        PageAnimationDrawable.start();
    }

    public void setLength(int l){
        length = l;
    }
    
    //анимация преобразований, будет удобно использовать для одного элемента
    //включает в себя вращение, изменение размера, транспортировку по осям
    public void oneElementAnimation(ImageView im) {
        im.setImageDrawable(animation);
                                    // animations.get(0) - первый ресурс drawable, содержит описание объекта

        AnimatorSet set = new AnimatorSet();

        //Animation drawAnimation = AnimationUtils.loadAnimation(c, R.anim.anim_mars1);
                                                                //anim_mars1 - 2 ресурс xml,
                                                                //содержит описание преобразований


//        set.addAnimation(drawAnimation);
//        set.addAnimation(drawAnimation1);
//        //set.addAnimation(drawAnimation2);
//
//        set.
//
//        imageView.startAnimation(set);
       // imageView.startAnimation(drawAnimation1);
        //imageView.startAnimation(drawAnimation2);

        ArrayList<ObjectAnimator> an = res_help.getDrawAnimationFiles();

        for (ObjectAnimator a: an) {
            a.setTarget(im);
            a.setStartDelay(0);
        }

        set.playSequentially(
                an.get(0), an.get(1), an.get(2),
                an.get(3), an.get(4), an.get(5),
                an.get(6), an.get(7));


        set.start();

    }

}
