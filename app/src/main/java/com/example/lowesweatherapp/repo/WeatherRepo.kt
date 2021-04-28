package com.example.lowesweatherapp.repo

import com.example.lowesweatherapp.model.MyWeather
import com.example.lowesweatherapp.repo.remote.RetrofitInstance
import retrofit2.Response

object WeatherRepo {
    private val weatherService = RetrofitInstance.weatherService

    suspend fun getWeatherResponse(city: String) : Response<MyWeather?> {
        return weatherService.getWeatherForCityResponse(city)
    }
}