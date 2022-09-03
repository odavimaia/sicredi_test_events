package com.davimaia.events.data.api

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RetrofitConnect {
    val service: ApiService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(ApiService::class.java)

    companion object{
        private const val BASE_URL = "https://5f5a8f24d44d640016169133.mockapi.io/api/"
    }
}

