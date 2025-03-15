package com.example.a1lab

import androidx.lifecycle.*
import kotlinx.coroutines.launch

class AirQualityViewModel(private val repository: AirQualityRepository) : ViewModel() {
    private val _airQuality = MutableLiveData<String>()
    val airQuality: LiveData<String> get() = _airQuality

    fun fetchNearestSensor(lat: Double, lon: Double) {
        viewModelScope.launch {
            try {
                val sensorIndex = repository.getNearestSensor("", lat, lon)

                if (sensorIndex == null) {
                    _airQuality.postValue("Ближайший сенсор не найден")
                    return@launch
                }

                val response = sensorIndex.let { repository.getAirQuality(it, "") }
                _airQuality.postValue("PM2.5: ${response.sensor.pm2_5}, lat: ${response.sensor.latitude}, long: ${response.sensor.longitude}")
            } catch (e: Exception) {
                _airQuality.postValue("Ошибка загрузки данных: ${e.message}")
            }
        }
    }
}



class AirQualityViewModelFactory(private val repository: AirQualityRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AirQualityViewModel::class.java)) {
            return AirQualityViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

