package com.tivit.talmatc.utils;

import com.tivit.talmatc.TivitApplication;
import com.tivit.talmatc.data.AppDataManager;
import com.tivit.talmatc.data.remote.ApiEndPoint;
import com.tivit.talmatc.data.remote.model.Authorization;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Alexzander Guillermo on 26/09/2017.
 */

public class TokenInterceptor implements Interceptor {

    private AppDataManager appDataManager;

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();

        appDataManager = AppDataManager.getInstance(TivitApplication.getAppContext());
        Authorization currentAuth = appDataManager.getAppPreferencesData().getAuthorization();

        if(currentAuth == null) {
            return chain.proceed(originalRequest);
        }

        String accessToken = ApiEndPoint.BEARER + currentAuth.getAccessToken();

        // Add authorization header with updated authorization value to  intercepted request
        Request authorisedRequest = originalRequest.newBuilder()
                .addHeader(ApiEndPoint.AUTHORIZATION, accessToken)
                .build();
        return chain.proceed(authorisedRequest);
    }
}
