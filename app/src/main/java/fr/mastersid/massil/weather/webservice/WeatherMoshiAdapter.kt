package fr.mastersid.massil.weather.webservice

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import fr.mastersid.massil.weather.data.ListWeatherJson
import fr.mastersid.massil.weather.data.MainJson
import fr.mastersid.massil.weather.db.Weather
import fr.mastersid.massil.weather.data.WeatherJson

class WeatherMoshiAdapter {
    @FromJson
    fun fromJson ( listWeatherJson : ListWeatherJson) : List <Weather> {
        return listWeatherJson.list.map { weatherJson ->
            Weather ( weatherJson.id , weatherJson.name , weatherJson.main.temp)
        }
    }

    @ToJson
    fun toJson ( listWeather : List < Weather >) : ListWeatherJson {
        return ListWeatherJson (
            listWeather .map { weather ->
                WeatherJson(weather.id, weather.city, MainJson(weather.temperature))
            }
        )
    }
}
