package com.example.lowesweatherapp.viewmodel

import androidx.lifecycle.*
import com.example.lowesweatherapp.model.AllWeather
import com.example.lowesweatherapp.repo.WeatherRepo
import com.example.lowesweatherapp.util.DataState

class LookupFragmentViewModel : ViewModel() {

    private val queryLd = MutableLiveData<String>()
    private val hasNavigatedLd = MutableLiveData<Boolean>()

    val state: LiveData<DataState<List<AllWeather>>> =
        MediatorLiveData<DataState<List<AllWeather>>>().apply {
            addSource(hasNavigatedLd) {
                if (it) {
                    value = DataState.NoAction
                    hasNavigated = false
                }
            }
            addSource(queryLd) {
                addSource(WeatherRepo.getWeatherResponse(it).asLiveData()) { state ->
                    value = state
                }
            }
        }

    var query: String = ""
        set(value) {
            queryLd.value = value
            field = value
        }

    var hasNavigated: Boolean = false
        set(value) {
            hasNavigatedLd.value = value
            field = value
        }

}