package com.example.myapplication.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Create a Retrofit instance
object RetrofitInstance {
    // IMPORTANT: Replace with the actual IP address and port of your Docker container
    private const val BASE_URL = "http://{COMPUTER_LOCAL_IP}:8000"

    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}
