package com.davimaia.events.domain.model

data class CheckInEvent(
    val eventId: String,
    val name: String,
    val email: String
)
