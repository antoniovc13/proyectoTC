package com.tivit.talmatc.feature.traslado_carga3.paso2;

import com.tivit.talmatc.base.mvp.MvpInteractor;
import com.tivit.talmatc.base.mvp.MvpPresenter;
import com.tivit.talmatc.base.mvp.MvpView;
import com.tivit.talmatc.data.local.model.Parameter;

import java.util.List;

/**
 * Created by Alexzander Guillermo on 29/08/2017.
 */

public interface FlightContract {

    interface FlightView extends MvpView {
        void openNextActivity(String flight);

        void updatePointInitSpinner(List<Parameter> list);
    }

    interface FlightPresenter<V extends FlightView> extends MvpPresenter<V> {
        void onViewInitialized();
        void saveTypeFlight(String flight);
    }

    interface FlightInteractor extends MvpInteractor {
        void saveTypeFlight(FlightContract.OnFlightListener onFlightListener, String flight);

        void onListPointInit(final FlightContract.OnFlightListener onFlightListener);
    }

    interface OnFlightListener {
        void onFlightSuccess();
        void onFlightError(String message);
        void openNextActivity(String flight);

        void onUpdateListPointInit(List<Parameter> list);
    }
}
