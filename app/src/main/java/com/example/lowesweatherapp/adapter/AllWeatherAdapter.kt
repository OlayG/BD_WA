package com.example.lowesweatherapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lowesweatherapp.databinding.WeatherItemBinding
import com.example.lowesweatherapp.model.AllWeather

class AllWeatherAdapter(private val allWeather: List<AllWeather>, private val listener: (weather : AllWeather) -> Unit) :
    RecyclerView.Adapter<AllWeatherAdapter.AllWeatherHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllWeatherHolder {
        val binding: WeatherItemBinding = WeatherItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return AllWeatherHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: AllWeatherHolder, position: Int) {
        holder.setAllWeather(allWeather[position])
    }

    override fun getItemCount(): Int {
        return allWeather.size
    }
    class AllWeatherHolder(private val binding: WeatherItemBinding, private val listener: (weather: AllWeather) -> Unit) : RecyclerView.ViewHolder(binding.root) {
        fun setAllWeather(allWeather: AllWeather) {
            binding.root.setOnClickListener {
                listener.invoke(allWeather)
            }
            binding.tvWeatherMain.text = "${allWeather.weather?.get(0)?.main}"
            binding.tvTemp.text = String.format("Temp: %d", allWeather.main?.temp?.toInt())
        }
    }
}