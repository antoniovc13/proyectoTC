package com.tivit.talmatc.feature.main;

import com.tivit.talmatc.base.ui.BasePresenter;
import com.tivit.talmatc.data.local.model.Order;
import com.tivit.talmatc.data.local.model.User;

import java.util.List;

/**
 * Created by Alexzander Guillermo on 29/08/2017.
 */

public class MainPresenter<V extends MainContract.MainView> extends BasePresenter<V> implements MainContract.MainPresenter<V>, MainContract.OnMainListener {

    private MainContract.MainInteractor mInteractor;

    public MainPresenter(V mView) {
        super(mView);
        this.mInteractor = new MainInteractor();
    }

    @Override
    public void onDetachView() {
        super.onDetachView();
        mInteractor.onUnsubscribe();
    }

    /* ======= START PRESENTER ======= */

    @Override
    public void onNavMenuCreated() {
        if(!isViewAttached()){
            return;
        }
        mView.updateAppVersion();
        mInteractor.loadCredentials(this);
    }


    @Override
    public void callPreferencesRemoveAuth() {
        mInteractor.callPreferenceRemoveAuth(this);
    }

    /* ======= START LISTENER ======= */

    @Override
    public void onListOrderSuccess(List<Order> orderList) {

    }

    @Override
    public void onLogoutSuccess() {
        mView.openLoginActivity();
    }

    @Override
    public void loadMenu(User user) {
        mView.loadMenu(user);
    }

}
