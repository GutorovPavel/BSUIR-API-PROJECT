package com.example.bsuirmentors.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bsuirmentors.data.local.entities.GroupEntity
import com.example.bsuirmentors.data.local.entities.MentorEntity

@Dao
interface IISDao {

    //Groups
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGroups(groupEntities: List<GroupEntity>)

    @Query("DELETE FROM groupentity")
    suspend fun clearGroups()

    @Query("SELECT * FROM groupentity")
    suspend fun getGroups(): List<GroupEntity>

    //Mentors
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMentors(mentorEntities: List<MentorEntity>)

    @Query("DELETE FROM mentorentity")
    suspend fun clearMentors()

    @Query("SELECT * FROM mentorentity")
    suspend fun getMentors(): List<MentorEntity>
}