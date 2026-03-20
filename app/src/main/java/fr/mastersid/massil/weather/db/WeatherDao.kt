package fr.mastersid.massil.weather.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll ( list: List <Weather>)

    @Query("SELECT * FROM weather_table")
    fun getWeatherListFlow(): Flow<List<Weather>>
}