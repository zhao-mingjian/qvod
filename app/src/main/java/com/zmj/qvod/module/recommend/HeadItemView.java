package com.zmj.qvod.module.recommend;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.zmj.qvod.R;

import butterknife.ButterKnife;

/**
 * Created by matt on 2017/3/15.
 */

public class HeadItemView implements RecyclerArrayAdapter.ItemView {

    private String setText;

    public HeadItemView(String setText) {
        this.setText = setText;
    }

    @Override
    public View onCreateView(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.include_textview, parent, false);
        TextView textView = ButterKnife.findById(view, R.id.tv_douban_head);
        textView.setText(setText);
        return view;
    }

    @Override
    public void onBindView(View headerView) {

    }
}
