package com.muhadif.forecastmvvm.data.provider

import com.muhadif.forecastmvvm.internal.UnitSystem

interface UnitProvider {
    fun getUnitSystem() : UnitSystem
}