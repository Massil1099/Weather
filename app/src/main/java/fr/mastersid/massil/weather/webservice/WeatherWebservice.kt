package fr.mastersid.massil.weather.webservice

import fr.mastersid.massil.weather.data.ListWeatherJson
import fr.mastersid.massil.weather.data.Weather
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


// https :// openweathermap .org/api to create account and get valid API key
private const val API_KEY = "c471790a5d4368cc2eeb7ee2035885ee" // valid API key here

interface WeatherWebservice {

    @GET("find?units=metric")
    suspend fun getWeatherList (
        @Query("cnt") count : Int = 50,
        @Query ("lat") latitude : Float,
        @Query ("lon") longitude : Float,
        @Query ("appid") key: String = API_KEY
    ) : List<Weather>
}