package fr.mastersid.massil.weather.data

sealed interface WeatherResponse {

    data object Pending : WeatherResponse

    @JvmInline
    value class Success (val list : List<Weather>) : WeatherResponse
}

