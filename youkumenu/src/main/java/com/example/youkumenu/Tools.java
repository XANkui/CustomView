package com.example.youkumenu;

import android.animation.ObjectAnimator;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;
import android.widget.RelativeLayout;

class Tools {
    public static void hideView(ViewGroup view) {
        hideView(view, 0);
    }

    public static void showView(ViewGroup view) {

        showView(view,0);
    }

    public static void hideView(ViewGroup view, int startOffset) {
            //补间动画
//        RotateAnimation rotateAnimation = new RotateAnimation(0, 180,view.getWidth()/2,view.getHeight());
//        rotateAnimation.setDuration(500);
//        rotateAnimation.setFillAfter(true);
//        rotateAnimation.setStartOffset(startOffset);
//
//        for (int i = 0; i < view.getChildCount(); i++){
//            view.getChildAt(i).setEnabled(false);
//        }
//
//        view.startAnimation(rotateAnimation);

        //属性动画
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view,"rotation",0,180);
        objectAnimator.setDuration(500);
        objectAnimator.setStartDelay(startOffset);
        objectAnimator.start();

        view.setPivotX(view.getWidth()/2);
        view.setPivotY(view.getHeight());
    }

    public static void showView(ViewGroup view, int startOffset) {

//        RotateAnimation rotateAnimation = new RotateAnimation(180, 360,view.getWidth()/2,view.getHeight());
//        rotateAnimation.setDuration(500);
//        rotateAnimation.setFillAfter(true);
//        rotateAnimation.setStartOffset(startOffset);
//
//        for (int i = 0; i < view.getChildCount(); i++){
//            view.getChildAt(i).setEnabled(true);
//        }
//        view.startAnimation(rotateAnimation);

        //属性动画
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view,"rotation",180,360);
        objectAnimator.setDuration(500);
        objectAnimator.setStartDelay(startOffset);
        objectAnimator.start();

        view.setPivotX(view.getWidth()/2);
        view.setPivotY(view.getHeight());
    }
}
