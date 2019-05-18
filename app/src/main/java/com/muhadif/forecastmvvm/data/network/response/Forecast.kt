package com.muhadif.forecastmvvm.data.network.response


import com.google.gson.annotations.SerializedName
import com.muhadif.forecastmvvm.data.db.entity.FutureWeatherEntry

data class Forecast(
    @SerializedName("forecastday")
    val entries: List<FutureWeatherEntry>
)