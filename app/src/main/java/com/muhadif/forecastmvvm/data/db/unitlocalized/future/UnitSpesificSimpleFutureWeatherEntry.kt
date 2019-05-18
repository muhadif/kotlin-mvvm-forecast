package com.muhadif.forecastmvvm.data.db.unitlocalized.future

import java.time.LocalDate

interface UnitSpesificSimpleFutureWeatherEntry {
    val date : LocalDate
    val avgTemperature : Double
    val conditionText : String
    val conditionIconUrl : String
}