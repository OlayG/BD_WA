package com.example.lowesweatherapp.repo

import com.example.lowesweatherapp.repo.remote.RetrofitInstance
import com.example.lowesweatherapp.util.DataState
import kotlinx.coroutines.flow.flow
import retrofit2.Response

object WeatherRepo {

    fun getWeatherResponse(city: String) = flow {
        emit(DataState.Loading)
        val state = try {
            val response = RetrofitInstance.weatherService.getWeatherForCityResponse(city)
            if (response.validResponse && response.body()?.list.isNullOrEmpty().not())
                DataState.Success(response.body()!!.list!!)
            else DataState.Error("No results found for $city")
        } catch (ex: Exception) {
            DataState.Error("Something went wrong.")
        }
        emit(state)
    }

    private val <T>Response<T>.validResponse
        get() = isSuccessful && body() != null

}