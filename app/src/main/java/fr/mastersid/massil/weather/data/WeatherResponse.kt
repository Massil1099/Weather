package fr.mastersid.massil.weather.data

import fr.mastersid.massil.weather.db.Weather

sealed interface WeatherResponse {

    data object Pending : WeatherResponse

    @JvmInline
    value class Success (val list : List<Weather>) : WeatherResponse
}

