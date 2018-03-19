package com.tivit.talmatc.base.ui;

import com.tivit.talmatc.TivitApplication;
import com.tivit.talmatc.base.mvp.MvpInteractor;
import com.tivit.talmatc.data.AppDataManager;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Alexzander Guillermo on 25/08/2017.
 */

public abstract class BaseInteractor implements MvpInteractor {

    protected CompositeDisposable compositeDisposable;
    private DisposableObserver subscriber;
    private final AppDataManager appDataManager;

    public BaseInteractor() {
        appDataManager = AppDataManager.getInstance(TivitApplication.getAppContext());
        compositeDisposable = new CompositeDisposable();
    }

    public AppDataManager getAppDataManager() {
        return appDataManager;
    }

    @Override
    public void onUnsubscribe() {
        if(compositeDisposable != null && compositeDisposable.isDisposed()) {
            compositeDisposable.clear();
        }
    }

    protected void addSubscribe(Observable observable, DisposableObserver subscriber) {
        this.subscriber = subscriber;
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add((Disposable) observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(subscriber));
    }

}
