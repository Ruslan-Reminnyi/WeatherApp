package com.mycompany.myapplication.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mycompany.myapplication.api.ApiInterface
import com.mycompany.myapplication.data.WeatherInfo
import com.mycompany.myapplication.data.WeatherResponse
import com.mycompany.myapplication.utilities.Preferences
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    private val _mutableWeatherList = MutableLiveData<ArrayList<WeatherInfo>>()

    val weatherList: LiveData<ArrayList<WeatherInfo>> get() = _mutableWeatherList

    fun checkLoginInput(email: String, password: String): Boolean {

        return email == Preferences.readLogin() && password == Preferences.readPassword()

    }

    fun checkRegistrationInput(
        email: String?,
        password: String?,
        confirmPassword: String?
    ): Boolean {
        return !(email.isNullOrEmpty() && password.isNullOrEmpty()
                && confirmPassword.isNullOrEmpty()) || password != confirmPassword
    }

    fun writeRegistrationData(email: String, password: String) {
        Preferences.writeLogin(email = email)
        Preferences.writePassword(password = password)
    }

    fun getWeather() {

        val city = "Kharkov,UA"
        val days = 10
        val apiKey = "d5ffa7aa9caf421baae4049d85dd8b26"

        val retrofit =  ApiInterface.create()
        val call: Call<WeatherResponse>? = retrofit.getData(city, days, apiKey)
        call?.enqueue(
                object : Callback<WeatherResponse> {
                    override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                        Log.i("onResponse ", "${response.body()?.cityName}")
                        Log.i("onResponse ", "${response.body()?.listWeatherInfo?.get(0)?.averageTemperature}")
                        _mutableWeatherList.setValue(response.body()?.listWeatherInfo)
                    }

                    override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                        Log.i("onFailure", "${t.message}")
                    }
                }
        )

    }

    sealed class State {
        class Default : State()
        class Loading : State()
        class Success : State()
    }

}