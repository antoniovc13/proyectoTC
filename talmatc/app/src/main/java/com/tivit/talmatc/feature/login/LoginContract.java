package com.tivit.talmatc.feature.login;

import com.tivit.talmatc.base.mvp.MvpInteractor;
import com.tivit.talmatc.base.mvp.MvpPresenter;
import com.tivit.talmatc.base.mvp.MvpView;
import com.tivit.talmatc.data.remote.model.Login;

/**
 * Created by Alexzander Guillermo on 29/08/2017.
 */

public interface LoginContract {

    interface LoginView extends MvpView {
        void openMainActivity();
        void showLoginView();
        void showLoadingActivity(String message);
/*
        void showLoading(String message);
        void hideLoading();
        */
    }

    interface LoginPresenter<V extends LoginView> extends MvpPresenter<V> {
        void startLogin(Login login);
        void onViewInitialized();

        void showLoading(String message);
    }

    interface LoginInteractor extends MvpInteractor {
        void callApiAuthLogin(Login login, OnLoginListener loginListener);
        void callApiCatalogList(OnLoginListener onLoginListener);
        void callPreferencesCheckAuth(OnLoginListener onLoginListener);
    }

    interface OnLoginListener {
        void onLoginSuccess();
        void onLoginError(String message);
        void onCallCatalogSuccess();
        void isAuhenticatedSuccess();
        void isAuhenticatedFailure();

    }
}
