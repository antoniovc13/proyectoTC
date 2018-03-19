package com.tivit.talmatc.feature.login;

import com.google.gson.GsonBuilder;
import com.tivit.talmatc.base.ui.BaseInteractor;
import com.tivit.talmatc.data.local.constant.ParameterEnum;
import com.tivit.talmatc.data.local.model.Parameter;
import com.tivit.talmatc.data.remote.model.ApiCallback;
import com.tivit.talmatc.data.remote.model.Authorization;
import com.tivit.talmatc.data.remote.model.Login;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by Alexzander Guillermo on 28/08/2017.
 */

public class LoginInteractor extends BaseInteractor implements LoginContract.LoginInteractor {

    public LoginInteractor() {
        super();
    }

    @Override
    public void callApiAuthLogin(Login login, final LoginContract.OnLoginListener onLoginListener) {

        //addSubscribe(getAppDataManager().getAppRemoteData().getUserService().getAuthorizationLogin(login), new ApiCallback<Authorization>() {
        addSubscribe(getAppDataManager().getAppLocalData().getUserServiceLocal().getAuthorizationLogin(login), new ApiCallback<Authorization>() {
            @Override
            public void onSuccess(Authorization model) {
                Timber.d(new GsonBuilder().setPrettyPrinting().create().toJson(model));
                getAppDataManager().getAppPreferencesData().saveAuthorization(model);
            }

            @Override
            public void onFailure(List<String> message) {
                onLoginListener.onLoginError(message.get(0));
            }

            @Override
            public void onFinish() {
                onLoginListener.onLoginSuccess();
            }
        });



    }

    @Override
    public void callApiCatalogList(final LoginContract.OnLoginListener onLoginListener) {
        //String token = "Bearer " + getAppDataManager().getAppPreferencesData().getAuthorization().getAccessToken();

        onLoginListener.onCallCatalogSuccess();


/*
        Observable<Boolean> catalogSituations = getAppDataManager().getAppRemoteData().getUserService().getCatalogSituations(token)
                .flatMap(parameters -> {
                        for (Parameter param : parameters) {
                            param.setEntidad(ParameterEnum.SITUACION.getValue());
                        }
                        return getAppDataManager().getAppLocalData().getUserServiceLocal().saveCatalogParameter(parameters);
                    });

        Observable<Boolean> catalogActions = getAppDataManager().getAppRemoteData().getUserService().getCatalogActions(token)
                .flatMap(parameters -> {
                    for (Parameter param : parameters) {
                        param.setEntidad(ParameterEnum.ACCION.getValue());
                    }
                    return getAppDataManager().getAppLocalData().getUserServiceLocal().saveCatalogParameter(parameters);
                });
*/
        String token = "Bearer " + getAppDataManager().getAppPreferencesData().getAuthorization().getAccessToken();

    }

    @Override
    public void callPreferencesCheckAuth(LoginContract.OnLoginListener onLoginListener) {
        compositeDisposable.add(getAppDataManager().getAppPreferencesData().isAuthentificated()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Boolean>() {
                    @Override
                    public void onNext(@NonNull Boolean aBoolean) {
                        /*if(aBoolean){
                            onLoginListener.isAuhenticatedSuccess();
                        } else {
                            onLoginListener.isAuhenticatedFailure();
                        }*/
                        onLoginListener.isAuhenticatedFailure();
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
