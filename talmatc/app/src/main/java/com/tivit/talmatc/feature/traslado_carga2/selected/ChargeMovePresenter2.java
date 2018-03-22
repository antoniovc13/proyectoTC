package com.tivit.talmatc.feature.traslado_carga2.selected;

import com.tivit.talmatc.base.ui.BasePresenter;
import com.tivit.talmatc.data.local.model.Parameter;
import com.tivit.talmatc.data.remote.model.Authorization;

import java.util.List;

/**
 * Created by Alexzander Guillermo on 27/08/2017.
 */

public class ChargeMovePresenter2<V extends ChargeMoveContract2.ChargeMoveView> extends BasePresenter<V> implements ChargeMoveContract2.ChargeMovePresenter<V>, ChargeMoveContract2.OnChargeMoveListener {

    private ChargeMoveContract2.ChargeMoveInteractor mInteractor;

    public ChargeMovePresenter2(V mView) {
        super(mView);
        mInteractor = new ChargeMoveInteractor2();
    }

    @Override
    public void onDetachView() {
        super.onDetachView();
        mInteractor.onUnsubscribe();
    }

    /* ======= START PRESENTER ======= */

    @Override
    public void onViewInitialized() {
        //mView.showProgressLoading();
        //mInteractor.callLocalListFlight(this);
        mInteractor.callApiListFlightIni(this);
        mInteractor.onListTractor(this);
    }

    @Override
    public void uploadItems(String vehicleType, String vehicleValue) {
        mView.hideLoading();
        mInteractor.saveUserParameters(vehicleType, vehicleValue);
        mView.openSelectFlightActivity();
    }


    @Override
    public void showProgressContent() {
        mView.showProgressContent();
    }

    @Override
    public void onUpdateListTractor(List<Parameter> list) {
        mView.updateTractorSpinner(list);
    }

    @Override
    public void updateUser(Authorization authorization) {
        mView.updateUser(authorization);
    }

}
