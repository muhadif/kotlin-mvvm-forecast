package com.muhadif.forecastmvvm.ui.weather.current

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer

import com.muhadif.forecastmvvm.R
import com.muhadif.forecastmvvm.internal.glide.GlideApp
import com.muhadif.forecastmvvm.ui.base.ScopedFragment
import kotlinx.android.synthetic.main.current_weather_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class CurrentWeatherFragment : ScopedFragment(), KodeinAware {

    override val kodein by kodein()
    private val viewModelFactory:CurrentWeatherViewModelFactory by instance()

    private lateinit var viewModel: CurrentWeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.current_weather_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(CurrentWeatherViewModel::class.java)

        bindUI()

    }

    private fun bindUI() = launch{
        val currentWeather = viewModel.weather.await()

        val weatherLocation = viewModel.weatherLocation.await()

        currentWeather.observe(this@CurrentWeatherFragment, Observer {
            if (it == null) return@Observer

            group_loading.visibility = View.GONE


            updateDateToDay()
            updateTemperature(it.temperature, it.feelLikeTemperature)
            updatePrecipitation(it.precipitationVolume)
            updateWind(it.windDirection, it.windSpeed)
            updateVisibility(it.visibilityDistance)

            GlideApp.with(this@CurrentWeatherFragment)
                .load("https:${it.conditionIconUrl}")
                .into(iv_condition_icon)

        })

        weatherLocation.observe(this@CurrentWeatherFragment, Observer {
            if (it == null) return@Observer

            updateLocation(it.name)

        })
    }

    private fun chooseLocalizedUnitAbbreviation(metric : String, imperial : String) : String{
        return if(viewModel.isMetric) metric else imperial
    }

    private fun updateLocation(location : String){
        (activity as? AppCompatActivity)?.supportActionBar?.title = location
    }

    private fun updateDateToDay(){
        (activity as? AppCompatActivity)?.supportActionBar?.subtitle = getString(R.string.today)
    }

    private fun updateTemperature(temperature : Double, feelsLike : Double){
        val unitAbbreviation = chooseLocalizedUnitAbbreviation("'C", "'F")
        tv_temperature.text = "$temperature$unitAbbreviation"
        tv_feel_like.text = "Feels like $feelsLike$unitAbbreviation"
    }

    private fun updatePrecipitation(precipitation : Double){
        val unitAbbreviationit = chooseLocalizedUnitAbbreviation("mm", "in")
        tv_precipitation.text = "Precipitation: $precipitation $unitAbbreviationit"
    }

    private fun updateWind(windDirection : String, windSpeed : Double){
        val unitAbbreviationit = chooseLocalizedUnitAbbreviation("kph", "mph")
        tv_wind.text = "Wind: $windDirection, $windSpeed $unitAbbreviationit"
    }

    private fun updateVisibility(visibilityDistance : Double) {
        val unitAbbreviation = chooseLocalizedUnitAbbreviation("km", "mil")
    }

}
