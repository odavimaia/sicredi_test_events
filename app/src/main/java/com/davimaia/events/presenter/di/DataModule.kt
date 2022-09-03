package com.davimaia.events.presenter.di

import com.davimaia.events.data.api.RetrofitConnect
import com.davimaia.events.domain.repository.EventInterface
import com.davimaia.events.domain.repository.EventRepository
import org.koin.core.module.Module
import org.koin.dsl.module

val dataModule: Module = module {
    factory { RetrofitConnect() }
    factory<EventInterface> { EventRepository(get()) }
}