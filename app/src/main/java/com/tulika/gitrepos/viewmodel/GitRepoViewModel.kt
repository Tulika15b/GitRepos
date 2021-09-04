package com.tulika.gitrepos.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.work.WorkManager
import com.tulika.gitrepos.data.remote.GitRepoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class GitRepoViewModel @Inject constructor(
    //context : Context,
    repository : GitRepoRepository
): ViewModel(){

  //  private val workManager = WorkManager.getInstance(context)

    val reposList = repository.getRepos(true).asLiveData()

}