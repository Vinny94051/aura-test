package com.vkir.aura_test.presentation.work_mangers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.vkir.aura_test.presentation.notification.BootNotificationManager
import com.vkir.aura_test.domain.NotificationTextProvider
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class BootNotificationWorker(
    appContext: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(appContext, workerParams), KoinComponent {

    private val bootNotificationManager: BootNotificationManager by inject()
    private val notificationTextProvider: NotificationTextProvider by inject()

    override suspend fun doWork(): Result {
        val notificationText = notificationTextProvider.provide()
        bootNotificationManager.showNotification(notificationText)
        return Result.success()
    }
}
