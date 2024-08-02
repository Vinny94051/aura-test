package com.vkir.aura_test.domain

import com.vkir.aura_test.data.EventInfo
import com.vkir.aura_test.data.EventsInfoDao

interface SaveEventInfoUseCase {

    suspend fun saveEventInfoUseCase(time: Long)
}

class SaveEventInfoUseCaseImpl(private val eventsInfoDao: EventsInfoDao) : SaveEventInfoUseCase {

    override suspend fun saveEventInfoUseCase(time: Long) {
        val info = EventInfo(eventDetectionTimeInMills = time)
        eventsInfoDao.insertEventInfo(info)
    }
}