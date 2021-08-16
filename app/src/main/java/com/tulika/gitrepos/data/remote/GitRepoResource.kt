package com.tulika.gitrepos.data.remote

sealed class GitRepoResource<T> (
    val data: T? = null,
    val error: Throwable? = null
) {
    class Success<T>(data: T) : GitRepoResource<T>(data)
    class Loading<T>(data: T? = null) : GitRepoResource<T>(data)
    class Error<T>(throwable: Throwable, data: T? = null) : GitRepoResource<T>(data, throwable)
}