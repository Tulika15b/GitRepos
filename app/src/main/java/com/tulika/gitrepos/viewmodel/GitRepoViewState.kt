package com.tulika.gitrepos.viewmodel

import com.tulika.gitrepos.data.GitRepo

sealed class GitRepoViewState{
    object loadingState : GitRepoViewState()
    class errorState(val error : Throwable) : GitRepoViewState()
    class dataLoadedState(val data : List<GitRepo>) : GitRepoViewState()
}
