package com.davimaia.events.domain.repository

import com.davimaia.events.domain.model.Event

interface EventInterface {

    suspend fun getEvents(): List<Event>
}