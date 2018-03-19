package com.tivit.talmatc.utils;

import com.tivit.talmatc.TivitApplication;
import com.tivit.talmatc.data.AppDataManager;
import com.tivit.talmatc.data.remote.model.APIError;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.HttpException;
import retrofit2.Response;
import timber.log.Timber;

/**
 * Created by Alexzander Guillermo on 29/08/2017.
 */

public class ErrorUtils {
    public static APIError parseError(Response<?> response) {
        Converter<ResponseBody, APIError> converter =
                AppDataManager.getInstance(TivitApplication.getAppContext()).getAppRemoteData().getRetrofit()
                        .responseBodyConverter(APIError.class, new Annotation[0]);

        APIError error;

        try {
            error = converter.convert(response.errorBody());
        } catch (IOException e) {
            return new APIError();
        }

        return error;
    }

    public static List<String> onFailure(Throwable e) {
        Timber.e(e);
        List<String> message = null;

        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            int code = httpException.code();
            Timber.i("code : %s", code);

            if (httpException.response().errorBody() != null ) {
                APIError error = ErrorUtils.parseError(httpException.response());
                message = error.getMessages();
            } else {
                message = Arrays.asList(httpException.getMessage());
            }

        } else {
            message = Arrays.asList(e.getMessage());
        }

        return message;
    }

    public static boolean isAuthorizedException(Throwable e) {
        boolean isUnauthorized = false;
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            int code = httpException.code();
            if(code == 401) {
                isUnauthorized = true;
            }
        }
        return isUnauthorized;
    }

    public static int getCodeHttp(Throwable e) {
        int code = 0;
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            code = httpException.code();
        }
        return code;
    }

}
