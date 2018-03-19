package com.tivit.talmatc.data.remote.service;

import com.tivit.talmatc.data.remote.ApiEndPoint;
import com.tivit.talmatc.data.local.model.Parameter;
import com.tivit.talmatc.data.remote.model.Authorization;
import com.tivit.talmatc.data.remote.model.Login;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by Alexzander Guillermo on 05/09/2017.
 */

public interface UserService {

    @POST(ApiEndPoint.WS_LOGIN)
    Observable<Authorization> getAuthorizationLogin(@Body Login login);

    @GET(ApiEndPoint.WS_REFRESH_TOKEN)
    Call<Authorization> getAuthorizationRefreshToken(@Header("X-Authorization") String refreshtoken);

    @GET(ApiEndPoint.WS_CATALOG_SIITUATION)
    Observable<List<Parameter>> getCatalogSituations(@Header("X-Authorization") String token);

    @GET(ApiEndPoint.WS_CATALOG_ACTION)
    Observable<List<Parameter>> getCatalogActions(@Header("X-Authorization") String token);

}
