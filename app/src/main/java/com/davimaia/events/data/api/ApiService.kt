package com.davimaia.events.data.api

import com.davimaia.events.domain.model.Event
import retrofit2.http.GET

interface ApiService {

    @GET("/events")
    suspend fun getEvents(): List<Event>
}