package com.tivit.talmatc.utils;

import android.support.annotation.Nullable;

import com.tivit.talmatc.TivitApplication;
import com.tivit.talmatc.data.AppDataManager;
import com.tivit.talmatc.data.remote.ApiEndPoint;
import com.tivit.talmatc.data.remote.model.Authorization;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Authenticator;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import timber.log.Timber;

/**
 * Created by Alexzander Guillermo on 26/09/2017.
 */

public class TokenAuthenticator implements Authenticator {

    private static final int RESPONSE_HTTP_RANK_2XX = 2;
    private static final int RESPONSE_HTTP_CLIENT_ERROR = 4;  // If failed by error 4xx...
    private static final int RESPONSE_HTTP_SERVER_ERROR = 5;  // If failed by error 5xx...

    private AppDataManager appDataManager;
    private Authorization authorization;

    @Nullable
    @Override
    public Request authenticate(Route route, Response response) throws IOException {
        appDataManager = AppDataManager.getInstance(TivitApplication.getAppContext());
        Timber.d("Authenticating for response: " + response);

            authorization = appDataManager.getAppPreferencesData().getAuthorization();

            if(authorization != null){
                int code = refreshToken() / 100;

                if(code != RESPONSE_HTTP_RANK_2XX) {                // If refresh token failed

                    if(code == RESPONSE_HTTP_CLIENT_ERROR || code == RESPONSE_HTTP_SERVER_ERROR ){
                        // logout
                        Timber.d("LOGOUT");
                        return null;
                    }
                }

                authorization = appDataManager.getAppPreferencesData().getAuthorization();
                String accessToken = ApiEndPoint.BEARER + authorization.getAccessToken();

                return response.request().newBuilder()
                        .header(ApiEndPoint.AUTHORIZATION, accessToken)
                        .build();
            }
            return null;
    }

    private int refreshToken() throws IOException {
        String refreshToken = ApiEndPoint.BEARER + authorization.getRefreshToken();

        int code = 0;

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .header(ApiEndPoint.AUTHORIZATION, refreshToken)
                .url(ApiEndPoint.WS_URL + ApiEndPoint.WS_REFRESH_TOKEN)
                .build();

        Response response = client.newCall(request).execute();

        if(response != null){
            code = response.code();

            switch (code){
                case 200:
                    try {
                        Timber.d("GUARDADO NUEVO TOKEN");
                        JSONObject jsonBody =  new JSONObject(response.body().string());
                        String newAuthtoken = jsonBody.getString("accessToken");
                        authorization.setAccessToken(newAuthtoken);
                        appDataManager.getAppPreferencesData().saveAuthorization(authorization);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    break;
                default:
                    try {
                        JSONObject jsonBodyE = new JSONObject(response.body().string());
                        Timber.d("REFRESH NO VASLIDO");
                        Timber.d(jsonBodyE.toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    break;
            }
        }

        return code;
    }

}
