package com.vkir.aura_test.presentation.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.vkir.aura_test.presentation.recievers.CancelNotificationReceiver

class BootNotificationManager(
    private val context: Context
) {

    companion object {
        const val CHANNEL_ID = "1259"
        const val NOTIFICATION_CHANEL_NAME = "BOOT_NOTIFICATION_CHANEL"
    }

    private val notificationManager by lazy {
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    // generate unique ID for each notification
    private val newNotificationId: Int
        get() = System.currentTimeMillis().toInt()

    fun showNotification(notificationText: String) {
        createNotificationChannelIfNeeded(context)
        val notificationId = newNotificationId
        val notification = buildNotification(notificationId, notificationText)
        notificationManager.notify(notificationId, notification)
    }

    private fun createNotificationChannelIfNeeded(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            val existingChannel = notificationManager.getNotificationChannel(CHANNEL_ID)
            if (existingChannel == null) {
                val channel = NotificationChannel(
                    CHANNEL_ID,
                    NOTIFICATION_CHANEL_NAME,
                    NotificationManager.IMPORTANCE_DEFAULT
                )

                notificationManager.createNotificationChannel(channel)
            }
        }
    }

    private fun buildNotification(notificationId: Int, text: String): Notification {
        val cancelIntent = Intent(context, CancelNotificationReceiver::class.java).apply {
            putExtra(CancelNotificationReceiver.NOTIFICATION_ID_INTENT_KEY, notificationId)
        }
        val cancelPendingIntent =
            PendingIntent.getBroadcast(context, 0, cancelIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        val cancellationAction = NotificationCompat.Action.Builder(
            android.R.drawable.ic_menu_close_clear_cancel,
            "Cancel",
            cancelPendingIntent
        ).build()

        return NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle("Aura boot notification")
            .setContentText(text)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .addAction(cancellationAction)
            .build()
    }

}