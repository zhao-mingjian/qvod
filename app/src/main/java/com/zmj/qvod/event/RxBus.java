package com.zmj.qvod.event;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

public class RxBus {


    private static volatile RxBus defaultInstance;

    private final Subject<Object, Object> bus;

    public RxBus() {
        bus = new SerializedSubject<>(PublishSubject.create());
    }

    // singleton
    public static RxBus getDefault() {
        if (defaultInstance == null) {
            synchronized (RxBus.class) {
                if (defaultInstance == null) {
                    defaultInstance = new RxBus();
                }
            }
        }
        return defaultInstance;
    }

    // send a new event
    public void post(Object o) {
        bus.onNext(o);
    }

    // filter event type，post the specific event object to observable.
    public <T> Observable<T> toObservable(Class<T> eventType) {
        return bus.ofType(eventType);
    }


}
