package com.vkir.aura_test.domain

import android.content.Context
import com.vkir.aura_test.R
import com.vkir.aura_test.data.EventInfo
import com.vkir.aura_test.data.EventsInfoDao
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

interface NotificationTextProvider {

    suspend fun provide(): String
}

class NotificationTextProviderImpl(
    private val context: Context,
    private val eventInfoDao: EventsInfoDao
) : NotificationTextProvider {

    companion object {

        private const val DATE_FORMAT = "dd/MM/yyyy HH:mm:ss"
        private const val DELTA_DATE_FORMAT = "HH:mm:ss"
    }

    override suspend fun provide(): String {
        val lastTwoEvents = eventInfoDao.getLastTwoEvents()
        return when (lastTwoEvents.size) {
            0 -> context.getString(R.string.notification_text_no_boots_detected)
            1 -> buildOneEventText(lastTwoEvents.first())
            else -> buildEventsText(lastTwoEvents)
        }
    }

    private fun buildOneEventText(eventInfo: EventInfo): String {
        val formattedDate = formatTimeMillis(eventInfo.eventDetectionTimeInMills)
        return context.getString(R.string.notification_text_one_boot_detected, formattedDate)
    }

    private fun buildEventsText(eventsInfo: List<EventInfo>): String {
        return when (eventsInfo.size) {
            2 -> buildTwoEventsText(eventsInfo)
            else -> buildTwoEventsText(eventsInfo.takeLast(2))
        }
    }

    private fun buildTwoEventsText(eventsInfo: List<EventInfo>): String {
        val difMills =
            eventsInfo.last().eventDetectionTimeInMills - eventsInfo.first().eventDetectionTimeInMills
        val date = Date(difMills)
        val dateFormat = SimpleDateFormat(DELTA_DATE_FORMAT, Locale.getDefault())
        return dateFormat.format(date)
    }

    private fun formatTimeMillis(timeMillis: Long): String {
        val dateFormat = SimpleDateFormat(DATE_FORMAT, Locale.getDefault())
        return dateFormat.format(Date(timeMillis))
    }
}