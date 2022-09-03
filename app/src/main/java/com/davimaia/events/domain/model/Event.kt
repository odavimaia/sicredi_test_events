package com.davimaia.events.domain.model

data class Event(
    val id: String,
    val title: String,
    val description: String,
    val longitude: String,
    val latitude: String,
    val date: Long,
    val price: Float,
    val image: String
)
