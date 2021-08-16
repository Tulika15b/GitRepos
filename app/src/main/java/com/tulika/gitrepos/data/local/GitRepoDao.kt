package com.tulika.gitrepos.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tulika.gitrepos.data.GitRepo
import kotlinx.coroutines.flow.Flow

@Dao
interface GitRepoDao {
    //This is our single source of truth
    //The data from rest api, will first get to dao, and using this api only we fetch the data
    @Query("SELECT * FROM gitRepo")
    fun getAllRepos(): Flow<List<GitRepo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRepos(repos: List<GitRepo>)

    @Query("DELETE FROM gitRepo")
    suspend fun deleteAll()
}