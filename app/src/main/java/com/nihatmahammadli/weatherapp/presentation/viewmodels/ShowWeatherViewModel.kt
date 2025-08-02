package com.nihatmahammadli.weatherapp.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nihatmahammadli.weatherapp.data.api.Repository
import com.nihatmahammadli.weatherapp.data.models.WeatherResponse
import kotlinx.coroutines.launch

class ShowWeatherViewModel: ViewModel() {
    private val repository = Repository()

    private val _weather = MutableLiveData<WeatherResponse>()
    val weather : LiveData<WeatherResponse> = _weather

    val API_KEY = "808803b30e13322487dfcad87a8a44ef"


    fun loadWeather(city: String){
        viewModelScope.launch {
            val result = repository.getWeather(city,API_KEY)
            result?.let {
                _weather.value = it
            }
        }
    }
}