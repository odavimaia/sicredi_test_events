package com.davimaia.events.presenter.ui.detailedevent.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.davimaia.events.R
import com.davimaia.events.databinding.ActivityDetailedEventBinding
import com.davimaia.events.utils.dateFormat
import com.davimaia.events.utils.loadImage
import com.davimaia.events.utils.moneyFormat
import com.davimaia.events.utils.show
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailedEventActivity : AppCompatActivity() {

    private val binding by lazy{
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
    }

    private fun setupEventDetails(){
        viewModel.eventDetail.observe(this){
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

    private fun setupToolbar(){
        binding.tbEventDetailActivity.title = getString(R.string.actDetailedEventTitle)
        setSupportActionBar(binding.tbEventDetailActivity)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.tbEventDetailActivity.setNavigationOnClickListener {
            finish()
        }
    }

    private fun getEventId() = intent.getStringExtra(EVENT_ID)

}