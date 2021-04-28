package com.example.lowesweatherapp.view

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.lowesweatherapp.R
import com.example.lowesweatherapp.databinding.FragmentLookupBinding
import com.example.lowesweatherapp.model.AllWeather
import com.example.lowesweatherapp.util.DataState
import com.example.lowesweatherapp.viewmodel.LookupFragmentViewModel

class LookupFragment : Fragment(R.layout.fragment_lookup) {
    private val viewModel by viewModels<LookupFragmentViewModel>()
    private lateinit var binding: FragmentLookupBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLookupBinding.bind(view).apply {
            btnLookup.setOnClickListener { viewModel.query = etCity.text.toString() }
        }
        with(viewModel) {
            state.observe(viewLifecycleOwner) {
                if (it is DataState.Success) navigateToWeatherList(query, it.data.toTypedArray())
                with(binding.textFieldLayout) {
                    isErrorEnabled = if (it is DataState.Error) {
                        error = it.errMsg;true
                    } else false
                }
                binding.loader.isVisible = it is DataState.Loading
                binding.btnLookup.isEnabled = it !is DataState.Loading
            }
        }
    }

    private fun navigateToWeatherList(
        city: String, data: Array<AllWeather>
    ) = with(findNavController()) {
        navigate(LookupFragmentDirections.actionLookupFragmentToAllWeatherFragment(data, city))
        viewModel.hasNavigated = true
    }
}
