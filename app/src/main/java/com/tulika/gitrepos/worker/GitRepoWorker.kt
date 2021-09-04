package com.tulika.gitrepos.worker

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.reflect.Parameter

class GitRepoWorker(context : Context, params: WorkerParameters) : CoroutineWorker(context, params){

    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        val appContext = applicationContext
        try{
            
            Result.success()
        }catch (ex : Exception){
            Log.e("GitRepoWorker", "Error GitRepoWorker", ex)
            Result.failure()
        }
    }
}