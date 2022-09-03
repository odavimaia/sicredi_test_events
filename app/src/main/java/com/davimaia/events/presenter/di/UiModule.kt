package com.davimaia.events.presenter.di

import com.davimaia.events.presenter.ui.events.view.EventsListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val uiModule: Module = module {
    viewModel { EventsListViewModel(get()) }
}