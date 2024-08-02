package com.vkir.aura_test

import android.app.Application
import com.vkir.aura_test.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AuraTestApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initDi()
    }

    private fun initDi() {
        startKoin {
            androidContext(this@AuraTestApplication)
            modules(listOf(appModule))
        }
    }
}