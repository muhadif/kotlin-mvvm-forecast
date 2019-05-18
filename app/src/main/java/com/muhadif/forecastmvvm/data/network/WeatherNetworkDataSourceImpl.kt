package com.muhadif.forecastmvvm.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.muhadif.forecastmvvm.data.db.entity.Current
import com.muhadif.forecastmvvm.data.network.response.CurrentWeatherResponse
import com.muhadif.forecastmvvm.data.network.response.FutureWeatherResponse
import com.muhadif.forecastmvvm.internal.NoConnectivityException

const val FORECAST_DAYS_COUNT = 7

class WeatherNetworkDataSourceImpl(
    private val apixuWeatherApiService: ApixuWeatherApiService
) : WeatherNetworkDataSource {

    private val _downloadCurrentWeather = MutableLiveData<CurrentWeatherResponse>()
    override val downloadCurrentWeather: LiveData<CurrentWeatherResponse>
        get() = _downloadCurrentWeather

    override suspend fun fetchCurrentWeather(location: String, languageCode: String) {
        try {
            val fetchCurrentWeather = apixuWeatherApiService
                .getCurrentWeather(location, languageCode)
                .await()
            _downloadCurrentWeather.postValue(fetchCurrentWeather)
        } catch (e: NoConnectivityException){
            Log.e("Connectivity", "No Internet Connection" + e)
        }
    }

    private val _downloadFutureWeather = MutableLiveData<FutureWeatherResponse>()
    override val downloadFutureWeather: MutableLiveData<FutureWeatherResponse>
        get() = _downloadFutureWeather

    override suspend fun fetchFutureWeather(location: String, languageCode: String) {
        try {
            val fetchFutureWeather = apixuWeatherApiService
                .getFutureWeather(location, FORECAST_DAYS_COUNT,languageCode)
                .await()
            _downloadFutureWeather.postValue(fetchFutureWeather)
        } catch (e: NoConnectivityException){
            Log.e("Connectivity", "No Internet Connection" + e)
        }
    }



}