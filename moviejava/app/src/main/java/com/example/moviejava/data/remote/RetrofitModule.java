package com.example.moviejava.data.remote;


import android.app.Application;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import okhttp3.Cache;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class RetrofitModule {
    @Provides
    public Cache provideHttpCache(Application application) {
        return new Cache(application.getCacheDir(), (long) (10 * 1024 * 1024));
    }


    @Provides
    public Gson provideGson() {
        return new GsonBuilder().setLenient()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
    }


    @Provides
    public HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    @Provides
    public OkHttpClient provideOkHttpClient(HttpLoggingInterceptor httpLoggingInterceptor,Interceptor interceptor) {
        return new OkHttpClient.Builder()
                .readTimeout(20, TimeUnit.SECONDS)
                .connectTimeout(2000, TimeUnit.MILLISECONDS)
                .retryOnConnectionFailure(true)
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(interceptor).build();
    }

    @Provides
    public Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(Constant.URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }
    @Provides
    public Interceptor provideInterceptor(){
        return chain -> {
            Request originalRequest = chain.request();
            HttpUrl.Builder urlBuilder = originalRequest.url().newBuilder()
                    .addQueryParameter("api_key" ,Constant.KEY).addQueryParameter(
                            "language","en-US"
                    );
            Request modifiedRequest = originalRequest.newBuilder()
                    .url(urlBuilder.build())
                    .build();
            return chain.proceed(modifiedRequest);
        };
    }
}