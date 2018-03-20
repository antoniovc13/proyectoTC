package com.tivit.talmatc.feature.login;

import com.tivit.talmatc.base.ui.BasePresenter;
import com.tivit.talmatc.data.remote.model.Login;

/**
 * Created by Alexzander Guillermo on 27/08/2017.
 */

public class LoginPresenter<V extends LoginContract.LoginView> extends BasePresenter<V> implements LoginContract.LoginPresenter<V>, LoginContract.OnLoginListener {

    private LoginContract.LoginInteractor mInteractor;

    public LoginPresenter(V mView) {
        super(mView);
        mInteractor = new LoginInteractor();
    }

    @Override
    public void onDetachView() {
        super.onDetachView();
        mInteractor.onUnsubscribe();
    }

    /* ======= START PRESENTER ======= */

    @Override
    public void startLogin(Login login) {
        mView.showLoading("Validando Credenciales");
        mInteractor.callApiAuthLogin(login, this);
    }

    @Override
    public void onViewInitialized() {
        mInteractor.callPreferencesCheckAuth(this);
    }

    /* ======= START LISTENER ======= */

    @Override
    public void onLoginSuccess() {
        mView.hideLoading();
        mView.showLoading("Obteniendo lista catalogo");
        mInteractor.callApiCatalogList(this);
    }

    @Override
    public void onLoginError(String message) {
        mView.hideLoading();
        mView.onError(message);
    }

    @Override
    public void onCallCatalogSuccess() {
        mView.hideLoading();
        mView.openMainActivity();
    }

    @Override
    public void isAuhenticatedSuccess() {
        mView.openMainActivity();
    }

    @Override
    public void isAuhenticatedFailure() {
        mView.showLoginView();
    }


}
