package com.davimaia.events.domain.repository

import com.davimaia.events.data.api.RetrofitConnect
import com.davimaia.events.domain.model.CheckInEvent
import com.davimaia.events.domain.model.Event

class EventRepository(private val retrofitConnect: RetrofitConnect) : EventInterface {

    override suspend fun getEvents(): List<Event> = retrofitConnect.service.getEvents()

    override suspend fun getEventDetails(id: String): Event = retrofitConnect.service.getEventDetails(id)

    override suspend fun doCheckIn(checkInEvent: CheckInEvent) = retrofitConnect.service.doCheckIn(checkInEvent)
}