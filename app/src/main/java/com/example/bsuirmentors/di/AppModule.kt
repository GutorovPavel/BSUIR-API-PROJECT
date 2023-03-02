package com.example.bsuirmentors.di

import com.example.bsuirmentors.common.Constants
import com.example.bsuirmentors.data.remote.MentorApi
import com.example.bsuirmentors.data.repository.MentorRepositoryImpl
import com.example.bsuirmentors.domain.repository.MentorRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMentorApi(): MentorApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MentorApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMentorRepository(api: MentorApi): MentorRepository {
        return MentorRepositoryImpl(api)
    }
}