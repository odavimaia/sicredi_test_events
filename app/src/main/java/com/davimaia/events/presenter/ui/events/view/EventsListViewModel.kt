package com.davimaia.events.presenter.ui.events.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davimaia.events.domain.model.Event
import com.davimaia.events.domain.repository.EventInterface
import kotlinx.coroutines.launch

class EventsListViewModel(private val repository: EventInterface) : ViewModel() {

    val eventsList: MutableLiveData<List<Event>> = MutableLiveData()

    fun getEventsList() {
        viewModelScope.launch {
            eventsList.value = repository.getEvents()
        }
    }
}