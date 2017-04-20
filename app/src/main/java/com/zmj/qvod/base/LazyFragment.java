package com.zmj.qvod.base;


import android.support.v4.app.Fragment;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by matt on 2017/1/11.
 * Fragment懒加载
 * http://blog.csdn.net/maosidiaoxian/article/details/38300627
 */
public abstract class LazyFragment extends Fragment {

    /**
     * 标志Fragment可见
     */
    protected boolean isVisible;
    /**
     * 控制订阅的生命周期
     */
    protected CompositeSubscription mCompositeSubscription;

    /**
     * 在这里实现Fragment数据的懒加载.
     *
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    /**
     * 当Fragment被设置为可见时候
     */
    protected void onVisible() {
        lazyLoad();
    }

    /**
     * 当Fragment被设置为不可见时候
     */
    protected void onInvisible() {
    }

    /**
     * 抽象方法_留给子类加载完视图之后并可见的时候调用
     */
    protected abstract void lazyLoad();

    /**
     * 控制订阅的生命周期
     *
     * @param subscription
     */
    protected void addSubscribe(Subscription subscription) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);
    }

    protected void unSubscribe() {
        if (mCompositeSubscription != null) {
            mCompositeSubscription.unsubscribe();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unSubscribe();
    }
}