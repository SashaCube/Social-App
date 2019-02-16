package com.pllug.course.oleksandr.havryliuk.utopiasocialapp.api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.pllug.course.oleksandr.havryliuk.utopiasocialapp.api.APIInterface.BASE_URL;

public class RetrofitClient {

    private static APIInterface api = null;

    public static APIInterface getApi() {
        if (api == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

            api = new Retrofit.Builder()
                    .baseUrl(APIInterface.BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(APIInterface.class);
        }
        return api;
    }
}
