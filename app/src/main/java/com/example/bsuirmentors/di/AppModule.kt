package com.example.bsuirmentors.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.bsuirmentors.common.Constants
import com.example.bsuirmentors.data.local.Converters
import com.example.bsuirmentors.data.local.IISDatabase
import com.example.bsuirmentors.data.remote.IISApi
import com.example.bsuirmentors.data.repository.IISRepositoryImpl
import com.example.bsuirmentors.data.util.GsonParser
import com.example.bsuirmentors.domain.repository.IISRepository
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideIISApi(): IISApi {

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(IISApi::class.java)
    }

    @Provides
    @Singleton
    fun provideIISDatabase(app: Application): IISDatabase {
        return Room.databaseBuilder(
            app,
            IISDatabase::class.java,
            "iisdb.db"
        )
            .addTypeConverter(Converters(GsonParser(Gson())))
            .build()
    }

    @Provides
    @Singleton
    fun provideIISRepository(api: IISApi, db: IISDatabase): IISRepository {
        return IISRepositoryImpl(api, db.dao)
    }
}