package com.mycompany.myapplication.data

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("data") var listWeatherInfo: ArrayList<WeatherInfo>,
    @SerializedName("city_name") var cityName: String
)