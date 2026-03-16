package fr.mastersid.massil.weather.repository

import fr.mastersid.massil.weather.data.WeatherResponse
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    val weatherResponse: Flow<WeatherResponse>

    suspend fun updateWeatherInfo()
}