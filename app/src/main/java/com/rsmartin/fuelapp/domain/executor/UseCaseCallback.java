package com.rsmartin.fuelapp.domain.executor;

/**
 * Use case callback.
 */

public interface UseCaseCallback<R> {

    void onResult(R result);


    void onError(Throwable error);

}
