package com.example.lowesweatherapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lowesweatherapp.databinding.WeatherItemBinding
import com.example.lowesweatherapp.model.AllWeather

class AllWeatherAdapter(
    private val allWeather: List<AllWeather>,
    private val listener: (weather: AllWeather) -> Unit
) : RecyclerView.Adapter<AllWeatherAdapter.AllWeatherHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ) = WeatherItemBinding.inflate(
        LayoutInflater.from(parent.context), parent, false
    ).let {
        AllWeatherHolder(it).apply {
            itemView.setOnClickListener { listener.invoke(allWeather[adapterPosition]) }
        }
    }

    override fun onBindViewHolder(holder: AllWeatherHolder, position: Int) {
        holder.setAllWeather(allWeather[position])
    }

    override fun getItemCount() = allWeather.size

    class AllWeatherHolder(
        private val binding: WeatherItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun setAllWeather(allWeather: AllWeather) = with(binding) {
            tvWeatherMain.text = "${allWeather.weather?.get(0)?.main}"
            tvTemp.text = String.format("Temp: %d", allWeather.main?.temp?.toInt())
        }
    }
}