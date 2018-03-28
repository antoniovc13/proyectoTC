package com.tivit.talmatc.feature.traslado_carga3;

import com.tivit.talmatc.base.mvp.MvpView;
import com.tivit.talmatc.base.ui.BasePresenter;
import com.tivit.talmatc.feature.traslado_carga3.paso1.ChargeMoveInteractor;

/**
 * Created by Antonio.Valdivieso on 27/03/2018.
 */

public class OrderPresenter<V extends OrderContract.OrderView> extends BasePresenter<V> implements OrderContract.OrderPresenter<V>/*, OrderContract.OnChangeTab */{

    public OrderPresenter(V mView) {
        super(mView);
        //mInteractor = new ChargeMoveInteractor();
    }

    @Override
    public void onDetachView() {
        super.onDetachView();
        //mInteractor.onUnsubscribe();
    }

    @Override
    public void goToFlight() {
        mView.goToFlight();
    }

    @Override
    public void backFlight() {

    }

    @Override
    public void goToFlightList() {

    }
}
