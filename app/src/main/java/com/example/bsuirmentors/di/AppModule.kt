package com.example.bsuirmentors.di

import com.example.bsuirmentors.common.Constants
import com.example.bsuirmentors.data.remote.IISApi
import com.example.bsuirmentors.data.repository.IISRepositoryImpl
import com.example.bsuirmentors.domain.repository.IISRepository
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
    fun provideMentorApi(): IISApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(IISApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMentorRepository(api: IISApi): IISRepository {
        return IISRepositoryImpl(api)
    }
}