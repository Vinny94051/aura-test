package com.vkir.aura_test.data

import android.content.Context

class CancelClickedNumberPrefs(private val context: Context) {

    companion object {
        private const val NAME = "counter_name"
        private const val DEFAULT_VALUE = 0

        private const val KEY = "number_key"
    }

    private val prefs by lazy { context.getSharedPreferences(NAME, Context.MODE_PRIVATE) }

    fun save(number: Int) {
        prefs.edit()
            .putInt(KEY, number)
            .apply()
    }

    fun get(): Int {
        val value = prefs.getInt(KEY, DEFAULT_VALUE)
        return value
    }
}