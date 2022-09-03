package com.davimaia.events.presenter.ui.events.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.davimaia.events.databinding.EventItemBinding
import com.davimaia.events.domain.model.Event
import com.davimaia.events.utils.dateFormat
import com.davimaia.events.utils.moneyFormat

class EventsListAdapter(
    private val events: List<Event>,
    private val onItemClick: ((event: Event) -> Unit),
) :
    RecyclerView.Adapter<EventsListAdapter.EventsListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, view: Int): EventsListViewHolder {
        val binding =
            EventItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EventsListViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: EventsListViewHolder, position: Int) {
        holder.bind(events[position])
    }

    override fun getItemCount() = events.size

    class EventsListViewHolder(
        private val binding: EventItemBinding,
        private val onItemClick: ((event: Event) -> Unit),
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(event: Event) = with(binding) {
            tvEventTitle.text = event.title
            tvEventDescription.text = event.description
            tvEventDate.text = event.date.dateFormat()
            tvEventPrice.text = event.price.moneyFormat()

            binding.root.setOnClickListener {
                onItemClick.invoke(event)
            }
        }
    }

}