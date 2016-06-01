package com.tencent.clean.presentation.presenters.impl;

import android.util.Log;
import android.widget.Toast;

import com.tencent.clean.data.model.SampleModel;
import com.tencent.clean.domain.usercase.SampleUserCase;
import com.tencent.clean.presentation.presenters.MainPresenter;

import java.util.List;

import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * 展现其实例
 */
public class MainPresenterImpl implements MainPresenter {

    private MainPresenter.View mView;
    private SampleUserCase     sampleUserCase;
    private Subscription       _subscription;

    public MainPresenterImpl(View mView, SampleUserCase sampleUserCase) {
        this.mView = mView;
        this.sampleUserCase = sampleUserCase;
    }

    @Override
    public void resume() {
        mView.showProgress();
        final long startTime = System.currentTimeMillis();
        _subscription = sampleUserCase.sample()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<SampleModel>>() {
            @Override
            public void onCompleted() {
                mView.hideProgress();
                long ellipseTime = System.currentTimeMillis() - startTime;
                mView.showError("执行时间: "+ellipseTime);
            }

            @Override
            public void onError(Throwable e) {
                Log.e("test",e.getMessage());
                mView.hideProgress();
            }

            @Override
            public void onNext(List<SampleModel> sampleModels) {
                mView.showSampleData(sampleModels);
            }
        });

    }

    @Override
    public void pause() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void destroy() {
        unsubscribe();
    }


    @Override
    public void unsubscribe() {
        if (_subscription != null && !_subscription.isUnsubscribed()) {
            _subscription.unsubscribe();
        }
    }
}
