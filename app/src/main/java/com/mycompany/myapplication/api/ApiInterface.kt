package com.mycompany.myapplication.api

import com.mycompany.myapplication.data.WeatherResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("forecast/daily")
    fun getData(@Query("city") city: String
                , @Query("days") days: Int
                , @Query("key") apiKey: String): Call<WeatherResponse>?

    companion object {
        private const val BASE_URL = "https://api.weatherbit.io/v2.0/"

        fun create(): ApiInterface {
            val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiInterface::class.java)
        }
    }

}