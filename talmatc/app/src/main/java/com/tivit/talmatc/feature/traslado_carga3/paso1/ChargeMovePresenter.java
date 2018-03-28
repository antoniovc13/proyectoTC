package com.tivit.talmatc.feature.traslado_carga3.paso1;

import com.tivit.talmatc.base.ui.BasePresenter;
import com.tivit.talmatc.data.local.model.Parameter;
import com.tivit.talmatc.data.remote.model.Authorization;

import java.util.List;

/**
 * Created by Alexzander Guillermo on 27/08/2017.
 */

public class ChargeMovePresenter<V extends ChargeMoveContract.ChargeMoveView> extends BasePresenter<V> implements ChargeMoveContract.ChargeMovePresenter<V>, ChargeMoveContract.OnChargeMoveListener {

    private ChargeMoveContract.ChargeMoveInteractor mInteractor;

    public ChargeMovePresenter(V mView) {
        super(mView);
        mInteractor = new ChargeMoveInteractor();
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
