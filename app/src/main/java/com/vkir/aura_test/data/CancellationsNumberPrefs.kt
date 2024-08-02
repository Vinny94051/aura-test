package com.vkir.aura_test.data

import android.content.Context

class CancellationsNumberPrefs(private val context: Context) {

    companion object {
        private const val NAME = "c_name"
        private const val DEFAULT_VALUE = 5

        private const val KEY = "n_key"
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