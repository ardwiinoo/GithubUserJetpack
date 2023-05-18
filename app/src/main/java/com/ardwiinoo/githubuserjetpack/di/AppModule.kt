package com.ardwiinoo.githubuserjetpack.di

import android.content.Context
import androidx.room.Room
import com.ardwiinoo.githubuserjetpack.BuildConfig
import com.ardwiinoo.githubuserjetpack.data.local.database.AppDatabase
import com.ardwiinoo.githubuserjetpack.data.remote.apiService.ApiService
import com.ardwiinoo.githubuserjetpack.data.repository.ApiRepository
import com.ardwiinoo.githubuserjetpack.data.repository.LocalRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // retrofit
    @Singleton
    @Provides
    fun provideApiConfig() : ApiService {
        val loggingInterceptor = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        } else {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
        }
        val authInterceptor = Interceptor { chain ->
            val req = chain.request()
            val requestHeaders = req.newBuilder()
                .addHeader("Authorization", "token ${BuildConfig.API_KEY}")
                .build()
            chain.proceed(requestHeaders)
        }
        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(authInterceptor)
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        return retrofit.create(ApiService::class.java)
    }

    // repository
    @Provides
    fun provideApiRepository(apiService: ApiService): ApiRepository {
        return ApiRepository(apiService)
    }

    @Provides
    fun provideLocalRepository(database: AppDatabase): LocalRepository {
        return LocalRepository(database)
    }

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext appContext: Context) : AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java, "github_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}