package com.example.lowesweatherapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lowesweatherapp.adapter.AllWeatherAdapter
import com.example.lowesweatherapp.databinding.FragmentAllWeatherBinding
import com.example.lowesweatherapp.viewmodel.LookupFragmentViewModel

class AllWeatherFragment : Fragment() {
    private lateinit var binding: FragmentAllWeatherBinding
    private val arguments by navArgs<AllWeatherFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentAllWeatherBinding.inflate(
        inflater, container, false)
        .also { binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.title = arguments.myWeather.city?.name
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        binding.rvWeather.layoutManager = LinearLayoutManager(this.context)
        binding.rvWeather.adapter = arguments.myWeather.list?.let { AllWeatherAdapter(it) { allWeather ->
            val action = AllWeatherFragmentDirections.actionAllWeatherFragmentToSelectedWeatherFragment(allWeather, arguments.myWeather.city?.name.toString())
            findNavController().navigate(action)
        } }
    }
}