package com.muhadif.forecastmvvm.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.muhadif.forecastmvvm.data.db.entity.FutureWeatherEntry
import com.muhadif.forecastmvvm.data.db.unitlocalized.current.ImperialCurrentWeatherEntry
import com.muhadif.forecastmvvm.data.db.unitlocalized.future.ImperialSimpleFutureWeatherEntry
import com.muhadif.forecastmvvm.data.db.unitlocalized.future.MetricSimpleFutureWeatherEntry
import java.time.LocalDate

@Dao
interface FutureWeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(futureWeatherEntries : List<FutureWeatherEntry>)

    @Query("select * from future_weather where date(date) >= date(:startDate)")
    fun getSimpleWeatherForecastsMetric(startDate : LocalDate) : LiveData< List<MetricSimpleFutureWeatherEntry>>

    @Query("select * from future_weather where date(date) >= date(:startDate)")
    fun getSimpleWeatherForecastsImperial(startDate : LocalDate) : LiveData< List<ImperialSimpleFutureWeatherEntry>>

    @Query("select count(id) from future_weather where date(date) >= date(:startDate)")
    fun countFutureWeather(startDate: LocalDate) : Int

    @Query("delete from future_weather where(date) < date(:firstDateToKeep)")
    fun deleteOldEntries(firstDateToKeep : LocalDate)
}