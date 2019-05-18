package com.muhadif.forecastmvvm.ui.weather.current

import androidx.lifecycle.ViewModel;
import com.muhadif.forecastmvvm.data.provider.UnitProvider
import com.muhadif.forecastmvvm.data.repository.ForecastRepository
import com.muhadif.forecastmvvm.internal.UnitSystem
import com.muhadif.forecastmvvm.internal.lazyDeferred
import com.muhadif.forecastmvvm.ui.base.WeatherViewModel

class CurrentWeatherViewModel(
    private val forecastRepository: ForecastRepository,
    unitProvider: UnitProvider
) : WeatherViewModel(forecastRepository, unitProvider) {

    val weather by lazyDeferred{
        forecastRepository.getCurrentWeather(isMetric)
    }

}
