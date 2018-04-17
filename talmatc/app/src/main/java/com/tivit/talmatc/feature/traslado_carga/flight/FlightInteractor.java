package com.tivit.talmatc.feature.traslado_carga.flight;

import com.tivit.talmatc.base.ui.BaseInteractor;
import com.tivit.talmatc.data.local.constant.ParameterEnum;
import com.tivit.talmatc.data.local.model.Parameter;
import com.tivit.talmatc.data.local.model.UserParameter;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Alexzander Guillermo on 28/08/2017.
 */

public class FlightInteractor extends BaseInteractor implements FlightContract.FlightInteractor {

    public FlightInteractor() {
        super();
    }

    @Override
    public void saveTypeFlight(final FlightContract.OnFlightListener onFlightListener, String codeFlight) {

        UserParameter userParameter = getAppDataManager().getAppPreferencesData().getUserParameter();
        userParameter.setTypeFlight(codeFlight);
        getAppDataManager().getAppPreferencesData().saveUserParameter(userParameter);

        onFlightListener.openNextActivity(codeFlight);
    }

    @Override
    public void onListPointInit(final FlightContract.OnFlightListener onFlightListener) {

        compositeDisposable.add(getAppDataManager().getAppLocalData().getParameterServiceLocal().findAllPointInit(ParameterEnum.POINT_INIT.getValue())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<List<Parameter>>() {
                    @Override
                    public void onNext(@io.reactivex.annotations.NonNull List<Parameter> list) {

                        onFlightListener.onUpdateListPointInit(list);
                        //onChargeMoveListener.updateUser( getAppDataManager().getAppPreferencesData().getAuthorization() );
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }

}
