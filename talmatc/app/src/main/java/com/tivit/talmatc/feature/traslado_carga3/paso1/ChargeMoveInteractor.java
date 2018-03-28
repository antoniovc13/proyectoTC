package com.tivit.talmatc.feature.traslado_carga3.paso1;

import android.support.annotation.NonNull;

import com.tivit.talmatc.base.ui.BaseInteractor;
import com.tivit.talmatc.data.local.constant.ParameterEnum;
import com.tivit.talmatc.data.local.model.Flight;
import com.tivit.talmatc.data.local.model.Parameter;
import com.tivit.talmatc.data.local.model.UserParameter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by Alexzander Guillermo on 28/08/2017.
 */

public class ChargeMoveInteractor extends BaseInteractor implements ChargeMoveContract.ChargeMoveInteractor {

    public ChargeMoveInteractor() {
        super();
    }

/*
    @Override
    public Flight findFlight(String flight) {
        //getAppDataManager().getAppPreferencesData().saveTypeFlight(flight);

        Flight f = new Flight();
        f.setCode(flight);
        f.setDescripcion(flight);
        f.setPea("8");
        f.setEta("08:00");
        f.setEtd("08:45");
        f.setDrawable(R.mipmap.ic_flight_takeoff_black_24dp);
        return f;
    }
*/

    @Override
    public void callLocalListFlight(final ChargeMoveContract.OnChargeMoveListener onFlightListListener) {

        compositeDisposable.add(getAppDataManager().getAppLocalData().getFlightServiceLocal().findAllFlights()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(Observable.just(new ArrayList<>()))//se agrega esta linea aparentemente no funciona
                .onExceptionResumeNext(Observable.just(new ArrayList<>()))//se agrega esta linea aparentemente no funciona
                .subscribeWith(new DisposableObserver<List<Flight>>() {
                    @Override
                    public void onNext(@NonNull List<Flight> flightList) {
                        Timber.d("interactor - callLocalListFlight - flightList:"+flightList.size());
                        //onFlightListListener.updateListAutocomplete(flightList);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Timber.e("callLocalListFlight - onError");
                        Timber.e(e);
                        //onFlightListListener.updateListAutocomplete(new ArrayList<>());
                    }

                    @Override
                    public void onComplete() {
                        onFlightListListener.showProgressContent();
                    }
                }));
    }


    @Override
    public void callApiListFlightIni(final ChargeMoveContract.OnChargeMoveListener onChargeMoveListener ) {

        getAppDataManager().getAppPreferencesData().saveUserParameter(new UserParameter());
        onChargeMoveListener.updateUser( getAppDataManager().getAppPreferencesData().getAuthorization() );
        /*
        compositeDisposable.add(getAppDataManager().getAppLocalData().getFlightServiceLocal().findAllFlightAssociate()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<List<Flight>>() {
                    @Override
                    public void onNext(@NonNull List<Flight> flightList) {
                        Timber.e("interactor - callApiListFlightIni - paso1");
                        //onFlightListListener.updateFlightList(flightList);
                        onChargeMoveListener.updateUser( getAppDataManager().getAppPreferencesData().getAuthorization() );
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Timber.e(e);
                        //onFlightListListener.showProgressError(e.getMessage());
                        //onFlightListListener.updateFlightList(new ArrayList<>());
                    }

                    @Override
                    public void onComplete() {
                        onChargeMoveListener.showProgressContent();
                    }
                }));
                */
    }


    @Override
    public void callLocalFindFlightByCode(final ChargeMoveContract.OnChargeMoveListener onFlightListListener, String code) {

        compositeDisposable.add(getAppDataManager().getAppLocalData().getFlightServiceLocal().findFlightByCode(code)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Flight>() {
                    @Override
                    public void onNext(@NonNull Flight flight) {
                        Timber.e("interactor - callLocalFindFlightByCode - paso1");
                        //onFlightListListener.addFlightToList(0, flight);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Timber.e(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }

    @Override
    public void onListTractor(final ChargeMoveContract.OnChargeMoveListener onChargeMoveListener) {

        if(getAppDataManager().getAppPreferencesData().getAuthorization().getUser().getUnidad()
                .equals(ParameterEnum.TRACTOR.getValue())) {
            //compositeDisposable.add(getAppDataManager().getAppLocalData().getUserServiceLocal().findAllTractors()
            compositeDisposable.add(getAppDataManager().getAppLocalData().getUserServiceLocal().findAllVehicles(ParameterEnum.TRACTOR.getValue())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(new DisposableObserver<List<Parameter>>() {
                        @Override
                        public void onNext(@io.reactivex.annotations.NonNull List<Parameter> list) {
                            Timber.d("onListTractor " + list.size());
                            onChargeMoveListener.onUpdateListTractor(list);
                            //onChargeMoveListener.updateUser( getAppDataManager().getAppPreferencesData().getAuthorization() );
                        }

                        @Override
                        public void onError(@io.reactivex.annotations.NonNull Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    }));
        }else if(
                getAppDataManager().getAppPreferencesData().getAuthorization().getUser().getUnidad()
                        .equals(ParameterEnum.CAMION.getValue())){
            compositeDisposable.add(getAppDataManager().getAppLocalData().getUserServiceLocal().findAllVehicles(ParameterEnum.CAMION.getValue())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(new DisposableObserver<List<Parameter>>() {
                        @Override
                        public void onNext(@io.reactivex.annotations.NonNull List<Parameter> list) {
                            Timber.d("onListTractor "+list.size());
                            onChargeMoveListener.onUpdateListTractor(list);
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

    @Override
    public void saveUserParameters(String vehicleType, String vehicleValue) {
        UserParameter userParameter = getAppDataManager().getAppPreferencesData().getUserParameter();
        userParameter.setVehicleType(vehicleType);
        userParameter.setVehicleValue(vehicleValue);
        getAppDataManager().getAppPreferencesData().saveUserParameter(userParameter);
    }
}
