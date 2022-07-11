package com.saba.searching.core.utility

import com.saba.searching.core.base.BaseObserverError
import com.saba.searching.core.data.Response
import com.saba.searching.core.data.Response.Companion.loading
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.*
import retrofit2.HttpException
import java.util.concurrent.TimeUnit
import kotlin.coroutines.CoroutineContext


var backgroundContextDefault: CoroutineContext = Dispatchers.Default
var backgroundContextIO: CoroutineContext = Dispatchers.IO
var foregroundContext: CoroutineContext = Dispatchers.Main

fun <T> Observable<T>.fromWorkerToMainWithOutAny(): Observable<T> {
    return this.subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
}

fun <T> calculatorData(
    data: ArrayList<T>,
    onNext: (T) -> Unit
) {
    Observable.fromIterable(data)
        .delaySubscription(100, TimeUnit.MILLISECONDS)
        .fromWorkerToMainWithOutAny()
        .subscribe {
            onNext(it)
        }

}


fun execute(
    block: suspend CoroutineScope.() -> Unit
) {
    val handler = CoroutineExceptionHandler { _, e ->
    }
    CoroutineScope(foregroundContext + handler).launch {
        withContext(backgroundContextIO) {
            block.invoke(this)
        }
    }
}


suspend fun <T> setNetWork(
    callBack: (Response<T>) -> Unit,
    block: suspend () -> T
) {
    callBack(Response.loading())
    try {
        val data = block.invoke()
        if (data is Response<*>) {
            val datres = data as retrofit2.Response<*>
            if (!datres.isSuccessful){
                throw HttpException(data)
            }
        }
        callBack(Response.success(data)!!)
    } catch (e: Exception) {
        BaseObserverError().setErrorInfo(e)
        callBack(Response.error(e.message))
    }

}