package com.vkir.aura_test.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "events_info")
data class EventInfo(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val eventDetectionTimeInMills: Long
)

