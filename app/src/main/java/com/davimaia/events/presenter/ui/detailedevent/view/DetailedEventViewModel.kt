package com.davimaia.events.presenter.ui.detailedevent.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davimaia.events.domain.model.Event
import com.davimaia.events.domain.repository.EventInterface
import kotlinx.coroutines.launch

class DetailedEventViewModel(private val repositoryInterface: EventInterface): ViewModel() {

    val eventDetail: MutableLiveData<Event> = MutableLiveData()

    fun getDetailedEvent(eventId: String){
        viewModelScope.launch {
            eventDetail.value = repositoryInterface.getEventDetails(eventId)
        }
    }
}