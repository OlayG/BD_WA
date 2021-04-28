package com.example.lowesweatherapp.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.lowesweatherapp.R
import com.example.lowesweatherapp.adapter.AllWeatherAdapter
import com.example.lowesweatherapp.databinding.FragmentAllWeatherBinding
import com.example.lowesweatherapp.model.AllWeather
import com.example.lowesweatherapp.util.init

class AllWeatherFragment : Fragment(R.layout.fragment_all_weather) {
    private val arguments by navArgs<AllWeatherFragmentArgs>()
    private val weatherAdapter by lazy {
        AllWeatherAdapter(arguments.myWeather.toList(), ::handleWeatherClick)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(FragmentAllWeatherBinding.bind(view)) {
            toolbar.init(arguments.city)
            rvWeather.adapter = weatherAdapter
        }
    }

    private fun handleWeatherClick(weather: AllWeather) = with(findNavController()) {
        navigate(
            AllWeatherFragmentDirections.actionAllWeatherFragmentToSelectedWeatherFragment(
                weather, arguments.city
            )
        )
    }
}