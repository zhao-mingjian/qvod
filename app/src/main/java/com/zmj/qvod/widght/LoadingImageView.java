package com.zmj.qvod.widght;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import com.zmj.qvod.R;

/**
 * Created by matt on 2017/4/18.
 */

public class LoadingImageView extends AppCompatImageView {


    public LoadingImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setImageResource(R.drawable.anim_yun);
        // 加载动画
        AnimationDrawable mAnimationDrawable = (AnimationDrawable) getDrawable();
        // 默认进入页面就开启动画
        if (!mAnimationDrawable.isRunning()) {
            mAnimationDrawable.start();
        }
    }
}
