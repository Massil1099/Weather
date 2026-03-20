package fr.mastersid.massil.weather.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather_table")
data class Weather(@PrimaryKey val id: Int, val city: String, val temperature: Float)