package com.vkir.aura_test.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface EventsInfoDao {

    @Insert
    suspend fun insertEventInfo(eventInfo: EventInfo)

    @Query("SELECT * FROM events_info ORDER BY eventDetectionTimeInMills DESC LIMIT 2")
    suspend fun getLastTwoEvents(): List<EventInfo>
}
