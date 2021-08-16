package com.tulika.gitrepos.di

import android.app.Application
import androidx.room.Room
import com.tulika.gitrepos.data.IGitRepoAPIService
import com.tulika.gitrepos.api.constants.APIConstants
import com.tulika.gitrepos.data.local.GitRepoDatabase
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
    fun provideRetrofit() : Retrofit = Retrofit.Builder()
        .baseUrl(APIConstants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideGitRepoAPI(retrofit : Retrofit) : IGitRepoAPIService =
        retrofit.create(IGitRepoAPIService::class.java)

    @Provides
    @Singleton
    fun provideDatabase(app: Application) : GitRepoDatabase =
        Room.databaseBuilder(app, GitRepoDatabase::class.java, "gitrepo_database")
            .build()
}