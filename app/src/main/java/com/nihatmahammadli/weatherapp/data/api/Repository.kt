package com.nihatmahammadli.weatherapp.data.api

import com.nihatmahammadli.weatherapp.data.models.WeatherResponse

class Repository {
    suspend fun getWeather(city: String, apiKey: String): WeatherResponse? {
        val response = RetrofitInstance.api.getWeatherByCity(city, apiKey)
        return if (response.isSuccessful) response.body() else null
    }
}