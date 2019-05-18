package com.muhadif.forecastmvvm.data.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.muhadif.forecastmvvm.data.db.entity.Current
import com.muhadif.forecastmvvm.data.network.response.CurrentWeatherResponse
import com.muhadif.forecastmvvm.data.network.response.FutureWeatherResponse

interface WeatherNetworkDataSource {
    val downloadCurrentWeather: LiveData<CurrentWeatherResponse>
    val downloadFutureWeather: MutableLiveData<FutureWeatherResponse>

    suspend fun fetchCurrentWeather(
        location: String,
        languageCode : String
    )

    suspend fun fetchFutureWeather(
        location: String,
        languageCode : String
    )
}