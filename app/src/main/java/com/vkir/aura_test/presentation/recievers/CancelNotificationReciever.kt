package com.vkir.aura_test.presentation.recievers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.app.NotificationManager
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.vkir.aura_test.data.CancelClickedNumberPrefs
import com.vkir.aura_test.data.CancellationsNumberPrefs
import com.vkir.aura_test.presentation.work_mangers.BootNotificationWorker
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.util.UUID
import java.util.concurrent.TimeUnit

class CancelNotificationReceiver : BroadcastReceiver(), KoinComponent {

    companion object {

        const val NOTIFICATION_ID_INTENT_KEY = "notification_id_key"
        private const val NOTIFICATION_ID_EMPTY = -1
    }

    private val cancelClickedNumberPrefs: CancelClickedNumberPrefs by inject()
    private val cancellationsNumberPrefs: CancellationsNumberPrefs by inject()

    override fun onReceive(context: Context, intent: Intent) {
        val currentCancels = cancelClickedNumberPrefs.get()
        val updatedValue = currentCancels + 1
        cancelClickedNumberPrefs.save(updatedValue)

        if (updatedValue > cancellationsNumberPrefs.get()) {
            rescheduleTask(context, updatedValue)
        }

        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notificationId = intent.getIntExtra(NOTIFICATION_ID_INTENT_KEY, NOTIFICATION_ID_EMPTY)
        if (notificationId != -1) {
            notificationManager.cancel(notificationId)
        }
    }

    private fun rescheduleTask(context: Context, cancelationTimes: Int) {
        val workManager = WorkManager.getInstance(context)
        val workRequestId = UUID.fromString(DeviceBootBroadCastReceiver.REQUEST_ID)
        workManager.cancelWorkById(workRequestId)

        val notificationWorkRequest = PeriodicWorkRequestBuilder<BootNotificationWorker>(
            repeatInterval = cancelationTimes * 20L,
            repeatIntervalTimeUnit = TimeUnit.MINUTES
        )
            .setId(UUID.fromString(DeviceBootBroadCastReceiver.REQUEST_ID))
            .build()

        WorkManager.getInstance(context).enqueue(notificationWorkRequest)
    }
}
