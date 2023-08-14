package com.example.movie.data.remote

import android.app.Application
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    @Provides
     fun provideHttpCache(application: Application) =
        Cache(application.cacheDir, (10 * 1024 * 1024).toLong())

    @Provides
     fun provideGson(): Gson = GsonBuilder().setLenient()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .create()

    @Provides
     fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor, interceptor: Interceptor): OkHttpClient =
        OkHttpClient.Builder().readTimeout(20, TimeUnit.SECONDS)
            .connectTimeout(2000, TimeUnit.MILLISECONDS).retryOnConnectionFailure(true)
            .addInterceptor(httpLoggingInterceptor).addInterceptor(interceptor).build()

    @Provides
    fun provideInterceptor(): Interceptor {
        return Interceptor { chain: Interceptor.Chain ->
            val originalRequest: Request = chain.request()
            val urlBuilder: HttpUrl.Builder =
                originalRequest.url().newBuilder().addQueryParameter("api_key", Constant.KEY)
                    .addQueryParameter(
                        "language", "en-US"
                    )
            val modifiedRequest: Request =
                originalRequest.newBuilder().url(urlBuilder.build()).build()
            chain.proceed(modifiedRequest)
        }
    }

    @Provides
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder().baseUrl(Constant.URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create()).client(okHttpClient).build()
}