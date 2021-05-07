package com.example.bookapplication;

import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
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


public class ResoursesHelper {
    Context c;
    Drawable animation;
    StringResourceHelper string_help;

    int animation_number = 0;

    ResoursesHelper(){}

    ResoursesHelper(Context context, int num){
        c = context;
        animation_number = num;
        string_help = new StringResourceHelper(c);
    }


    public void setAnimationNumber(int num) {
        animation_number = num;
    }

    public String getAnimationType() {
        return string_help.getAnimationtype(animation_number);
    }

    public Drawable setDrawableResource() {
        String name = string_help.getAnimationFileName(animation_number);
        int num = string_help.getIdDrawable(name);
        animation = c.getDrawable(num);
        return animation;
    }

    public ArrayList<ObjectAnimator> getDrawAnimationFiles() {
        ArrayList<ObjectAnimator> an = new ArrayList<ObjectAnimator>();

        ObjectAnimator animator = (ObjectAnimator) AnimatorInflater.loadAnimator(c,
                  R.animator.anim_mars1);
        an.add(animator);

        animator = (ObjectAnimator) AnimatorInflater.loadAnimator(c,
                R.animator.anim_mars2);
        an.add(animator);

        animator = (ObjectAnimator) AnimatorInflater.loadAnimator(c,
                R.animator.anim_mars3);
        an.add(animator);

        animator = (ObjectAnimator) AnimatorInflater.loadAnimator(c,
                R.animator.anim_mars4);
        an.add(animator);

        animator = (ObjectAnimator) AnimatorInflater.loadAnimator(c,
                R.animator.anim_mars5);
        an.add(animator);

//        animator = (ObjectAnimator) AnimatorInflater.loadAnimator(c,
//                R.animator.anim_mars6);
//        an.add(animator);

        animator = (ObjectAnimator) AnimatorInflater.loadAnimator(c,
                R.animator.anim_mars7);
        an.add(animator);

        animator = (ObjectAnimator) AnimatorInflater.loadAnimator(c,
                R.animator.anim_mars8);
        an.add(animator);

        animator = (ObjectAnimator) AnimatorInflater.loadAnimator(c,
                R.animator.anim_mars9);
        an.add(animator);

        return an;
    }
}
