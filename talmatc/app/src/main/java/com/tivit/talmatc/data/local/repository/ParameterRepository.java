package com.tivit.talmatc.data.local.repository;

import com.tivit.talmatc.data.local.model.Parameter;

import java.util.List;

/**
 * Created by Alexzander Guillermo on 07/09/2017.
 */

public interface ParameterRepository extends GenericRepository<Parameter> {

    List<Parameter> findAllByEntity(String filter);

    long countByEntidad(String[] whereArgs);

    /*
    //agregado
    Authorization getAuthorizationLogin(Login login);
    */
}
