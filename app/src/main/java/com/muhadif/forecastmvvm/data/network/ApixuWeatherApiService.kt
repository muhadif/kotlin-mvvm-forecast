package com.muhadif.forecastmvvm.data.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.muhadif.forecastmvvm.data.db.entity.Current
import com.muhadif.forecastmvvm.data.network.response.CurrentWeatherResponse
import com.muhadif.forecastmvvm.data.network.response.FutureWeatherResponse
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY = "fad833e37e86430091d161900190503"


interface ApixuWeatherApiService {

    //http://api.apixu.com/v1/current.json?key=fad833e37e86430091d161900190503&q=Paris
    @GET("current.json")
    fun getCurrentWeather(
        @Query("q") location: String,
        @Query("lang") languageCode: String = "en"
    ) : Deferred<CurrentWeatherResponse>


    //https://api.apixu.com/v1/forecast.json?key=9428df3aabe14012b7b70048191205&q=Bantul&days=7
    @GET("forecast.json")
    fun getFutureWeather(
        @Query("q") location: String,
        @Query("days") days : Int,
        @Query("lang") languageCode: String = "en"
    ) : Deferred<FutureWeatherResponse>

    companion object {
        operator fun invoke(
            connectivityInterceptorImpl: ConnectivityInterceptorImpl
        ) : ApixuWeatherApiService {
            val requestInterceptor = Interceptor{
                val url = it.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter("key", API_KEY)
                    .build()
                val request = it.request()
                    .newBuilder()
                    .url(url)
                    .build()

                return@Interceptor it.proceed(request)
            }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .addInterceptor(connectivityInterceptorImpl)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://api.apixu.com/v1/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApixuWeatherApiService::class.java)
        }


    }
}