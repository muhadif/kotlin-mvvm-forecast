package com.muhadif.forecastmvvm.data.network.response

import com.google.gson.annotations.SerializedName
import com.muhadif.forecastmvvm.data.db.entity.Current
import com.muhadif.forecastmvvm.data.db.entity.WeatherLocation

data class CurrentWeatherResponse(
    @SerializedName("current")
    val current: Current,
    @SerializedName("location")
    val location: WeatherLocation
)