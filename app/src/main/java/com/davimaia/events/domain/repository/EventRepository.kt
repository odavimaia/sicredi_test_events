package com.davimaia.events.domain.repository

import com.davimaia.events.data.api.RetrofitConnect
import com.davimaia.events.domain.model.Event

class EventRepository(private val retrofitConnect: RetrofitConnect) : EventInterface {

    override suspend fun getEvents(): List<Event> = retrofitConnect.service.getEvents()
}