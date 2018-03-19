package com.tivit.talmatc.data.remote.model;

import com.tivit.talmatc.utils.ErrorUtils;

import java.util.Arrays;
import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import retrofit2.HttpException;
import timber.log.Timber;

/**
 * Created by Alexzander Guillermo on 26/08/2017.
 */

public abstract class ApiCallback<M> extends DisposableObserver<M> {

    public abstract void onSuccess(M model);

    public abstract void onFailure(List<String> messages);

    public abstract void onFinish();

    @Override
    public void onNext(@NonNull M model) {
        onSuccess(model);
    }

    @Override
    public void onError(@NonNull Throwable e) {
        Timber.e(e);
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            int code = httpException.code();
            Timber.i("code : %s", code);

            List<String> message;

            if (httpException.response().errorBody() != null ) {
                APIError error = ErrorUtils.parseError(httpException.response());
                message = error.getMessages();
            } else {
                message = Arrays.asList(httpException.getMessage());
            }
            onFailure(message);

        } else {
            onFailure(Arrays.asList(e.getMessage()));
        }
//        onFinish();
    }

    @Override
    public void onComplete() {
        onFinish();
    }

}
