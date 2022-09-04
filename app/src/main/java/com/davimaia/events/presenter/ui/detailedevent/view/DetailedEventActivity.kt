package com.davimaia.events.presenter.ui.detailedevent.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.davimaia.events.R
import com.davimaia.events.databinding.ActivityDetailedEventBinding
import com.davimaia.events.utils.*
import org.koin.androidx.viewmodel.ext.android.viewModel

open class DetailedEventActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityDetailedEventBinding.inflate(layoutInflater)
    }

    private val viewModel: DetailedEventViewModel by viewModel()

    companion object {
        const val EVENT_ID = "eventId"
        fun start(context: Context, eventId: String) {
            val intent =
                Intent(context, DetailedEventActivity::class.java).apply {
                    putExtra(EVENT_ID, eventId)
                }
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupToolbar()
        setupEventDetails()
        doCheckIn()
    }

    private fun doCheckIn() {
        binding.btEventCheckIn.setOnClickListener {
            hideEventDetails()
            onClickButtonConfirmCheckIn()
        }
    }

    private fun onClickButtonConfirmCheckIn() {
        binding.btConfirmCheckIn.setOnClickListener {
            val eventId = getEventId().toString()
            val name = binding.tvEditCheckInName.text.toString()
            val email = binding.tvEditCheckInEmail.text.toString()
            postCheckIn(name, email, eventId)
            showEventDetails()
        }
    }

    private fun showEventDetails() {
        binding.svEventDetail.show()
        binding.clDoCheckIn.hide()
        binding.btEventCheckIn.show()
        binding.btEventShare.show()
    }

    private fun postCheckIn(name: String, email: String, eventId: String) {
        if (name.isEmpty() && email.isEmpty()) {
            Toast.makeText(this, "Tem algum campo vazio!", Toast.LENGTH_SHORT).show()
        } else {
            viewModel.postCheckIn(eventId, name, email)
        }
    }

    private fun hideEventDetails() {
        binding.svEventDetail.hide()
        binding.clDoCheckIn.show()
        binding.btEventCheckIn.hide()
        binding.btEventShare.hide()
    }

    private fun setupEventDetails() {
        viewModel.eventDetail.observe(this) {
            binding.clEventDetailContent.show()
            it?.let { event ->
                binding.ivEventDetailImage.loadImage(event.image)
                binding.tvEventDetailTitle.text = event.title
                binding.tvEventDetailDescription.text = event.description
                binding.tvEventDetailDate.text = event.date.dateFormat()
                binding.tvEventDetailPrice.text = event.price.moneyFormat()
            }
        }
        viewModel.getDetailedEvent(getEventId().toString())
    }

    private fun setupToolbar() {
        binding.tbEventDetailActivity.title = getString(R.string.actDetailedEventTitle)
        setSupportActionBar(binding.tbEventDetailActivity)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.tbEventDetailActivity.setNavigationOnClickListener {
            finish()
        }
    }

    private fun getEventId() = intent.getStringExtra(EVENT_ID)

}