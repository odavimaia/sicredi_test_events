package com.davimaia.events.utils

import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.davimaia.events.R
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

//Função para formatar a data recebida da api para o formato usado no Brasil
fun Long.dateFormat(): String {
    val dateFormatted = SimpleDateFormat("EEE, dd MMMM yyyy HH:mm")
    val date = Date(this)
    return dateFormatted.format(date)
        .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
}

//Função para formatar o preço do evento para real
fun Float.moneyFormat() = NumberFormat.getCurrencyInstance(Locale("pt","BR")).format(this) ?: ""

//Função para mudar o estado de uma view para 'visível'
fun View.show() {
    this.visibility = View.VISIBLE
}

//Função para mostrar as imagens recebidas da api
fun ImageView.loadImage(
    imageUrl: String?,
    @DrawableRes placeholderRes: Int = R.drawable.img_event_example,
    @DrawableRes imageErrorRes: Int = R.drawable.img_not_found
) {
    val requestOptions = RequestOptions().apply {
        placeholder(placeholderRes)
        error(imageErrorRes)
    }
    Glide.with(this.context)
        .setDefaultRequestOptions(requestOptions)
        .load(imageUrl)
        .into(this)
}
