package com.nihatmahammadli.weatherapp.presentation.screens

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.nihatmahammadli.weatherapp.R
import com.nihatmahammadli.weatherapp.databinding.FragmentShowWeatherBinding
import com.nihatmahammadli.weatherapp.presentation.viewmodels.ShowWeatherViewModel
import java.text.SimpleDateFormat
import java.util.*

class ShowWeatherFragment : Fragment() {
    private lateinit var binding: FragmentShowWeatherBinding
    private val viewModel: ShowWeatherViewModel by viewModels()

    private val countries = listOf("AZ", "TR", "US", "GB", "DE", "RU", "CN", "JP")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShowWeatherBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpSpinner()
        setUpButtonClick()
    }

    private fun setUpSpinner() {
        val adapter = ArrayAdapter(
            requireContext(),
            R.layout.spinner_item,
            countries
        )
        adapter.setDropDownViewResource(R.layout.spinner_item)
        binding.countrySpinner.adapter = adapter

    }

    private fun setUpButtonClick() {
        binding.loadButton.setOnClickListener {
            val city = binding.cityInput.text.toString().trim()
            val country = binding.countrySpinner.selectedItem.toString()

            if (city.isNotEmpty()) {
                loadWeather(city, country)
            } else {
                Toast.makeText(requireContext(), "Şəhər daxil edin", Toast.LENGTH_SHORT).show()
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun loadWeather(city: String, countryCode: String) {
        val query = "$city,$countryCode"

        viewModel.loadWeather(query)
        viewModel.weather.observe(viewLifecycleOwner) { weather ->
            binding.degree.text = "${weather.main.temp}°"

            if (weather.main.temp<0){
                binding.main.setBackgroundResource(R.drawable.snowy_background)
                binding.weatherIcon.setBackgroundResource(R.drawable.cloud_ic)
            }else if(weather.main.temp<20 && weather.main.temp>0){
                binding.main.setBackgroundResource(R.drawable.cloudy_background)
                binding.weatherIcon.setBackgroundResource(R.drawable.cloud_ic)
            }else{
                binding.main.setBackgroundResource(R.drawable.sunny_background)
                binding.weatherIcon.setBackgroundResource(R.drawable.sun_icon)
            }

            binding.location.text = "${weather.name}, ${weather.sys.country}"
            binding.weatherType.text = weather.weather.firstOrNull()?.description ?: ""

            val date = Date(weather.dt * 1000)
            val sdf = SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.getDefault())
            binding.date.text = sdf.format(date)
        }
    }
}
