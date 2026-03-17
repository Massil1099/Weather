package fr.mastersid.massil.weather.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fr.mastersid.massil.weather.webservice.WeatherMoshiAdapter
import fr.mastersid.massil.weather.webservice.WeatherWebservice
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
@Module
@InstallIn(SingletonComponent:: class)
object WeatherWebserviceModule {

    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .add(WeatherMoshiAdapter())
            .build()
    }

    @Provides
    fun provideRetrofit( moshi : Moshi ) : Retrofit {
        return Retrofit.Builder ()
            .addConverterFactory ( MoshiConverterFactory.create(moshi))
            .baseUrl ( BASE_URL )
            .build ()
    }

    @Provides
    fun provideWeatherWebservice (retrofit : Retrofit) : WeatherWebservice {
        return retrofit.create(WeatherWebservice:: class.java)
    }
}
