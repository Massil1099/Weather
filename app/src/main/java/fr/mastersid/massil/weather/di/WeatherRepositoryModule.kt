package fr.mastersid.massil.weather.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import fr.mastersid.massil.weather.repository.WeatherRepository
import fr.mastersid.massil.weather.repository.WeatherRepositoryDummyImpl
import fr.mastersid.massil.weather.repository.WeatherRepositoryImpl

@Module
@InstallIn(ViewModelComponent::class)
abstract class WeatherRepositoryModule {
    @Binds
    abstract fun bindWeatherRepository ( weatherRepositoryImpl : WeatherRepositoryImpl) :
            WeatherRepository
}