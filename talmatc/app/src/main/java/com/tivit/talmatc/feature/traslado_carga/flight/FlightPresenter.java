package com.tivit.talmatc.feature.traslado_carga.flight;

import com.tivit.talmatc.base.ui.BasePresenter;
import com.tivit.talmatc.data.local.model.Parameter;

import java.util.List;

/**
 * Created by Alexzander Guillermo on 27/08/2017.
 */

public class FlightPresenter<V extends FlightContract.FlightView> extends BasePresenter<V> implements FlightContract.FlightPresenter<V>, FlightContract.OnFlightListener {

    private FlightContract.FlightInteractor mInteractor;

    public FlightPresenter(V mView) {
        super(mView);
        mInteractor = new FlightInteractor();
    }

    @Override
    public void onDetachView() {
        super.onDetachView();
        mInteractor.onUnsubscribe();
    }

    /* ======= START PRESENTER ======= */

    @Override
    public void onViewInitialized() {
        //mInteractor.callPreferencesCheckAuth(this);
        mInteractor.onListPointInit(this);
    }

    @Override
    public void saveTypeFlight(String codeFlight) {
        mInteractor.saveTypeFlight(this, codeFlight);
    }

    @Override
    public void onFlightSuccess() {

    }

    @Override
    public void onFlightError(String message) {

    }

    @Override
    public void openNextActivity(String codeFlight) {  mView.openNextActivity(codeFlight);     }

    @Override
    public void onUpdateListPointInit(List<Parameter> list) {
        mView.updatePointInitSpinner(list);
    }



}
