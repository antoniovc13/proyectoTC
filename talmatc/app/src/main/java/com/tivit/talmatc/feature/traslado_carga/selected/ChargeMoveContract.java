package com.tivit.talmatc.feature.traslado_carga.selected;

import com.tivit.talmatc.base.mvp.MvpInteractor;
import com.tivit.talmatc.base.mvp.MvpPresenter;
import com.tivit.talmatc.base.mvp.MvpView;
import com.tivit.talmatc.data.local.model.Parameter;
import com.tivit.talmatc.data.remote.model.Authorization;

import java.util.List;

/**
 * Created by Alexzander Guillermo on 29/08/2017.
 */

public interface ChargeMoveContract {

    interface ChargeMoveView extends MvpView {
        void showProgressContent();
        void showProgressError(String message);

        void openSelectFlightActivity();
        void updateTractorSpinner(List<Parameter> list);
        void updateUser(Authorization authorization);
    }

    interface ChargeMovePresenter<V extends ChargeMoveView> extends MvpPresenter<V> {
        void onViewInitialized();
        void uploadItems(String vehicleType, String vehicleValue);
        void onUpdateListTractor(List<Parameter> list);
    }

    interface ChargeMoveInteractor extends MvpInteractor {
        void callApiListFlightIni(ChargeMoveContract.OnChargeMoveListener onFlightListListener);
        void callLocalListFlight(ChargeMoveContract.OnChargeMoveListener onFlightListListener);
        void callLocalFindFlightByCode(ChargeMoveContract.OnChargeMoveListener onFlightListListener, String code);
        void onListTractor(final ChargeMoveContract.OnChargeMoveListener onChargeMoveListener);

        void saveUserParameters(String vehicleType, String vehicleValue);
    }

    interface OnChargeMoveListener {
        void showProgressContent();
        void onUpdateListTractor(List<Parameter> list);
        void updateUser(Authorization authorization);
    }
}
