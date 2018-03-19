package com.tivit.talmatc.base.mvp;

import android.support.annotation.StringRes;

/**
 * Created by Alexzander Guillermo on 27/08/2017.
 */

public interface MvpView {

    void showLoading(String message);

    void hideLoading();

    void showMessage(String message);

    void showMessage(@StringRes int resId);

    void onError(String message);

    void onError(@StringRes int resId);

    boolean isNetworkConnected();

    void hideKeyboard();
}
