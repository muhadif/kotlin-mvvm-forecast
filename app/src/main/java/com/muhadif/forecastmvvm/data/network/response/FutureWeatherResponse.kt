package com.muhadif.forecastmvvm.data.network.response


import com.google.gson.annotations.SerializedName
import com.muhadif.forecastmvvm.data.db.entity.WeatherLocation

data class FutureWeatherResponse(
    @SerializedName("forecast")
    val futureWeatherEntry: Forecast,
    @SerializedName("location")
    val location: WeatherLocation
)