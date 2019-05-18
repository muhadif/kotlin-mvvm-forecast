package com.muhadif.forecastmvvm.ui.weather.future.list

import androidx.lifecycle.ViewModel;
import com.muhadif.forecastmvvm.data.provider.UnitProvider
import com.muhadif.forecastmvvm.data.repository.ForecastRepository
import com.muhadif.forecastmvvm.internal.UnitSystem
import com.muhadif.forecastmvvm.internal.lazyDeferred
import com.muhadif.forecastmvvm.ui.base.WeatherViewModel
import java.time.LocalDate

class FutureListWeatherViewModel(
    private val forecastRepository: ForecastRepository,
    unitProvider: UnitProvider
) : WeatherViewModel(forecastRepository, unitProvider) {

    val weatherEntries by lazyDeferred{
        forecastRepository.getFutureWeatherList(LocalDate.now(), super.isMetric)
    }

}
