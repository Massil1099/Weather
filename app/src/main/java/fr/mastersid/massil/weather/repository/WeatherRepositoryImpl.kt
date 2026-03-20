package fr.mastersid.massil.weather.repository

import android.util.Log
import fr.mastersid.massil.weather.data.WeatherResponse
import fr.mastersid.massil.weather.db.WeatherDao
import fr.mastersid.massil.weather.webservice.WeatherWebservice
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import java.io.IOException
import java.text.Normalizer
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class WeatherRepositoryImpl @Inject constructor(
    private val weatherWebservice : WeatherWebservice,
    private val weatherDao: WeatherDao
) : WeatherRepository {

    private val pendingFlow : MutableSharedFlow <WeatherResponse> = MutableSharedFlow()


    override val weatherResponse =
        listOf (
            pendingFlow,
            weatherDao.getWeatherListFlow().map { list -> WeatherResponse.Success(list) }
        ).merge()

    override suspend fun updateWeatherInfo () {
        pendingFlow.emit (WeatherResponse.Pending) // voir diapo suivante
        val list = weatherWebservice
            . getWeatherList ( latitude = 49.38697f, longitude = 1.0668312f)
            . distinctBy { weather ->
                "\\p{InCombiningDiacriticalMarks}"
                    .toRegex()
                    .replace(
                        Normalizer.normalize( weather.city , Normalizer.Form.NFD),
                        ""
                    )
            }
        weatherDao.insertAll(list)
    }


}