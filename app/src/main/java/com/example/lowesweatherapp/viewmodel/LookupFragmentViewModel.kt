package com.example.lowesweatherapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lowesweatherapp.model.MyWeather
import com.example.lowesweatherapp.repo.WeatherRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class LookupFragmentViewModel : ViewModel() {
    private val _myWeatherResponse = MutableLiveData<Response<MyWeather?>>()
    val myWeatherResponse: LiveData<Response<MyWeather?>> get() = _myWeatherResponse

    fun getMyWeatherResponse(city: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val myWeatherResponse = WeatherRepo.getWeatherResponse(city)
            _myWeatherResponse.postValue(myWeatherResponse)
        }
    }
}