package com.zmj.qvod.widght;

import android.content.Context;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.zmj.qvod.R;

/**
 * Created by Administrator on 2017/3/26.
 */
public class TitleLayout extends RelativeLayout {

    private Toolbar toolbar;

    public TitleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.include_title_layout, this);
        //
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        //
        ((AppCompatActivity) getContext()).setSupportActionBar(toolbar);
        ActionBar actionBar = ((AppCompatActivity) getContext()).getSupportActionBar();
        if (actionBar != null) {
            //是否显示默认Title
            actionBar.setDisplayShowTitleEnabled(true);
            //是否显示返回键
            actionBar.setDisplayHomeAsUpEnabled(true);
            //监听返回键
            toolbar.setNavigationOnClickListener(view -> ((AppCompatActivity) getContext()).onBackPressed());
        }
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

}
