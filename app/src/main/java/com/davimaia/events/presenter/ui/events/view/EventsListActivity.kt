package com.davimaia.events.presenter.ui.events.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.davimaia.events.R
import com.davimaia.events.databinding.ActivityEventsListBinding
import com.davimaia.events.presenter.ui.detailedevent.view.DetailedEventActivity
import com.davimaia.events.presenter.ui.events.adapter.EventsListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class EventsListActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityEventsListBinding.inflate(layoutInflater)
    }
    private val viewModel: EventsListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupToolbar()
        setupEventsList()
    }

    private fun setupEventsList() {
        viewModel.eventsList.observe(this) {
            it?.let { events ->
                binding.rvEventList.layoutManager = LinearLayoutManager(this@EventsListActivity)
                binding.rvEventList.adapter = EventsListAdapter(events) {
                    DetailedEventActivity.start(this@EventsListActivity, it.id)
                }
            }
        }
        viewModel.getEventsList()
    }

    private fun setupToolbar() {
        binding.tbEventListActivity.title = getString(R.string.actEventsListTitle)
    }
}