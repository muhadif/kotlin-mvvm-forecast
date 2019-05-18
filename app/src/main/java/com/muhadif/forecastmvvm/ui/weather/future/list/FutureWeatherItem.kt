package com.muhadif.forecastmvvm.ui.weather.future.list

import com.muhadif.forecastmvvm.R
import com.muhadif.forecastmvvm.data.db.unitlocalized.future.MetricSimpleFutureWeatherEntry
import com.muhadif.forecastmvvm.data.db.unitlocalized.future.UnitSpesificSimpleFutureWeatherEntry
import com.muhadif.forecastmvvm.internal.glide.GlideApp
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.current_weather_fragment.*
import kotlinx.android.synthetic.main.item_future_weather.*
import kotlinx.android.synthetic.main.item_future_weather.iv_condition_icon
import kotlinx.android.synthetic.main.item_future_weather.tv_condition
import kotlinx.android.synthetic.main.item_future_weather.tv_temperature
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

class FutureWeatherItem(
    val weatherEntry: UnitSpesificSimpleFutureWeatherEntry
) : Item() {
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.apply {
            tv_condition.text = weatherEntry.conditionText
            updateDate()
            updateConditionImage()
            updateTemperature()
        }
    }

    override fun getLayout(): Int = R.layout.item_future_weather

    private fun ViewHolder.updateDate(){
        val dateFormated = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)
        tv_date.text = weatherEntry.date.format(dateFormated)
    }

    private fun ViewHolder.updateTemperature() {
        val unitAbbreviation = if (weatherEntry is MetricSimpleFutureWeatherEntry) "'C"
        else "'F"
        tv_temperature.text = "${weatherEntry.avgTemperature} ${unitAbbreviation}"
    }

    private fun ViewHolder.updateConditionImage(){
        GlideApp.with(this.containerView)
            .load("https:${weatherEntry.conditionIconUrl}")
            .into(iv_condition_icon)
    }
}
