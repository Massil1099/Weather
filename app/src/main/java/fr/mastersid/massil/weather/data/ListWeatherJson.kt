package fr.mastersid.massil.weather.data

data class ListWeatherJson(
    val list: List<WeatherJson>
)

data class WeatherJson (
    val id: Int ,
    val name : String ,
    val main : MainJson
)
data class MainJson (
    val temp : Float
)

