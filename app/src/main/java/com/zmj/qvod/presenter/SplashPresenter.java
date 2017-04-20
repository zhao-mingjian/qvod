package com.zmj.qvod.presenter;

import android.support.annotation.NonNull;

import com.jude.beam.bijection.Presenter;
import com.zmj.qvod.constant.ImageLoader;
import com.zmj.qvod.utils.RxUtil;
import com.zmj.qvod.utils.StringUtils;
import com.zmj.qvod.view.activity.SplashActivity;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by matt on 2017/2/20.
 */
public class SplashPresenter extends Presenter<SplashActivity> {

    private static final int COUNT_DOWN_TIME = 2000;
    //
    private Subscription rxSubscription;

    @Override
    protected void onCreateView(@NonNull SplashActivity view) {
        super.onCreateView(view);
        //图片放大
        int page = StringUtils.getRandomNumber(0, getArrayList().size() - 1);
        ImageLoader.load(getView(), (String) getArrayList().get(page), getView().getImageView());
        getView().getImageView().animate().scaleX(1.12f).scaleY(1.12f).setDuration(2000).setStartDelay(100).start();
        //
        startCountDown();
    }

    private void startCountDown() {
        rxSubscription = Observable.timer(COUNT_DOWN_TIME, TimeUnit.MILLISECONDS)
                .compose(RxUtil.<Long>rxSchedulerHelper())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        getView().startMainActivity();
                    }
                });
    }

    private ArrayList getArrayList() {
        ArrayList arrayList = new ArrayList();
        arrayList.add("file:///android_asset/a.jpg");
        arrayList.add("file:///android_asset/b.jpg");
        arrayList.add("file:///android_asset/c.jpg");
        arrayList.add("file:///android_asset/d.jpg");
        arrayList.add("file:///android_asset/e.jpg");
        arrayList.add("file:///android_asset/f.jpg");
        arrayList.add("file:///android_asset/g.jpg");
        return arrayList;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        rxSubscription.unsubscribe();
    }
}
