package com.tulika.gitrepos.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.tulika.gitrepos.data.remote.GitRepoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import javax.inject.Inject

@HiltViewModel
class GitRepoViewModel @Inject constructor(
    repository : GitRepoRepository
): ViewModel(){

    val reposList = repository.getRepos(true).asLiveData()

}