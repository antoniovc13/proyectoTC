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

public class ParameterServiceLocalImpl implements ParameterServiceLocal {

    private DbOpenHelper dbOpenHelper;
    private ParameterRepository parameterRepository;

    public ParameterServiceLocalImpl(DbOpenHelper dbOpenHelper) {
        this.dbOpenHelper = dbOpenHelper;
        parameterRepository = new ParameterRepositoryImpl(dbOpenHelper);
    }

    @Override
    public Observable<List<Parameter>> findAllPointInit(String pointInit) {
        return Observable.fromCallable(() -> parameterRepository.findAllByEntity(pointInit));
    }



}
