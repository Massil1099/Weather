package fr.mastersid.massil.weather.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import fr.mastersid.massil.weather.repository.WeatherRepository
import fr.mastersid.massil.weather.repository.WeatherRepositoryDummyImpl

@Module
@InstallIn(ViewModelComponent::class)
abstract class WeatherRepositoryModule {
    @Binds
    abstract fun bindWeatherRepository ( weatherRepositoryImpl : WeatherRepositoryDummyImpl) :
            WeatherRepository
}