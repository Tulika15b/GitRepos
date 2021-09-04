package com.tulika.gitrepos.data.remote

import androidx.room.withTransaction
import com.tulika.gitrepos.data.IGitRepoAPIService
import com.tulika.gitrepos.data.GitRepo
import com.tulika.gitrepos.data.local.GitRepoDatabase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class GitRepoRepository @Inject constructor(
    private val api: IGitRepoAPIService,
    private val db: GitRepoDatabase
) {
    private val gitRepoDao = db.gitRepoDao()

    fun getRepos(isFetch : Boolean): Flow<GitRepoResource<List<GitRepo>>> {
        return networkBoundResource(
            query = {
                gitRepoDao.getAllRepos() },
            fetch = {
                delay(500)
                api.fetchGitRepos() },
            saveFetchResult = { repos ->
                db.withTransaction {
                    gitRepoDao.deleteAll()
                    gitRepoDao.insertRepos(repos)
                }
            },
            shouldFetch ={
                isFetch
            }
        )
    }

    suspend fun deleteStaleData() : Unit{
        gitRepoDao.deleteAll()
    }


}
