package fr.mastersid.massil.weather.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import fr.mastersid.massil.weather.db.WeatherDao
import fr.mastersid.massil.weather.db.WeatherRoomDatabase
import javax.inject.Singleton

@InstallIn( SingletonComponent:: class )
@Module
object WeatherRoomDatabaseModule {
    @Provides
    fun provideWeatherDao ( weatherRoomDatabase : WeatherRoomDatabase) : WeatherDao {
        return weatherRoomDatabase.weatherDao ()
    }
    @Provides
    @Singleton
    fun provideWeatherRoomDatabase ( @ApplicationContext appContext : Context) : WeatherRoomDatabase {
        return Room.databaseBuilder (
            appContext,
            WeatherRoomDatabase :: class.java,
            "weather_database"
        ).build ()
    }
}