package com.tivit.talmatc.feature.main;

import com.tivit.talmatc.base.mvp.MvpInteractor;
import com.tivit.talmatc.base.mvp.MvpPresenter;
import com.tivit.talmatc.base.mvp.MvpView;
import com.tivit.talmatc.data.local.model.Order;

import java.util.List;

/**
 * Created by Alexzander Guillermo on 29/08/2017.
 */

public interface MainContract {

    interface MainView extends MvpView {
        void updateAppVersion();
        void closeNavigationDrawer();
        void openLoginActivity();
    }

    interface MainPresenter<V extends MainView> extends MvpPresenter<V> {
        void onNavMenuCreated();
        void callPreferencesRemoveAuth();
    }

    interface MainInteractor extends MvpInteractor {

        void callPreferenceRemoveAuth(OnMainListener onMainListener);
    }

    interface OnMainListener {
        void onListOrderSuccess(List<Order> orderList);
        void onLogoutSuccess();
    }

}
