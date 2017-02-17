package io.mindjet.jetgear.network;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Jet on 2/17/17.
 */

public class ServiceGen {
    private static Retrofit retrofit;
    private static String baseUrl = "";

    public static void init(String _baseUrl) {
        baseUrl = _baseUrl;
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static <T> T create(Class<T> clz) {
        if (baseUrl.equals("")) {
            throw new IllegalArgumentException("BaseUrl is null, do you forget to init the ServiceGen?");
        }
        return retrofit.create(clz);
    }
}
