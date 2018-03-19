package com.tivit.talmatc.base.mvp;

/**
 * Created by Alexzander Guillermo on 27/08/2017.
 */

public interface MvpPresenter<V extends MvpView>{

    void onAttachView(V view);

    void onDetachView();

    boolean isViewAttached();

}
