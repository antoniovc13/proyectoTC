package com.tivit.talmatc.data.local.service;

import com.tivit.talmatc.data.local.DbOpenHelper;
import com.tivit.talmatc.data.local.constant.ParameterEnum;
import com.tivit.talmatc.data.local.model.Parameter;
import com.tivit.talmatc.data.local.model.User;
import com.tivit.talmatc.data.local.repository.ParameterRepository;
import com.tivit.talmatc.data.local.repository.ParameterRepositoryImpl;
import com.tivit.talmatc.data.local.repository.UserRepository;
import com.tivit.talmatc.data.local.repository.UserRepositoryImpl;
import com.tivit.talmatc.data.remote.model.Authorization;
import com.tivit.talmatc.data.remote.model.Login;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Alexzander Guillermo on 14/09/2017.
 */

public class UserServiceLocalImpl implements UserServiceLocal {

    private DbOpenHelper dbOpenHelper;
    private ParameterRepository parameterRepository;
    private UserRepository userRepository;

    public UserServiceLocalImpl(DbOpenHelper dbOpenHelper) {
        this.dbOpenHelper = dbOpenHelper;
        parameterRepository = new ParameterRepositoryImpl(dbOpenHelper);
        userRepository = new UserRepositoryImpl(dbOpenHelper);
    }
/*
    @Override
    public Observable<List<Parameter>> findAllSituations() {
        return Observable.fromCallable(() -> parameterRepository.findAllByEntity(ParameterEnum.SITUACION.getValue()));
    }

    @Override
    public Observable<List<Parameter>> findAllActions() {
        return Observable.fromCallable(() -> parameterRepository.findAllByEntity(ParameterEnum.ACCION.getValue()));
    }

    @Override
    public Observable<Boolean> isSituationsEmpty() {
        return Observable.fromCallable(() -> !(parameterRepository.countByEntidad(new String[]{ParameterEnum.SITUACION.getValue()}) > 0));
    }

    @Override
    public Observable<Boolean> isActionsEmpty() {
        return Observable.fromCallable(() -> !(parameterRepository.countByEntidad(new String[]{ParameterEnum.ACCION.getValue()}) > 0));
    }
*/
    @Override
    public Observable<Boolean> saveCatalogParameter(List<Parameter> list) {
        return Observable.fromCallable(() -> {
            parameterRepository.insertAll(list);
            return true;
        });
    }

    //TODO agregado AVC
    @Override
    public Observable<Authorization> getAuthorizationLogin(Login login) {
        return Observable.fromCallable(() ->
            userRepository.getAuthorizationLogin(login)
        );
    }

    @Override
    public Observable<List<Parameter>> findAllTractors() {
        return Observable.fromCallable(() -> parameterRepository.findAllByEntity(ParameterEnum.TRACTOR.getValue()));
    }

    @Override
    public Observable<List<Parameter>> findAllVehicles(String typeVehicle) {
        return Observable.fromCallable(() -> parameterRepository.findAllByEntity(typeVehicle));
    }


    public Observable<User> findUserByUsernameAndPassword(Login login) {
        return Observable.fromCallable(() ->
                userRepository.findUserByUsernameAndPassword(login.getUsername(), login.getUsername())
        );
    }
}
