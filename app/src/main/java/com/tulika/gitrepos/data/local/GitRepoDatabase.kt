package com.tulika.gitrepos.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tulika.gitrepos.data.GitRepo

@Database(entities = [GitRepo::class], version = 1)
abstract class GitRepoDatabase : RoomDatabase() {
    abstract fun gitRepoDao() : GitRepoDao
}