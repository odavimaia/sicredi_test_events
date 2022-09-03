package com.davimaia.events.presenter.ui.events.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.davimaia.events.R

class EventsListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_events_list)
    }
}