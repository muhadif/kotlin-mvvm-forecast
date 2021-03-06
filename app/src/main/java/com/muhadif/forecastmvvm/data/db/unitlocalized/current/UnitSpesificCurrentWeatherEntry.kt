package com.muhadif.forecastmvvm.data.db.unitlocalized.current

interface UnitSpesificCurrentWeatherEntry {
    val temperature : Double
    val conditionText : String
    val conditionIconUrl : String
    val windSpeed : Double
    val windDirection : String
    val precipitationVolume : Double
    val feelLikeTemperature : Double
    val visibilityDistance : Double
}