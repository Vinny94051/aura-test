package com.vkir.aura_test.presentation.recievers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log
import android.widget.Toast
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.vkir.aura_test.presentation.work_mangers.BootNotificationWorker
import com.vkir.aura_test.presentation.work_mangers.SaveBootEventInfoWorker
import java.util.UUID
import java.util.concurrent.TimeUnit.MINUTES

class DeviceBootBroadCastReceiver : BroadcastReceiver() {

    companion object {

        private const val DEFAULT_NOTIFICATION_PERIOD_IN_MIN = 15L

        const val REQUEST_ID = "6578"
    }

    override fun onReceive(context: Context, intent: Intent) {
        if (Intent.ACTION_BOOT_COMPLETED == intent.action) {
            Log.d("AURA_TEST", "event registered")
            saveBootEventDate(context)
            setupNotificationTask(context)
        }
    }

    private fun saveBootEventDate(context: Context) {
        val workRequest = OneTimeWorkRequestBuilder<SaveBootEventInfoWorker>().build()
        WorkManager.getInstance(context).enqueue(workRequest)
    }

    private fun setupNotificationTask(context: Context) {
        val workRequest = OneTimeWorkRequestBuilder<BootNotificationWorker>()
            .build()
        WorkManager.getInstance(context).enqueue(workRequest)

        val notificationWorkRequest = PeriodicWorkRequestBuilder<BootNotificationWorker>(
            repeatInterval = DEFAULT_NOTIFICATION_PERIOD_IN_MIN,
            repeatIntervalTimeUnit = MINUTES
        )
            .setId(UUID.fromString(REQUEST_ID))
            .build()

        WorkManager.getInstance(context).enqueue(notificationWorkRequest)
    }
}
