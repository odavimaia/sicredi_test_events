package com.davimaia.events.presenter.ui.detailedevent.view

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupToolbar()
        setupEventDetails()
        doCheckIn()
        shareEvent()
    }

    private fun doCheckIn() {
        binding.btEventCheckIn.setOnClickListener {
            hideEventDetails()
            onClickButtonConfirmCheckIn()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_event_details, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.actLocation -> {
            openEventLocation()
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    private fun openEventLocation() {
        viewModel.eventDetail.observe(this) {
            it?.let { event ->
                val latitude = event.latitude
                val longitude = event.longitude
                val locationUri = Uri.parse("geo:$latitude + $longitude?z=20()")
                val mapIntent = Intent(Intent.ACTION_VIEW, locationUri)
                mapIntent.setPackage("com.google.android.apps.maps")
                mapIntent.resolveActivity(packageManager)?.let {
                    startActivity(mapIntent)
                }
            }
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

    private fun shareEvent() {
        binding.btEventShare.setOnClickListener {
            val shareText = viewModel.getShareText()
            val intent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, shareText)
                type = TEXT_TYPE
            }
            val shareIntent = Intent.createChooser(intent, null)
            startActivity(shareIntent)
        }
    }

    private fun getEventId() = intent.getStringExtra(EVENT_ID)

    companion object {
        const val TEXT_TYPE = "text/plain"
        const val EVENT_ID = "eventId"
        fun start(context: Context, eventId: String) {
            val intent =
                Intent(context, DetailedEventActivity::class.java).apply {
                    putExtra(EVENT_ID, eventId)
                }
            context.startActivity(intent)
        }
    }
}