package com.dissiapps.weatherapp.networkBoundResources

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

inline fun <RequestType, ResultType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline shouldFetch: (ResultType) -> Boolean = {true},
    crossinline onFetchSuccess: () -> Unit = {},
    crossinline onFetchFailed: (Throwable) -> Unit = {}
) = channelFlow {

    Log.e("TAG", "networkBoundResource: +++++++++++")
    val data = query().first()

    if ( shouldFetch(data)) {
        val loading = launch {
            query().collect { send(Resource.Loading(it)) }
        }
        try {
            val newData = fetch()
            saveFetchResult(newData)
            loading.cancel()
            onFetchSuccess()
            query().collect { send(Resource.Success(it)) }
        } catch (tr: Throwable) {
            Log.e("TAG", "networkBoundResource: ", tr)
            loading.cancel()
            onFetchFailed(tr)
            query().collect { send(Resource.Error(it, tr)) }
        }
    } else {
        query().collect { send(Resource.Success(it)) }
    }
}