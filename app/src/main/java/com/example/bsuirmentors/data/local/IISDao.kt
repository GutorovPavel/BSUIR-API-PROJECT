package com.example.bsuirmentors.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bsuirmentors.data.local.entities.CookieEntity
import com.example.bsuirmentors.data.local.entities.GroupEntity
import com.example.bsuirmentors.data.local.entities.LoginRequestEntity
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

    //Login
    @Query("SELECT * FROM loginrequestentity WHERE id = 0")
    suspend fun getLoginAndPassword(): LoginRequestEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setLoginAndPassword(loginRequest: LoginRequestEntity)

    @Query("DELETE FROM loginrequestentity")
    suspend fun deleteLoginAndPassword()

    @Query("SELECT * FROM cookieentity WHERE id = 0")
    suspend fun getCookie(): CookieEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setCookie(cookie: CookieEntity)

    @Query("DELETE FROM cookieentity")
    suspend fun deleteCookie()

    //PersonalCv


}