package com.saba.searching.core.di

import android.content.Context
import com.saba.searching.BuildConfig
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import timber.log.Timber
import java.io.File
import java.util.concurrent.TimeUnit

val NetworkModule = module {
    factory { Interceptor() }
    single { fileCache(get()) }
    single { cache(get()) }
    single { httpLoggingInterceptor() }
    single { okHttpClient(get(), get()) }
}

private fun okHttpClient(
    httpLoggingInterceptor: HttpLoggingInterceptor,
    cache: Cache
): OkHttpClient {

    return OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor)
        .cache(cache)
        .addInterceptor(Interceptor())
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .connectTimeout(30, TimeUnit.SECONDS)
        .build()
}

fun httpLoggingInterceptor(): HttpLoggingInterceptor {

    val levels = if (BuildConfig.DEBUG)
        HttpLoggingInterceptor.Level.BODY
    else
        HttpLoggingInterceptor.Level.NONE

    return HttpLoggingInterceptor { message ->
        Timber.tag("Network").e(message)
    }.apply {
        level = levels
    }
}

fun cache(fileCache: File): Cache {
    return Cache(fileCache, 50 * 1000 * 1000)
}

fun fileCache(mContext: Context): File {
    val file = File(mContext.cacheDir, "okhttp_cache")
    file.mkdir()
    return file
}
