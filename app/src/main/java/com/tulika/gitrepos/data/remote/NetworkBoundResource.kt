package com.tulika.gitrepos.data.remote

import kotlinx.coroutines.flow.*

inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline onFetchFailed: (Throwable) -> Unit = { Unit },
    crossinline shouldFetch: (ResultType) -> Boolean = { true }
) = flow<GitRepoResource<ResultType>> {
    emit(GitRepoResource.Loading(null))
    val data = query().first() //Collecting from flow only one time

    val flow = if (shouldFetch(data)) {
        emit(GitRepoResource.Loading(data))

        try {
            saveFetchResult(fetch())
            query().map { GitRepoResource.Success(it) }
        } catch (throwable: Throwable) {
            onFetchFailed(throwable)
            query().map { GitRepoResource.Error(throwable, it) }
        }
    } else {
        //Current cached data; In case of not right time to re-fetch from server
        query().map { GitRepoResource.Success(it) }
    }

    emitAll(flow)
}