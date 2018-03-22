package com.tivit.talmatc.feature.traslado_carga2.flight;

import com.tivit.talmatc.base.ui.BasePresenter;
import com.tivit.talmatc.data.local.model.Parameter;

import java.util.List;

/**
 * Created by Alexzander Guillermo on 27/08/2017.
 */

public class FlightPresenter2<V extends FlightContract2.FlightView> extends BasePresenter<V> implements FlightContract2.FlightPresenter<V>, FlightContract2.OnFlightListener {

    private FlightContract2.FlightInteractor mInteractor;

    public FlightPresenter2(V mView) {
        super(mView);
        mInteractor = new FlightInteractor2();
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
