package fr.mastersid.massil.weather.repository

import fr.mastersid.massil.weather.db.Weather
import fr.mastersid.massil.weather.data.WeatherResponse
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject

class WeatherRepositoryDummyImpl @Inject constructor() : WeatherRepository {

    override val weatherResponse = MutableSharedFlow<WeatherResponse>()

    override suspend fun updateWeatherInfo () {
        weatherResponse.emit(WeatherResponse.Pending)
        delay(1000)
        weatherResponse.emit(
            WeatherResponse.Success(
                listOf(
                    Weather(1, " Saint-Etienne-du-Rouvray ", 11.0f),
                    Weather (3 , " Rouen ", (1..9).random().toFloat() ) ,
                    Weather (4 , " Petit Quevilly ", 8.3f)
                )
            )
        )
    }
}