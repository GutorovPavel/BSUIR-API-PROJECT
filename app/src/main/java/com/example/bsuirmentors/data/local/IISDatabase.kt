package com.example.bsuirmentors.data.local

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.bsuirmentors.data.local.entities.GroupEntity
import com.example.bsuirmentors.data.local.entities.MentorEntity

@Database(
    entities = [
        GroupEntity::class,
        MentorEntity::class
    ],
    version = 1
)
@TypeConverters(Converters::class)
abstract class IISDatabase: RoomDatabase() {
    abstract val dao: IISDao
}