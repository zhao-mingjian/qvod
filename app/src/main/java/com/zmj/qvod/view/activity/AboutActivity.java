package com.zmj.qvod.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.BeamBaseActivity;
import com.zmj.qvod.R;
import com.zmj.qvod.presenter.AboutPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/4/20.
 */
@RequiresPresenter(AboutPresenter.class)
public class AboutActivity extends BeamBaseActivity<AboutPresenter> {

    @BindView(R.id.tv_version)
    public TextView tvVersion;
    @BindView(R.id.tv_author)
    public TextView tvAuthor;
    @BindView(R.id.tv_email)
    public TextView tvEmail;

    public static void startAction(Context context) {
        context.startActivity(new Intent(context, AboutActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);

    }
}
