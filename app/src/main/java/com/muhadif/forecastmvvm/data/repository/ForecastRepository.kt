package com.muhadif.forecastmvvm.data.repository

import androidx.lifecycle.LiveData
import com.muhadif.forecastmvvm.data.db.entity.WeatherLocation
import com.muhadif.forecastmvvm.data.db.unitlocalized.current.UnitSpesificCurrentWeatherEntry
import com.muhadif.forecastmvvm.data.db.unitlocalized.future.UnitSpesificSimpleFutureWeatherEntry
import java.time.LocalDate

interface ForecastRepository {
    suspend fun getCurrentWeather(
        metric : Boolean) : LiveData<out UnitSpesificCurrentWeatherEntry>

    suspend fun getFutureWeatherList(
        startDate : LocalDate,
        metric : Boolean) : LiveData<out List<UnitSpesificSimpleFutureWeatherEntry>>

    suspend fun getWeatherLocation() : LiveData<WeatherLocation>

}