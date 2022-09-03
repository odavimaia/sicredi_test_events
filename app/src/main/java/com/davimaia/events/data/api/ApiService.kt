package com.davimaia.events.data.api

import com.davimaia.events.domain.model.Event
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("/events")
    suspend fun getEvents(): List<Event>

    @GET("/events/{id}")
    suspend fun getEventDetails(@Path("id") id: String): Event
}