package com.mvp.model.http;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/**
 * Created by Asion on 2018/1/9.
 *
 */

public abstract class BaseSubscriber<T> implements Subscriber<T> {

    @Override
    public void onSubscribe(Subscription s) {

    }

    @Override
    public void onNext(T t) {

    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onError(Throwable t) {

    }
}
