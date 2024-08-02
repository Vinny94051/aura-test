package com.vkir.aura_test.presentation

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import com.vkir.aura_test.utils.PermissionManager
import com.vkir.aura_test.R
import com.vkir.aura_test.databinding.ActivityMainBinding
import com.vkir.aura_test.presentation.recievers.DeviceBootBroadCastReceiver

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val binding by viewBinding<ActivityMainBinding>()

    override fun onStart() {
        super.onStart()
        requestNotificationPermission()
    }

    private fun requestNotificationPermission() {
        val isNotificationPermissionGranted =
            PermissionManager.isNotificationPermissionGranted(this)

        if (!isNotificationPermissionGranted) {
            PermissionManager.requestNotificationPermission(this)
        }
    }
}