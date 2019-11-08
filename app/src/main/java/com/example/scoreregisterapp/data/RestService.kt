package com.example.scoreregisterapp.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RestService {
    private const val BASE_URL = "https://api.backendless.com/"
    const val APP_ID = "B642684C-58D1-9156-FFD9-6B79EE22FC00"
    const val API_KEY = "3ED88B66-E351-72C0-FF13-D6B228F21800"

    private fun getRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getRestProvider() : RestProvider {
        return getRetrofit().create<RestProvider>(RestProvider::class.java)
    }
}