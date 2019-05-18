package com.muhadif.forecastmvvm

import android.app.Application
import android.content.Context
import android.location.Location
import com.google.android.gms.location.LocationServices
import com.jakewharton.threetenabp.AndroidThreeTen
import com.muhadif.forecastmvvm.data.db.ForecastDatabase
import com.muhadif.forecastmvvm.data.network.*
import com.muhadif.forecastmvvm.data.provider.LocationProvider
import com.muhadif.forecastmvvm.data.provider.LocationProviderImpl
import com.muhadif.forecastmvvm.data.provider.UnitProvider
import com.muhadif.forecastmvvm.data.provider.UnitProviderImpl
import com.muhadif.forecastmvvm.data.repository.ForecastRepository
import com.muhadif.forecastmvvm.data.repository.ForecastRepositoryImpl
import com.muhadif.forecastmvvm.ui.weather.current.CurrentWeatherViewModelFactory
import com.muhadif.forecastmvvm.ui.weather.future.list.FutureListWeatherViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.androidCoreModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton


class ForecastApplication : Application(), KodeinAware {
    override val kodein: Kodein = Kodein.lazy {
        import(androidCoreModule(this@ForecastApplication))


        bind() from singleton {
            ForecastDatabase(instance())
        }

        bind() from singleton {
            instance<ForecastDatabase>().currentWeatherDao()
        }

        bind() from singleton {
            instance<ForecastDatabase>().futureWeatherDao()
        }

        bind() from singleton {
            instance<ForecastDatabase>().weatherLocationDao()
        }

        bind() from singleton {
            ConnectivityInterceptorImpl(instance())
        }
        bind() from singleton {
            ApixuWeatherApiService(instance())
        }

        bind() from provider { LocationServices.getFusedLocationProviderClient(instance<Context>()) }

        bind<WeatherNetworkDataSource>() with singleton {
            WeatherNetworkDataSourceImpl(instance())
        }

        bind<LocationProvider>() with singleton { LocationProviderImpl(instance(), instance()) }

        bind<ForecastRepository>() with singleton {
            ForecastRepositoryImpl(instance(), instance(), instance(), instance(), instance())
        }

        bind<UnitProvider>() with singleton {
            UnitProviderImpl(instance())
        }

        bind() from provider {
            CurrentWeatherViewModelFactory(instance(), instance())
        }

        bind() from provider {
            FutureListWeatherViewModelFactory(instance(), instance())
        }
    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
    }
}