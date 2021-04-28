package com.example.lowesweatherapp.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.example.lowesweatherapp.databinding.FragmentLookupBinding
import com.example.lowesweatherapp.viewmodel.LookupFragmentViewModel

class LookupFragment : Fragment() {
    private lateinit var binding: FragmentLookupBinding
    private val viewModel by viewModels<LookupFragmentViewModel>()
    private lateinit var action:  NavDirections
    private var isAction = false // if you go back the observer is called for the data already in it (first city search)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentLookupBinding.inflate(inflater, container, false)
        .also { binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isAction = false
        binding.btnLookup.setOnClickListener {
            isAction = true
            viewModel.getMyWeatherResponse(binding.etCity.text.toString())
            // this could be bad but idea is wait until we have weather to go to next fragment
        }
        viewModel.myWeatherResponse.observe(this.viewLifecycleOwner, Observer { response ->
            if(response.isSuccessful) {
                response.body()?.let { myWeather ->
                    action = LookupFragmentDirections.actionLookupFragmentToAllWeatherFragment(myWeather)
                    if(isAction) findNavController().navigate(action)
                }
            } else {
                Toast.makeText(context, response.message(),Toast.LENGTH_LONG).show()
            }
        })
    }
}