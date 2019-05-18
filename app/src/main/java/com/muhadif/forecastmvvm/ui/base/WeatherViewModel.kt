package com.muhadif.forecastmvvm.ui.base

import androidx.lifecycle.ViewModel
import com.muhadif.forecastmvvm.data.provider.UnitProvider
import com.muhadif.forecastmvvm.data.repository.ForecastRepository
import com.muhadif.forecastmvvm.internal.UnitSystem
import com.muhadif.forecastmvvm.internal.lazyDeferred

abstract class WeatherViewModel(
    private val forecastRepository: ForecastRepository,
    val unitProvider: UnitProvider
) : ViewModel() {
    private val unitSystem = unitProvider.getUnitSystem()

    val isMetric : Boolean
        get() = unitSystem == UnitSystem.METRIC

    val weatherLocation by lazyDeferred {
        forecastRepository.getWeatherLocation()
    }

}