package com.example.lowesweatherapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.lowesweatherapp.databinding.FragmentSelectedWeatherBinding

class SelectedWeatherFragment : Fragment() {
    private lateinit var binding: FragmentSelectedWeatherBinding
    private val arguments by navArgs<SelectedWeatherFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentSelectedWeatherBinding.inflate(
        inflater, container, false
    ).also { binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            toolbar.title = arguments.city
            toolbar.setNavigationOnClickListener { findNavController().navigateUp() }
            tvMainTemp.text = arguments.selectedWeather.main?.temp.toString()
            tvFeelsTemp.text = arguments.selectedWeather.main?.feelsLike.toString()
            tvMainWeather.text = arguments.selectedWeather.weather?.get(0)?.main
            tvWeatherDescription.text = arguments.selectedWeather.weather?.get(0)?.description
        }
    }
}