package com.davimaia.events.utils

import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

fun Long.dateFormat(): String {
    val dateFormatted = SimpleDateFormat("EEE, dd MMMM yyyy HH:mm")
    val date = Date(this)
    return dateFormatted.format(date)
        .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
}

fun Float.moneyFormat() = NumberFormat.getCurrencyInstance(Locale("pt","BR")).format(this) ?: ""
