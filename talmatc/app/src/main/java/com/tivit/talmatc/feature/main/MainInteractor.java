package com.tivit.talmatc.feature.main;

import com.tivit.talmatc.base.ui.BaseInteractor;
import com.tivit.talmatc.data.local.constant.OrderEnum;
import com.tivit.talmatc.data.local.model.Order;
import com.tivit.talmatc.data.local.model.UserParameter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by Alexzander Guillermo on 29/08/2017.
 */

public class MainInteractor extends BaseInteractor implements MainContract.MainInteractor {

    public MainInteractor() {
        super();
    }


    @Override
    public void loadCredentials(MainContract.OnMainListener onMainListener) {

        onMainListener.loadMenu(getAppDataManager().getAppPreferencesData().getAuthorization().getUser());
    }

    @Override
    public void callPreferenceRemoveAuth(MainContract.OnMainListener onMainListener) {
        compositeDisposable.add(getAppDataManager().getAppPreferencesData().removeAuthorization()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(new DisposableObserver<Boolean>() {
                @Override
                public void onNext(@NonNull Boolean aBoolean) {
                    if(aBoolean) {
                        onMainListener.onLogoutSuccess();
                    }
                }

                @Override
                public void onError(@NonNull Throwable e) {

                }

                @Override
                public void onComplete() {

                }
            }));
    }
}
