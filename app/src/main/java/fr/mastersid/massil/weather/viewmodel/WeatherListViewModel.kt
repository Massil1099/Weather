package fr.mastersid.massil.weather.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.mastersid.massil.weather.data.Weather
import fr.mastersid.massil.weather.data.WeatherResponse
import fr.mastersid.massil.weather.repository.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class WeatherListViewModel @Inject constructor (
    private val weatherRepository : WeatherRepository) : ViewModel() {
        private val _weatherList : MutableLiveData<List<Weather>> = MutableLiveData ( emptyList () )
        val weatherList : LiveData <List < Weather > > = _weatherList
        private val _isUpdating = MutableLiveData ( false )
        val isUpdating : LiveData<Boolean> = _isUpdating


         init {
            viewModelScope.launch(Dispatchers.IO) {
                weatherRepository.weatherResponse.collect { response ->
                    when(response) {
                        is WeatherResponse.Pending -> _isUpdating.postValue(true)
                        is WeatherResponse.Success -> {
                            _weatherList.postValue(response.list)
                            _isUpdating.postValue(false)

                        }

                    }
                }
            }
        }

    fun updateWeatherList () {
        viewModelScope.launch(Dispatchers .IO) {
            weatherRepository.updateWeatherInfo ()
        }
    }

}
