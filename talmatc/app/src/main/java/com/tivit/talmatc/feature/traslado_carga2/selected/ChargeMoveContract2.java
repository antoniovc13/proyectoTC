package com.tivit.talmatc.feature.traslado_carga2.selected;

import com.tivit.talmatc.base.mvp.MvpInteractor;
import com.tivit.talmatc.base.mvp.MvpPresenter;
import com.tivit.talmatc.base.mvp.MvpView;
import com.tivit.talmatc.data.local.model.Parameter;
import com.tivit.talmatc.data.remote.model.Authorization;

import java.util.List;

/**
 * Created by Alexzander Guillermo on 29/08/2017.
 */

public interface ChargeMoveContract2 {

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
        void callApiListFlightIni(ChargeMoveContract2.OnChargeMoveListener onFlightListListener);
        void callLocalListFlight(ChargeMoveContract2.OnChargeMoveListener onFlightListListener);
        void callLocalFindFlightByCode(ChargeMoveContract2.OnChargeMoveListener onFlightListListener, String code);
        void onListTractor(final ChargeMoveContract2.OnChargeMoveListener onChargeMoveListener);

        void saveUserParameters(String vehicleType, String vehicleValue);
    }

    interface OnChargeMoveListener {
        void showProgressContent();
        void onUpdateListTractor(List<Parameter> list);
        void updateUser(Authorization authorization);
    }
}
