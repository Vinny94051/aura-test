package com.vkir.aura_test.presentation

import androidx.lifecycle.ViewModel
import com.vkir.aura_test.data.CancellationsNumberPrefs

class MainActivityViewModel(
    private val cancellationsNumberPrefs: CancellationsNumberPrefs
) : ViewModel() {

    fun saveMaxCancellationsNumber(number: Int) {
        cancellationsNumberPrefs.save(number)
    }

    fun getMaxCancellationsNumber(): Int {
        return cancellationsNumberPrefs.get()
    }
}