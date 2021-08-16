package com.tulika.gitrepos.data

import com.tulika.gitrepos.api.constants.APIConstants
import com.tulika.gitrepos.data.GitRepo
import retrofit2.http.GET

interface IGitRepoAPIService {

    @GET(APIConstants.API_GET_REPOS)
    suspend fun fetchGitRepos() : List<GitRepo>
}