package com.tivit.talmatc.base.ui;

import com.tivit.talmatc.base.mvp.MvpPresenter;
import com.tivit.talmatc.base.mvp.MvpView;

/**
 * Created by Alexzander Guillermo on 25/08/2017.
 */

public class BasePresenter<V extends MvpView> implements MvpPresenter<V> {

    protected V mView;

    public BasePresenter(V view) {
        onAttachView(view);
    }

    @Override
    public void onAttachView(V view) {
        this.mView = view;
    }

    @Override
    public void onDetachView() {
        this.mView = null;
    }

    @Override
    public boolean isViewAttached() {
        return mView != null;
    }

}

