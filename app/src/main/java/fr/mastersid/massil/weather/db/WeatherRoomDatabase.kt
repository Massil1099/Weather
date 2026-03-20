package fr.mastersid.massil.weather.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Weather :: class],
    version = 1,
    exportSchema = false
)
abstract class WeatherRoomDatabase : RoomDatabase() {
    abstract fun weatherDao() : WeatherDao
}