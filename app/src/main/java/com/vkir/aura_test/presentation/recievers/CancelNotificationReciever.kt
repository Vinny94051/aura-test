package com.vkir.aura_test.presentation.recievers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.app.NotificationManager

class CancelNotificationReceiver : BroadcastReceiver() {

    companion object {

        const val NOTIFICATION_ID_INTENT_KEY = "notification_id_key"
        private const val NOTIFICATION_ID_EMPTY = -1
    }

    override fun onReceive(context: Context, intent: Intent) {
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notificationId = intent.getIntExtra(NOTIFICATION_ID_INTENT_KEY, NOTIFICATION_ID_EMPTY)
        if (notificationId != -1) {
            notificationManager.cancel(notificationId)
        }
    }
}
