package fr.mastersid.massil.weather.repository

import android.util.Log
import fr.mastersid.massil.weather.data.WeatherResponse
import fr.mastersid.massil.weather.webservice.WeatherWebservice
import kotlinx.coroutines.flow.MutableSharedFlow
import java.io.IOException
import java.text.Normalizer
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherWebservice : WeatherWebservice
) : WeatherRepository {

    override val weatherResponse = MutableSharedFlow<WeatherResponse>()

    override suspend fun updateWeatherInfo() {
        weatherResponse.emit(WeatherResponse.Pending)
        val list = weatherWebservice.getWeatherList (
            latitude = 49.38698f,
            longitude = 1.0668312f
        )
        weatherResponse.emit(WeatherResponse.Success(list.distinctBy { weather ->
            "\\p{InCombiningDiacriticalMarks}"
                . toRegex ()
                . replace (
                    Normalizer.normalize( weather.city, Normalizer.Form.NFD) ,
                    ""
                )
        }))

    }

}