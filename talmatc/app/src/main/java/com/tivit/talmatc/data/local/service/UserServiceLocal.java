package com.tivit.talmatc.data.local.service;

import com.tivit.talmatc.data.local.model.Parameter;
import com.tivit.talmatc.data.local.model.User;
import com.tivit.talmatc.data.remote.model.Authorization;
import com.tivit.talmatc.data.remote.model.Login;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Alexzander Guillermo on 14/09/2017.
 */

public interface UserServiceLocal {
    /*
    Observable<List<Parameter>> findAllSituations();

    Observable<List<Parameter>> findAllActions();

    Observable<Boolean> isSituationsEmpty();

    Observable<Boolean> isActionsEmpty();
*/
    Observable<Boolean> saveCatalogParameter(List<Parameter> list);

    Observable<Authorization> getAuthorizationLogin(Login login);

    Observable<List<Parameter>> findAllTractors();

    Observable<List<Parameter>> findAllVehicles(String typeVehicle);

    Observable<User> findUserByUsernameAndPassword(Login login);
}
