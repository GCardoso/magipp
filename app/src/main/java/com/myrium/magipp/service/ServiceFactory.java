package com.myrium.magipp.service;

import retrofit.RestAdapter;

/**
 * Created by guilhermecardoso on 9/1/17.
 */

public class ServiceFactory {
    public static <T> T createRetrofitService(final Class<T> tClass, final String endPoint) {
        final RestAdapter restAdapter = new RestAdapter
                .Builder()
                .setEndpoint(endPoint)
                .build();
        T service = restAdapter.create(tClass);

        return service;
    }

}
