package com.vkir.aura_test.di

import android.content.Context
import androidx.room.Room
import com.vkir.aura_test.presentation.notification.BootNotificationManager
import com.vkir.aura_test.data.AuraTestDatabase
import com.vkir.aura_test.data.EventsInfoDao
import com.vkir.aura_test.domain.NotificationTextProvider
import com.vkir.aura_test.domain.NotificationTextProviderImpl
import com.vkir.aura_test.domain.SaveEventInfoUseCase
import com.vkir.aura_test.domain.SaveEventInfoUseCaseImpl
import org.koin.dsl.module

val appModule = module {

    single<AuraTestDatabase> {
        Room.databaseBuilder(
            get<Context>(),
            AuraTestDatabase::class.java,
            "aura_test_database"
        ).build()
    }

    single<EventsInfoDao> { get<AuraTestDatabase>().eventsInfoDao() }

    factory<SaveEventInfoUseCase> { SaveEventInfoUseCaseImpl(get()) }

    single { BootNotificationManager(get()) }

    factory<NotificationTextProvider> { NotificationTextProviderImpl(get(), get()) }
}