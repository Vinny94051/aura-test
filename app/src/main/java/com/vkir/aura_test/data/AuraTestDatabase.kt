package com.vkir.aura_test.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [EventInfo::class], version = 1)
abstract class AuraTestDatabase: RoomDatabase() {

    abstract fun eventsInfoDao(): EventsInfoDao
}